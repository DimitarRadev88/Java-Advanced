package com.definingClasses.exercise.companyRoster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    private List<Department> departments;

    public Company() {
        this.departments = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        Department department = getDepartment(employee.getDepartment());
        if (department == null) {
            department = new Department(employee.getDepartment());
            departments.add(department);
        }

        department.add(employee);
    }

    private Department getDepartment(String name) {
        return departments.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
    }

    public Department getHighestAverageSalaryDepartment() {
        return departments.stream().max(Comparator.comparingDouble(Department::getAverageSalary)).get();
    }
}
