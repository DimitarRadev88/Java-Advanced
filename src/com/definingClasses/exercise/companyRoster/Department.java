package com.definingClasses.exercise.companyRoster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Department {
    private String name;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void add(Employee employee) {
        this.employees.add(employee);
    }
    
    public double getAverageSalary() {
        return employees.stream().mapToDouble(Employee::getSalary).sum() / employees.size();
    }
    
    public String getEmployeesInfo() {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .map(Employee::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String getName() {
        return this.name;
    }
}
