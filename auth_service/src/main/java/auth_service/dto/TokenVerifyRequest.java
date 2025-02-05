package auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenVerifyRequest {
    @NotBlank(message = "Access token is required")
    private String accessToken;
}

