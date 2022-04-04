package uz.pdp.appinstagram.controller;

//import com.example.soliqjwttask.dto.LoginDTO;
//import com.example.soliqjwttask.security.JwtProvider;
//import com.example.soliqjwttask.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appinstagram.payload.ApiResponse;
import uz.pdp.appinstagram.payload.LoginDTO;
import uz.pdp.appinstagram.payload.RegisterDto;
import uz.pdp.appinstagram.security.JwtProvider;
import uz.pdp.appinstagram.service.AuthService;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO){
        String token=jwtProvider.generateToken(loginDTO.getUserName());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto dto) throws MessagingException {
        ApiResponse response = authService.register(dto);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("/verifyEmail")
    public HttpEntity<?> verify(@RequestParam String email, @RequestParam String code, @RequestBody String password) {

        ApiResponse response = authService.verify(email, password);
        return ResponseEntity.status(response.isSuccess()?
                HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }


}
