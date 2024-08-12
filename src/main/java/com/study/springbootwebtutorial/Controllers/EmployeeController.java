package com.study.springbootwebtutorial.Controllers;

import com.study.springbootwebtutorial.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getAllEmployees() {
//        return "abcd";
//    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Anuj", "sam@gmail.com", 18);
    }

    //http://localhost:8082/employees?inputAge=2&sortBy=age
    @GetMapping
    public String getEmployees(@RequestParam(required = false, name= "inputAge") Integer age,
                                    @RequestParam(required = false) String sortBy){
        return "Hi age "+ age + "  "+ sortBy;
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;
//        return
    }

    @PutMapping
    public String updateEmployeeId(){
        return "update";
    }
}
