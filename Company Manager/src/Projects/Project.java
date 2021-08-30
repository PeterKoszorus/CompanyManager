package Projects;

import Users.Employee;

import java.util.ArrayList;

/**
 * Trieda ktora popisuje objekt projektu
 */

public class Project implements java.io.Serializable {

    private final String name;
    private final int capacity;
    private final ArrayList<Employee> assigned_emp = new ArrayList<>();

    // Constructor for project class
    public Project(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    /**
     * Getter metoda ktora vrati capacitu projektu
     */
    // Method to get the capacity of project class
    public int GetCapacity(){
        return this.capacity;
    }

    /**
     * Getter metoda ktora vrati nazov projektu
     */
    // Method to get the name of project class
    public String GetName(){
        return this.name;
    }

    /**
     * Setter metoda ktora priradi zamestnanca do zoznamu zamestnancov projektu
     */
    // Method to assign employee to project
    public String AssignEmployee (Employee employee){
        assigned_emp.add(employee);
        return "Employee: " + employee.GetUsername() + " has been assigned to project: " + GetName();
    }

    /**
     * Getter metoda ktora vrati zoznam priradenych zamestnancov
     */
    // Getter method to get the assigned_emp ArrayList
    public ArrayList<Employee> GetAssignedEmployees(){
        return this.assigned_emp;
    }

    /**
     * Setter metoda ktora odradi zamestnanca zo zozname priradenych zamestnancov
     */
    // Method to unassign employee from project
    public void UnassignEmployee(Employee employee){
        assigned_emp.remove(employee);
    }
}
