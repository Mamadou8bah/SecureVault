package Mamadou.com.SecureVault.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    @NotBlank(message = "Name Cannot be empty")
    private String fullName;
    @Email(message = "email must be valid")
    private String email;
    @Size(min = 8,message = "Password should be a minimum of 8 characters long")
    private String password;
}
