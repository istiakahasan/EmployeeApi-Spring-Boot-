package com.example.EmployeeAPI.service;

import com.example.EmployeeAPI.dto.EmployeeDTO;
import com.example.EmployeeAPI.entity.EmployeeEntity;
import com.example.EmployeeAPI.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> employeeById(Long Id) {
    return employeeRepository.findById(Id).map(employeeEntity->mapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> mapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee) {
    EmployeeEntity toSaveEntity=mapper.map(inputEmployee,EmployeeEntity.class);
    EmployeeEntity savedEmployee=employeeRepository.save(toSaveEntity);
    return mapper.map(savedEmployee,EmployeeDTO.class);
    }


    public EmployeeDTO updateById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity=mapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEntity=employeeRepository.save(employeeEntity);
        return mapper.map(savedEntity,EmployeeDTO.class);
    }
    public boolean isExist(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }
    public boolean deleteById(Long employeeId) {
    boolean isExisted=isExist(employeeId);
    if(!isExisted)return false;
    employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, Map<String, Object> updates) {
       boolean existedId=isExist(employeeId);
       EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();
       updates.forEach((field,value)->{
          Field fieldTobeUpdated= ReflectionUtils.findField(EmployeeEntity.class,field);
          fieldTobeUpdated.setAccessible(true);
          ReflectionUtils.setField(fieldTobeUpdated,EmployeeEntity.class,field);
       });
        return mapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}
