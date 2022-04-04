package uz.pdp.appinstagram.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {

    private String numberOrEmail;
    private String fullName;
    private String username;
    private String password;
}
