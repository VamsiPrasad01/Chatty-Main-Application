package auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "'username' field is required.")
    private String username;

    @NotBlank(message = "'password' field is required.")
    private String password;
}
