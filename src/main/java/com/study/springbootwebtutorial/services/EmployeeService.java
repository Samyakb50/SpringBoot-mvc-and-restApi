package com.study.springbootwebtutorial.services;

import com.study.springbootwebtutorial.entities.EmployeeEntity;
import com.study.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeEntity> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}
