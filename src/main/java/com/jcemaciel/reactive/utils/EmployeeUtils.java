package com.jcemaciel.reactive.utils;

import com.jcemaciel.reactive.model.Employee;

import java.util.List;

public class EmployeeUtils {

    public static List<Employee> getEmployees() {
        return List.of(new Employee(1, "Alfredo"),
                new Employee(2, "José"),
                new Employee(3, "Maria"),
                new Employee(4, "Joana"));
    }
}
