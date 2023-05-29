package com.example.homework28.Service;

import com.example.homework28.Class.Employee;

import java.util.List;

public interface EmployeeService {
    String toStringEmployee();

    //    Найти сотрудника
    Employee findEmployee(String firstName, String lastName);
    //   добавить сотрудника
    Employee addNewEmployee(String firstName, String lastName, int department, double salary);

    //  удалить сотрудника
    Employee deleteEmployee(String firstName, String lastName);

    List<Employee> getAll();
}