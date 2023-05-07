package com.example.homework28.Controller;

import com.example.homework28.Class.Employee;
import com.example.homework28.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

//    Возвращать сотрудника с максимальной зарплатой
//    /departments/max-salary?departmentId=5
    @GetMapping(path = "/max-salary")
    public Employee maxSalaryDepartment(@RequestParam(name = "departmentId") int department){
        return departmentService.maxSalaryDepartment(department);
    }

//    Возвращать сотрудника с минимальной зарплатой
//    /departments/min-salary?departmentId=5
    @GetMapping(path = "/min-salary")
    public Employee minSalaryDepartment(@RequestParam(name = "departmentId") int department){
        return departmentService.minSalaryDepartment(department);
    }

//    Возвращать всех сотрудников по отделу.
//    /departments/all?departmentId=5
    @GetMapping(path = "/all", params = "departmentId")
    public List<Employee> displayFullNameDepartment(@RequestParam(name = "departmentId") int department){
        return departmentService.displayFullNameDepartment(department);
    }


//    Возвращать всех сотрудников с разделением по отделам
//     /departments/all
    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(){
        return departmentService.getAllEmployeesByDepartment();
    }
}
