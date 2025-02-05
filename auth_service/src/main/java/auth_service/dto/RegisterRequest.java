package auth_service.dto;

import auth_service.validation.PasswordMatch;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch(message = "Passwords must match")
public class RegisterRequest {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$",
            message = "Username can only contain letters, numbers, dots, underscores and hyphens")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
    private String password;

    @NotBlank(message = "Password repeat is mandatory")
    private String passwordRepeat;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 255, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[\\p{L}\\s'-]+$", message = "Name can only contain letters from any language, spaces and hyphens")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 255, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[\\p{L}\\s'-]+$", message = "Name can only contain letters from any language, spaces and hyphens")
    private String lastName;

    @NotNull(message = "Date of birth is mandatory")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
}
