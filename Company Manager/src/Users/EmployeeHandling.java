package Users;

import CustomExceptions.AlreadyRegistered;
import CustomExceptions.IncorectPassword;
import CustomExceptions.IncorrectFormatOfPassword;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tato trieda obsahuje metody ktore vykonavaju operacie nad triedou Employee
*/
public class EmployeeHandling {

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final String filename = "employees.ser";


    /**
     * Pri konstruktore dochada k deserializacii dat zo zoserializovaneho suboru, je tu taktiez odkomentovana moznost
     * vytvorenia admina v pripade ze by doslo ku poskodeniu zoserializovanych dat
     */
    public EmployeeHandling(){

        //Vytvorenie pola objektov o velkosti poctu zamestnancov
        /*//In case you get locked out of the app just wipe clean the employees.ser remove this comment and login and
           //logout before another run comment this out again
            CreateAdmin();
        */

        Deserialization();

    }

    // Funkcia na vytvorenie admina moze byt zavolana presne raz a to pri konstruktore
    private void CreateAdmin(){

        // Employer is a singletone class so I can create only one instance of this object
        employees.add(Employer.getInstance());

    }

    // Prejde cez list Employees a ak existuje uzivatel s rovnakym menom tak vrati objekt s rovnakym menom
    private Employee UsernameComparison(String username) {

        for (Employee employee : employees) {

            if (employee.GetUsername().equals(username)) {
                return employee;
            }
        }

        return null;
    }

    /**
     * Wraper metoda na vyuzivanie UsernameComparison co je private metoda, vrati adresu objektu s menom
     * zadanym ako parameter metody
     */

    // Wraper function for UsernameComparison
    public Employee GetUserByUsername(String username){
        return UsernameComparison(username);
    }

    /**
     *
     * Porovna heslo zamestnanca ak sa zhoduje nevrati nic v pripade ze by sa heslo zamestnanca nezhodovalo so
     * zadanym heslom metoda by vyhodila custom exception IncorectPassword
     *
     */
    // Porovna heslo zadane v GUI s atributom password objektu
    private void PasswordComparison(Employee employee, String password) throws IncorectPassword {


        if(employee.GetPassword().equals(password)){
        }
        else{
            throw new IncorectPassword();
        }

    }

    /**
     *
     * Metoda ktora vola PasswordComparison a UsernameComparison a sluzi na overenie prihlasvacych udajov z udajmi
     * ktore su na vstupe ak pouzivatel zada pri loggine meno kotre neni v zozname zamestnancov dostane NullPointerException
     * ak pouzivatel zada spravne meno ale heslo nie tak dostane custom exception IncorectPassword. Metoda vracia
     * adresu objektu zamestnanca ktory sa prihlasil
     *
     */
    // Funkcia ktora logne uzivatela a vrati jeho meno aby sa s nim dalo dalej pracovat
    public Employee Login(String username, String password) throws IncorectPassword {

        Employee curent = UsernameComparison(username);

        assert curent != null;

        PasswordComparison(curent, password);

        return curent;
    }

    /**
     *
     * Metoda ktora ma nastarosti registrovanie noveho pouzivatela. Metoda kontrolu ci uzivatel s rovnakym menom
     * uz nie je prihlaseny ak by takato situacia nastala dojde k vyhodeniu custom exception AlreadyRegistered.
     * Taktiez pre heslo je tu zadany regex pattern ktory heslo musi splnat aby sa bolo uznane (aspon jedno lower case
     * pismeno, ziadne medzere a aspon jedno cislo) ak regex pattern neni splneny dojde k vyhodeniu custom exception
     * IncorrectFormatOfPassword. Metoda vracia adresu objektu uzivatela ktory sa prave zaregistroval
     */
    // Method for registration of new user
    public Employee Register(String username, String password) throws AlreadyRegistered, IncorrectFormatOfPassword {

        Pattern expresion;
        // At least one digit, at least one lower case letter, no whitspaces allowed
        expresion = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$)");

        Matcher to_check = expresion.matcher(password);

        if(UsernameComparison(username) != null){
            throw new AlreadyRegistered();
        }

        if(!to_check.find()){
            throw new IncorrectFormatOfPassword();
        }

        employees.add(new Employee(username, password));

        return employees.get(employees.size() - 1);

    }

    /**
     * Metoda ktora serializuje zoznam zamestnancov
     */

    public void Serialization(){

        // Change the catch to be displayed in GUI, or at least thats the way ist should be done in normal situation

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream obj = new ObjectOutputStream(file);

            for (Employee employee : employees){
                obj.writeObject(employee);
            }

            obj.close();
            file.close();

        }
        catch (IOException ex){
            System.out.println("IOException is caught");
        }

    }

    /**
     * Metoda ktora deserializuje subor s zoznamom zamestnancov
     */
    public void Deserialization(){

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream obj = new ObjectInputStream(file);

            while (file.available() > 0){
                Employee object = (Employee) obj.readObject();
                employees.add(object);
            }

            }
            catch(IOException ex){
                System.out.println("IOExeption is caught");

            }
            catch(ClassNotFoundException ex){
                System.out.println("ClassNotFoundException is caught");
            }
    }

    /**
     * Metoda ktora odhlasi uzivatela z projektu jedna sa o ovedriven metodu tato je volana z Triedy SelectedProject
     */
    // Method unassign employee from project, calling from the SlectedProject
    public void UnassignEmployee(String username){

        Employee employee = GetUserByUsername(username);

        employee.GetAssignedProject().UnassignEmployee(employee);
        employee.SetProject(null);

    }

    /**
     * Metoda ktora odhlasi uzivatela z projektu jedna sa o ovedriven metodu tato je volana z metody DeleteUser
     */
    // Method unassign employee from project, calling when deleting user
    public void UnassignEmployee(Employee employee){

        employee.GetAssignedProject().UnassignEmployee(employee);
        employee.SetProject(null);

    }

    /**
     * Metoda ktora ma nastarosti odstranenie zamestnanca zo zoznamu zamestnancov
     */
    // Method which deletes the user which was selected in the gui part
    public void DeleteUser(int index){

        //System.out.println("Will be removing " + employees.get(index + 1).GetUsername());

        // If statement to protect the deletion of Admin
        if(employees.get(index).GetUsername().equals("Admin")) {
            return;
        }

        // Removes the employee from project if he is assigned to some
        if(employees.get(index).GetAssignedProject() != null){
            UnassignEmployee(employees.get(index));
        }

        employees.remove(index);
    }

    /**
     * Pomocna metoda pre GUI cast ktora do observablelistu nahadze stringy s potrebnymi info ktore nasledne vypise do
     * ListView. Metoda vracia observablelist
     */
    // Method for to get all the stuff to output in the SelectedUser class
    public ObservableList<String> GetUserInfo(String username){

        ObservableList<String> sallary_info = FXCollections.observableArrayList();

        Employee act_user = GetUserByUsername(username);

        sallary_info.add(act_user.GetUsername() + " makes: " + act_user.GetHourlyPay() + "€ per hour");
        sallary_info.add(act_user.GetUsername() + " worked for: " + act_user.GetTime() + " hours");
        sallary_info.add(act_user.GetUsername() + "'s sallary for this month is: " + act_user.GetSallary() + "€");

        if(act_user.GetAssignedProject() == null){
            sallary_info.add(act_user.GetUsername() + " isn't assigned to any project");
        }
        else {
            sallary_info.add(act_user.GetUsername() + " is assigned to: " + act_user.GetAssignedProject().GetName());
        }

        return sallary_info;
    }

    /**
     * Getter metoda ktora vrati zoznam zamestnancov
     */
    // Method which passes the employess observavble list
    public ObservableList<Employee> GetEmployees(){
        return this.employees;
    }

    /**
     * Metoda ktora vytvori zoznam vsetkych zamestnancov ktory nemaju priradeny ziadny projekt, vyuzitie lambda vyrazu
     */
    // Method to get all unassigned employees without admin, using lambda expression for iterating throug list
    public ObservableList<String> GetUnassignedEmp(){

        ObservableList<String> unassigned_emp = FXCollections.observableArrayList();

        employees.forEach(employee -> {

            if(employee.GetAssignedProject() == null && !employee.GetUsername().equals("Admin")){
                unassigned_emp.add(employee.GetUsername());
            }
        });

        return unassigned_emp;
    }

}
