package dev.annopud.boot_validation.controllers;

import dev.annopud.boot_validation.model.PersonFormNoAnnotation;
import dev.annopud.boot_validation.model.UserRequest;
import dev.annopud.boot_validation.validators.PersonFormValidator;
import dev.annopud.boot_validation.validators.UserRequestValidator;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/validation")
public class SpringInterfaceValidationController {

    private final PersonFormValidator personFormValidator;
    private final UserRequestValidator userRequestValidator;

    public SpringInterfaceValidationController(
        PersonFormValidator personFormValidator,
        UserRequestValidator userRequestValidator
    ) {
        this.personFormValidator = personFormValidator;
        this.userRequestValidator = userRequestValidator;
    }

    @InitBinder({"personFormNoAnnotation"})
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personFormValidator);
    }

    @InitBinder("userRequest")
    protected void initUserRequestBinder(WebDataBinder binder) {
        binder.setValidator(userRequestValidator);
    }

    @PostMapping("")
    public String postHome(@Valid @RequestBody PersonFormNoAnnotation form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Validation errors occurred: " + bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining());

        }
        return "Hello from POST validated request";
    }

    @PostMapping("/user-request")
    public String userRequest(@Valid @RequestBody UserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Validation errors occurred: " + bindingResult.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining());

        }
        return "Hello from POST validated request";
    }
}
