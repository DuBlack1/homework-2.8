package com.example.homework28.Service;

import com.example.homework28.Class.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    //    Изменить отдел сотрудника
    public void changeDepartment(Employee employee, int newDepartment){
        employeeService.getAll().stream()
                .filter(value -> Objects.equals(employee, value))
                .forEach(value -> value.setDepartment(newDepartment));
    }



    //    Найти сотрудника с минимальной зарплатой из определенного отдела
    public Employee minSalaryDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==departmentId)
                .min((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
                .orElse(null);
    }

    //    Найти сотрудника с максимальной зарплатой из определенного отдела
    public Employee maxSalaryDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==departmentId)
                .max((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
                .orElse(null);
    }

    //    Напечатать всех сотрудников отдела
    public List<Employee> displayFullNameDepartment(int departmentId){
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==departmentId)
                .toList();
    }

    //    Получить Ф. И. О. всех сотрудников по отделам
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
