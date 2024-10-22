package com.definingClasses.exercise.companyRoster;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Company company = new Company();

        IntStream.range(0, Integer.parseInt(scanner.nextLine())).mapToObj(line -> scanner.nextLine())
                .map(line -> line.split(" ")).map(info -> getEmployee(info)).forEach(e -> {
                    company.addEmployee(e);
                });

        Department highestAverageSalaryDepartment = company.getHighestAverageSalaryDepartment();

        System.out.printf("Highest Average Salary: %s%n", highestAverageSalaryDepartment.getName());
        System.out.println(highestAverageSalaryDepartment.getEmployeesInfo());
    }

    private static Employee getEmployee(String[] info) {
        if (info.length == 4) {
            return new Employee(info[0], Double.parseDouble(info[1]), info[2], info[3]);
        } else if (info.length == 5 && isNumber(info[4])) {
            return new Employee(info[0], Double.parseDouble(info[1]), info[2], info[3], Integer.parseInt(info[4]));
        } else if (info.length == 5) {
            return new Employee(info[0], Double.parseDouble(info[1]), info[2], info[3], info[4]);
        } else if (info.length == 6) {
            return new Employee(info[0], Double.parseDouble(info[1]), info[2], info[3], info[4], Integer.parseInt(info[5]));
        }

        return null;
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
