package com.example.homework28.Controller;

import com.example.homework28.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String hello(){
        return "Приветствуем";
    }

    //    Чтение сотрудников
    @GetMapping(path = "/toString")
    public String toStringEmployee(){
        return employeeService.toStringEmployee();
    }

    //    Найти сотрудника
//    http://localhost:8080/employee/find?firstName=David&lastName=Hkkf
    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam(name = "firstName") String firstName,
                               @RequestParam(name = "lastName") String lastName,
                               @RequestParam(name = "department") int department,
                               @RequestParam(name = "salary") double salary){
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    //    добавить сотрудника
    //    http://localhost:8080/employee/add?firstName=David&lastName=Hkkf&department=4&salary=40
    //    http://localhost:8080/employee/add?firstName=Davna&lastName=Nok&department=4&salary=50
//    http://localhost:8080/employee/add?firstName=Cet&lastName=Has&department=3&salary=40
    @GetMapping(path = "/add")
    public String addNewEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName,
                                 @RequestParam(name = "department") int department,
                                 @RequestParam(name = "salary") double salary){
        return employeeService.addNewEmployee(firstName, lastName, department, salary);
    }

    //    удалить сотрудника
//    http://localhost:8080/employee/remove?firstName=David&lastName=Hkkf
    @GetMapping(path = "/remove")
    public String deleteEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName,
                                 @RequestParam(name = "department") int department,
                                 @RequestParam(name = "salary") double salary){
        return employeeService.deleteEmployee(firstName, lastName, department, salary);
    }
}
