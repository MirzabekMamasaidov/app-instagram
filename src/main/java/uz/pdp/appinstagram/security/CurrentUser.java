package uz.pdp.appinstagram.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.LOCAL_VARIABLE, ElementType.TYPE, ElementType.PARAMETER})
@AuthenticationPrincipal
public @interface CurrentUser {
}
