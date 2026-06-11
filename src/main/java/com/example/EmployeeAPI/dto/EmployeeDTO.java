package com.example.EmployeeAPI.dto;

import com.example.EmployeeAPI.annotations.EmpoyeeRoleValidationAnnotation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Name can not be blank")
    @Size(min=3, max = 13, message = "Number of nameshould be rannge between[3,13]")
    private String name;
    @Email
    @NotBlank(message = "Email name  can not be blank")
    private String email;
    @Min(value = 18,message = "Minimum age should be [18]")
    @Max(value = 80,message = "MMaximum age should be 80")
    @Positive(message = "Age can not be negative")
    private Integer age;
    @EmpoyeeRoleValidationAnnotation
    @NotBlank
    @Positive(message = "salary can not be negative")
    @Digits(integer = 6,fraction = 2,message = "salary can not be more than XXXXXX.XX")
    private Double salary;
    @PastOrPresent(message = "date of joining can not be past")
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private boolean isActive;

}
