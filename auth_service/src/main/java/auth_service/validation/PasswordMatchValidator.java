package auth_service.validation;

import auth_service.dto.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return true; // or return false if you want to handle null case explicitly
        }
        return request.getPassword() != null && request.getPassword().equals(request.getPasswordRepeat());
    }
}
