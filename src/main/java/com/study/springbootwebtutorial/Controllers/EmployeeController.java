package com.study.springbootwebtutorial.Controllers;

import com.study.springbootwebtutorial.dto.EmployeeDTO;
import com.study.springbootwebtutorial.entities.EmployeeEntity;
import com.study.springbootwebtutorial.repositories.EmployeeRepository;
import com.study.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


//    @GetMapping(path = "/getSecretMessage")
//    public String getAllEmployees() {
//        return "abcd";
//    }

//    @GetMapping("/{employeeId}")
//    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
//        return new EmployeeDTO(employeeId, "Anuj", "sam@gmail.com", 18);
//    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return employeeService.getEmployeeById(id);
    }

    //http://localhost:8082/employees?inputAge=2&sortBy=age
//    @GetMapping
//    public String getEmployees(@RequestParam(required = false, name= "inputAge") Integer age,
//                                    @RequestParam(required = false) String sortBy){
//        return "Hi age "+ age + "  "+ sortBy;
//    }

    @GetMapping
    public List<EmployeeEntity> getEmployees(@RequestParam(required = false, name= "inputAge") Integer age,
                                             @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
//        inputEmployee.setId(100L);
//        return inputEmployee;
//    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeId(){
        return "update";
    }
}
