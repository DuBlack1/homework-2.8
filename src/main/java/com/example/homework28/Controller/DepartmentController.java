package com.example.homework28.Controller;

import com.example.homework28.Class.Employee;
import com.example.homework28.Service.DepartmentService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

//    Возвращать сотрудника с максимальной зарплатой
//    /departments/max-salary?departmentId=5
    @GetMapping(path = "/max-salary")
    public Employee maxSalaryDepartment(@RequestParam(name = "departmentId") int department){
        return departmentService.employeeWithMaxSalaryByDepartment(department);
    }

//    Возвращать сотрудника с минимальной зарплатой
//    /departments/min-salary?departmentId=5
    @GetMapping(path = "/min-salary")
    public Employee minSalaryDepartment(@RequestParam(name = "departmentId") int department){
        return departmentService.employeeWithMinSalaryByDepartment(department);
    }

//    Возвращать всех сотрудников с разделением по отделам
//     /departments/all
    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(){
        return departmentService.getAllEmployeesByDepartment();
    }

//     возвращает список сотрудников по департаменту.
    @GetMapping("/{id}/employees")
    public List<Employee> employeesByDepartment(@PathVariable("id") int department){
        return departmentService.employeesByDepartment(department);
    }
//    возвращает сумму зарплат по департаменту.
    @GetMapping("/{id}/salary/sum")
    public double salaryAmount(@PathVariable("id") int department){
        return departmentService.salaryAmountByDepartment(department);
    }
//    возвращает максимальную зарплату по департаменту.
    @GetMapping("/{id}/salary/max")
    public double maxSalary(@PathVariable("id") int department){
        return departmentService.maxSalaryByDepartment(department);
    }
//    возвращает минимальную зарплату по департаменту.
    @GetMapping("/{id}/salary/min")
    public double minSalary(@PathVariable("id") int department){
        return departmentService.minSalaryByDepartment(department);
    }
}
