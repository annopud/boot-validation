package dev.annopud.boot_validation.validators;

import dev.annopud.boot_validation.model.UserRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Email;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserRequestValidator implements Validator {


    private final jakarta.validation.Validator beanValidator;

    public UserRequestValidator(jakarta.validation.Validator beanValidator) {
        this.beanValidator = beanValidator;
    }

    private static final Pattern MOBILE_PATTERN = Pattern.compile("^0\\d{9}$");

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return UserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UserRequest req = (UserRequest) target;

        // 1. Validate mobilePhone conditionally
        validateMobilePhone(errors, req);

        // 2. Validate email conditionally
        validateEmail(errors, req, beanValidator);
    }

    private void validateEmail(Errors errors, UserRequest req, jakarta.validation.Validator validator) {
        if (!"N".equalsIgnoreCase(req.getIsSelectedEasyAuthen())) {
            return;
        }

        String field = "email";
        if (req.getEmail() == null || req.getEmail().isBlank()) {
            errors.rejectValue(field, "email.required");
        } else {
            EmailWrapper wrapper = new EmailWrapper(req.getEmail());
//            Set<ConstraintViolation<EmailWrapper>> violations = validator.validateProperty(wrapper, field);
            Set<ConstraintViolation<EmailWrapper>> violations = validator.validate(wrapper);
            for (ConstraintViolation<EmailWrapper> violation : violations) {
                errors.rejectValue(field, "email.invalid", violation.getMessage());
            }
        }
    }

    private static void validateMobilePhone(Errors errors, UserRequest req) {
        if (!"N".equalsIgnoreCase(req.getIsSelectedEasyAuthen())) {
            return;
        }

        if (req.getMobilePhone() == null || req.getMobilePhone().isBlank()) {
            errors.rejectValue("mobilePhone", "mobilePhone.required");
        } else if (!MOBILE_PATTERN.matcher(req.getMobilePhone()).matches()) {
            errors.rejectValue("mobilePhone", "mobilePhone.invalid");
        }
    }

    public static class EmailWrapper {
        @Email
        private final String email;

        public EmailWrapper(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }
}
