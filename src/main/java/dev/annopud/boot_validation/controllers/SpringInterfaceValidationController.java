package dev.annopud.boot_validation.controllers;

import dev.annopud.boot_validation.model.PersonFormNoAnnotation;
import dev.annopud.boot_validation.validators.PersonFormValidator;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class SpringInterfaceValidationController {

    private final PersonFormValidator personFormValidator;

    public SpringInterfaceValidationController(PersonFormValidator personFormValidator) {
        this.personFormValidator = personFormValidator;
    }

    @InitBinder({"personFormNoAnnotation"})
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(personFormValidator);
    }

    @PostMapping("")
    public String postHome(@Valid @RequestBody PersonFormNoAnnotation form, BindingResult bindingResult) {
        return "Hello from POST validated request";
    }
}
