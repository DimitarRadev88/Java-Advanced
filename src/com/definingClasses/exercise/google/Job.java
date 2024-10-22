package com.definingClasses.exercise.google;

public class Job {
    private Company company;
    private Department department;
    private double salary;

    public Job(Company company, Department department, double salary) {
        this.company = company;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.company, this.department, this.salary);
    }
}
