package com.example.homework28.Exception;

public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String s) {
        super(s);
    }
}
