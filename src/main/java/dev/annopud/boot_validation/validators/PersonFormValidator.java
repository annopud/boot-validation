package dev.annopud.boot_validation.validators;

import dev.annopud.boot_validation.model.PersonFormNoAnnotation;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonFormValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class clazz) {
        return PersonFormNoAnnotation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors){

        PersonFormNoAnnotation form = (PersonFormNoAnnotation) target;

        if(errors.getErrorCount() == 0){
            ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "age",
                "error.age",
                "Age is required.");
            ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "name",
                "error.name",
                "Nameis required.");

            if(form.getAge() < 18){
                ValidationUtils.rejectIfEmptyOrWhitespace(
                    errors, "guardian",
                    "error.guardian",
                    "Guardian name is required.");
            }
        }
    }
}
