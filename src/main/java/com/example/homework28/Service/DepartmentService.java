package com.example.homework28.Service;


import com.example.homework28.Class.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    //    Изменить отдел сотрудника
    void changeDepartment(Employee employee, int newDepartment);
    //    Получить Ф. И. О. всех сотрудников по отделам
    Map<Integer, List<Employee>> getAllEmployeesByDepartment();
    //    Найти сотрудника с минимальной зарплатой из определенного отдела
   Employee minSalaryDepartment(int department);
    //    Найти сотрудника с максимальной зарплатой из определенного отдела
    Employee maxSalaryDepartment(int department);
    //    Напечатать всех сотрудников отдела
    List<Employee> displayFullNameDepartment(int department);
}
