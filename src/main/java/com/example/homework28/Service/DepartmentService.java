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
   Employee employeeWithMinSalaryByDepartment(int department);
    //    возвращает минимальную зарплату по департаменту
    double minSalaryByDepartment(int department);
    //    Найти сотрудника с максимальной зарплатой из определенного отдела
    Employee employeeWithMaxSalaryByDepartment(int department);
    //    возвращает максимальную зарплату по департаменту
    double maxSalaryByDepartment(int department);
    //    возвращает список сотрудников по департаменту
    List<Employee> employeesByDepartment(int department);
    //    возвращает сумму зарплат по департаменту
    double salaryAmountByDepartment(int department);
}
