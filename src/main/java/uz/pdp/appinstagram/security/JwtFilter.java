package uz.pdp.appinstagram.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.appinstagram.service.AuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    final JwtProvider jwtProvider;

    final AuthService authService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
//        token = token.substring(7);
        //validate expired kimga tegishli
        if (jwtProvider.validateToken(token)) {
            if (jwtProvider.expireToken(token)) {
                String username = jwtProvider.getUserNameFromToken(token);
                if (username!=null){
                UserDetails userDetails = authService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
            }}

        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

      doFilter(request,response,filterChain);

    }
}
