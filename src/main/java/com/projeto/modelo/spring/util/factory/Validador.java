package com.projeto.modelo.spring.util.factory;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

@Service
public class Validador {

    public boolean validar(Object paraValidar) {

        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = null;
        violations = validator.validate(paraValidar);

        for (ConstraintViolation<Object> violation : violations) {
            System.out.println(violation.getMessage());
            return false;
        }

        return true;
    }
}
