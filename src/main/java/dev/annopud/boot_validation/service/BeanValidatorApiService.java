package dev.annopud.boot_validation.service;

import dev.annopud.boot_validation.model.PersonForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
// Uncomment the @Validated annotation to enable method-level validation
//@Validated
public class BeanValidatorApiService {

    private final Validator validator;

    public String testValidate(
//        Uncomment the @Valid annotation to see the difference
//        @Valid
        PersonForm form
    ) {
        Set<ConstraintViolation<PersonForm>> validate = validator.validate(form);
        for (ConstraintViolation<PersonForm> violation : validate) {
            log.info("Validation error: {}", violation.getMessage());
        }
        return "Hello from POST validated request";
    }
}
