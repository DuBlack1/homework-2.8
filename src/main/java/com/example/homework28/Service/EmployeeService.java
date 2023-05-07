package com.example.homework28.Service;

public interface EmployeeService {
    String toStringEmployee();

    //    Найти сотрудника
    String findEmployee(String firstName, String lastName, int department, double salary);
    //   добавить сотрудника
    String addNewEmployee(String firstName, String lastName, int department, double salary);

    //  удалить сотрудника
    String deleteEmployee(String firstName, String lastName, int department, double salary);
}