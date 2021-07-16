package com.projeto.modelo.spring.util.factory;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

@Component
@Log4j2
public class Validador {

    public boolean validar(Object paraValidar) {

        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = null;
        violations = validator.validate(paraValidar);

        for (ConstraintViolation<Object> violation : violations) {
            log.info(violation.getMessage());
            return false;
        }

        return true;
    }
}
