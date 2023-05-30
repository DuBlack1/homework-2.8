package com.example.homework28.Service;

import com.example.homework28.Class.Employee;
import com.example.homework28.Exception.EmployeeAlreadyAddedException;
import com.example.homework28.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String,Employee> EmployeeBook = new HashMap<>();

    private final ValidatorService validatorService;

    public EmployeeServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    private String getKey(String name, String surname){ return name + "|" + surname;}

    //    Список сотрудников
    @Override
    public String toStringEmployee(){
        return EmployeeBook.toString();
    }

    //    Найти сотрудника
    @Override
    public Employee findEmployee(String firstName, String lastName){
        String key = getKey(firstName, lastName);
        if (!EmployeeBook.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return EmployeeBook.get(key);
    }

    // Добавить сотрудника
    @Override
    public Employee addNewEmployee(String firstName, String lastName, int department, double salary){
        Employee forAdd = new Employee(
                validatorService.validateFirstName(firstName),
                validatorService.validateLastName(lastName),
                department,
                salary);
        String key = getKey( firstName, lastName);
            if (EmployeeBook.containsKey(key)) {
                throw new EmployeeAlreadyAddedException(" такой сотрудник уже есть ");
            }
            else {
                EmployeeBook.put(key, forAdd);
                return forAdd;
            }
    }

    //    Удалить сотрудника
    public Employee deleteEmployee(String firstName, String lastName){
        String key = getKey( firstName, lastName);
        if (!EmployeeBook.containsKey(key)) {
            throw new EmployeeNotFoundException(" Сотрудник не найден ");
        }
        return EmployeeBook.remove(key);
    }

    public List<Employee> getAll(){
        return new ArrayList<>(EmployeeBook.values());
    }


}