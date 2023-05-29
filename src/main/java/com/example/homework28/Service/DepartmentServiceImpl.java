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
    public Employee employeeWithMinSalaryByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==departmentId)
                .min((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
                .orElse(null);
    }

    //    возвращает минимальную зарплату по департаменту
    @Override
    public double minSalaryByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .map(Employee::getSalary)
                .min((o1, o2) -> (int) (o1 - o2))
                .get();
    }

    //    Найти сотрудника с максимальной зарплатой из определенного отдела
    public Employee employeeWithMaxSalaryByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==departmentId)
                .max((o1, o2) -> (int) (o1.getSalary() - o2.getSalary()))
                .orElse(null);
    }

    //    возвращает максимальную зарплату по департаменту.
    public double maxSalaryByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .map(x -> x.getSalary())
                .max((m, n) -> (int) (m - n))
                .get();
    }

    //     возвращает список сотрудников по департаменту.
    @Override
    public List<Employee> employeesByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .toList();
    }

    //    возвращает сумму зарплат по департаменту.
    public double salaryAmountByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment()==department)
                .map(x -> x.getSalary())
                .reduce(Double.valueOf(0), (a, b) -> a + b);
    }

    //    Получить Ф. И. О. всех сотрудников по отделам
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment(){
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
