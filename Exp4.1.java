// Experiment 4.1: Employee Management System

import java.util.*;
class Employee {
    int id;
    String name;
    double salary;
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}
public class EmployeeManagementSystem {
    private static List<Employee> employees = new ArrayList<>();
    public static void addEmployee(int id, String name, double salary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added: ID=" + id + ", Name=" + name + ", Salary=" + salary);
    }
    public static void updateEmployee(int id, double newSalary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                emp.salary = newSalary;
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }
    public static void removeEmployee(int id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.id == id) {
                iterator.remove();
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }
    public static void searchEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Employee Found: " + emp);
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }
    public static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
    public static void main(String[] args) {
        displayEmployees();
        addEmployee(101, "Anish", 50000);
        addEmployee(102, "Bobby", 60000);
        updateEmployee(101, 55000);
        searchEmployee(102);
        removeEmployee(101);
        displayEmployees();
        addEmployee(102, "Charlie", 70000);
    }
}
