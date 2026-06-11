package com.example.EmployeeAPI.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeValidatior implements ConstraintValidator<EmpoyeeRoleValidationAnnotation,String> {
    @Override
    public boolean isValid(String inputrole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> role=List.of("USER,ADMINN");
        return role.contains(inputrole);
    }
}
