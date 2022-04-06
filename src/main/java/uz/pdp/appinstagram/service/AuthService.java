package uz.pdp.appinstagram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appinstagram.component.MailSender;
import uz.pdp.appinstagram.entity.User;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.RegisterDto;
import uz.pdp.appinstagram.repository.UserRepository;
import uz.pdp.appinstagram.security.CurrentUser;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    final
    UserRepository userRepository;

    final
    MailSender mailSender;

    final
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username).get();
    }

    public ApiResponse register(RegisterDto dto) throws MessagingException {
        boolean byUsername = userRepository.existsByUsername(dto.getUsername());
        if (byUsername) {
            return new ApiResponse("This username is already exist",false);
        }
        boolean byPhone = userRepository.existsByPhone(dto.getNumberOrEmail());
        if (byPhone) {
            return new ApiResponse("This phone number is already exist",false);
        }
        boolean byEmail = userRepository.existsByEmail(dto.getNumberOrEmail());
        if (byEmail) {
            return new ApiResponse("This email is already exist",false);
        }
        User user = new User();
        if (dto.getNumberOrEmail().contains("@")) {
            user.setEmail(dto.getNumberOrEmail());
        }else {
            user.setPhone(dto.getNumberOrEmail());
        }
        user.setUsername(dto.getUsername());
        user.setName(dto.getFullName());
        user.setPassword(dto.getPassword());
        user.setActive(true);



        //4 xonali
        String code = UUID.randomUUID().toString().substring(0,5);//.concat(UUID.randomUUID().toString().substring(0,5));
        user.setCode(code);
        //mail chaqirib xabar jo'natish kerak
        SimpleMailMessage message = new SimpleMailMessage();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.addHeader("content-type", "html/text");
        message.setFrom("pdp@gmail.com");
        message.setTo(dto.getNumberOrEmail());
        message.setSubject("Confirmation code");
        message.setText(code);
        message.setSentDate(new Date());
        mailSender.getEmail().send(message);



        userRepository.save(user);
        return new ApiResponse("Code is sent to your email. Please verify!",true);
    }
    public ApiResponse verify(String email, String password) {
        Optional<User> byUserName = userRepository.findByUsername(email);
        if (!byUserName.isPresent()) return new ApiResponse("Error",false);

        if (!byUserName.get().getPassword().equals(passwordEncoder.encode(password)))
            return new ApiResponse("Confirmation code is wrong",false);
        return new ApiResponse("It's a good",true);
    }

    public ApiResponse verifyCode(String code,String username) {

        Optional<User> byUsername = userRepository.findByUsername(username);
        if (!byUsername.isPresent()) {
            return new ApiResponse("User not found",false);
        }
        if (byUsername.get().getCode().equals(code)) {
            return new ApiResponse("Success",true);
        }
        return new ApiResponse("Confirmation code is wrong",false);
    }
}
