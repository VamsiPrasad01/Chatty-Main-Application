package auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {
    @NotBlank(message = "Refresh token must not be empty")
    private String refreshToken;
}
