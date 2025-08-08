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
public class LoginRequest {
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email Should not be empty")
    private String email;
    private String password;
}
