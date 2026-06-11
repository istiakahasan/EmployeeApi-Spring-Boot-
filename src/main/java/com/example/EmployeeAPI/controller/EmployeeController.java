package com.example.EmployeeAPI.controller;

import com.example.EmployeeAPI.dto.EmployeeDTO;
import com.example.EmployeeAPI.service.EmployeeService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(path="/employees")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> employeeById(@PathVariable(name="employeeId") Long Id){
    Optional<EmployeeDTO> employeeDTO=employeeService.employeeById(Id);
    return employeeDTO.map(employeeDTO1 ->ResponseEntity.ok(employeeDTO1)).orElse(ResponseEntity.notFound().build());

    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false)Integer age, @RequestParam(required = false)String sortBy){
    return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee=employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }
    @PutMapping
    public ResponseEntity<EmployeeDTO> updateById(@RequestBody @Valid  EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.updateById(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long employeeId){
        boolean gotDeleted= employeeService.deleteById(employeeId);
        if(gotDeleted)return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();

    }
    @PatchMapping(path ="/{emplloyeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody @Valid Map<String, Object> updates, @PathVariable Long employeeId){
        EmployeeDTO employeeDTO= employeeService.updateEmployeeById(employeeId,  updates);
        if(employeeDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
