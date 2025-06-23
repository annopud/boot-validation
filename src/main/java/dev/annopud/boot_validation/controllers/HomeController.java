package dev.annopud.boot_validation.controllers;

import dev.annopud.boot_validation.model.PersonForm;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String home() {
        return "Hello";
    }

    @PostMapping("")
    public String postHome(@Valid @RequestBody PersonForm form,
                           BindingResult bindingResult) {
        return "Hello from POST validated request";
    }
}
