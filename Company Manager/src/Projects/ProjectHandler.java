package Projects;

import CustomExceptions.IncorectCapacity;
import CustomExceptions.InsufficientCapacity;
import CustomExceptions.ProjectAlreadyExists;
import GUI.eh;
import Users.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * Tato metoda obsahuje metody ktore vykonavaju operacie nad triedou Project
 */

public class ProjectHandler implements eh {

    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final String filename = "projects.ser";

    /**
     * Pri konstruktore dochadza k deserializacii dat zo zoserialivaneho suboru a taktie sa zavola metoda UpdateProjectsList
     * UpdateProjectsList sluzi na spravne na linkovanie adries deserializovanych suborov employees.ser a projects.ser
     */
    // Constructor
    public ProjectHandler(){
        Deserialization();

        projects.forEach(this::UpdateProjectsList);

    }

    /**
     * Metoda ktora vrati adresu objektu projektu ktory bol hladany podla jeho mena
    */
    // Method which find project by project name
    public Project ProjectNameComparisson(String name){

        for(Project project: projects){

            if(project.GetName().equals(name)){
                return project;
            }
        }

        return null;
    }

    /**
     * Metoda ktora vytvori projekt taktiez hadze custom exception ked projekt s rovnakym nazvom uz existuje
     * alebo je zadana capacita v zlom fromate a to ked je hodnota capacity mensia alebo rovna hodnote 0
     */
    // Method for creating new projects
    public Project CreateProject(String name, int capacity) throws ProjectAlreadyExists, IncorectCapacity{

        // Checking if ProjectName is unique
        if(ProjectNameComparisson(name) != null){
            throw new ProjectAlreadyExists();
        }

        // Checking if capacity is more than 0
        if(capacity <= 0){
            throw new IncorectCapacity();
        }

        projects.add(new Project(name, capacity));

        return projects.get(projects.size() - 1);
    }

    /**
     * Getter metoda ktora vrati zoznam vsetkych projektov
     */
    // Method to get the whole projects ObservableList
    public ObservableList<Project> GetProjects(){
        return this.projects;
    }

    // Sets the reference back to functional state
    private void UpdateProjectsList(Project project){

        ArrayList<Employee> temp = new ArrayList<>(project.GetAssignedEmployees());

        project.GetAssignedEmployees().clear();

        temp.forEach(employee -> {

            try {
                AssignEmployee(eh.GetUserByUsername(employee.GetUsername()), project);
            } catch (InsufficientCapacity err) {
                // Nikdy to nechyti tento error
                System.out.println("Insufficient capacity");
            }

        });
    }

    /**
     * Getter metoda ktora zobere zoznam priradenych zamestnancov k porjektu a vrati ju ako ObservableList
     */
    // Method for getting the array list to observable
    public ObservableList<Employee> ProjectInfo(Project slected_project){

        return FXCollections.observableArrayList(slected_project.GetAssignedEmployees());
    }

    /**
     * Metoda ktora priradi zamestnanca k projektu hodi custom exception v pripade ze v projekte uz nie je miesto pre
     * noveho zamestnanca
     */
    // Method which assigns the employee to the project
    public String AssignEmployee(Employee employee, Project project) throws InsufficientCapacity {

        // If there is no space in project
        if(project.GetCapacity() == project.GetAssignedEmployees().size()){
            throw new InsufficientCapacity("There is no room to assign new employee to project: " + project.GetName());
        }

        employee.SetProject(project);

        return project.AssignEmployee(employee);
    }

    /**
     * Metoda ktora vymaze vybrany projekt a vsetkych uzivaetelov ktory boli priradeny k projektu automaticky odradi
     */
    // Method which deletes the project
    public String DeleteProject(Project project){

        project.GetAssignedEmployees().forEach(employee -> employee.SetProject(null));

        projects.remove(project);

        return "The project: " + project.GetName() + " has been successfuly removed";
    }

    /**
     * Metoda na serializaciu zoznamu projektov
     */
    public void Serialization(){

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream obj = new ObjectOutputStream(file);

            for (Project project : projects){
                obj.writeObject(project);
            }

            obj.close();
            file.close();

        }
        catch (IOException ex){
            System.out.println("IOException is caught Serialization");
        }

    }

    /**
     * Metoda ktora deserializuje subor so zoserializovanymi objektami projektu
     */
    public void Deserialization(){

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream obj = new ObjectInputStream(file);

            while (file.available() > 0){
                Project object = (Project) obj.readObject();
                projects.add(object);
            }

        }
        catch(IOException ex){
            System.out.println("IOException is caught Deserialization");

        }
        catch(ClassNotFoundException ex){
            System.out.println("ClassNotFoundException is caught Deserialization");
        }
    }

}
