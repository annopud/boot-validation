package dev.annopud.boot_validation.controllers;

import dev.annopud.boot_validation.model.PersonForm;
import dev.annopud.boot_validation.service.BeanValidatorApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation/bean-validator")
@RequiredArgsConstructor
public class BeanValidatorApiController {

    private final BeanValidatorApiService beanValidatorApiService;

    @PostMapping("")
    public String postHome(@RequestBody PersonForm form) {
        return beanValidatorApiService.testValidate(form);
    }

}
