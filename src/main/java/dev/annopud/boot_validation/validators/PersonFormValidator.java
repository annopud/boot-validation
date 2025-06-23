package dev.annopud.boot_validation.validators;

import dev.annopud.boot_validation.model.PersonFormNoAnnotation;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonFormValidator implements Validator, MessageSourceAware {

    private MessageSource messageSource;

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(@NonNull Class clazz) {
        return PersonFormNoAnnotation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {

        PersonFormNoAnnotation form = (PersonFormNoAnnotation) target;

        if ((form.getName() != null && !form.getName().isEmpty() && form.getAge() == null)) {
            Object[] arguments = {"111", "222"};
            String message = messageSource.getMessage(
                "age.required.when.name",
                arguments,
                LocaleContextHolder.getLocale()
            );
            errors.rejectValue("age", "age.required.when.name", arguments, message);
        }
    }

}
