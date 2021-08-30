package Users;

import CustomExceptions.LowestSallary;
import CustomExceptions.NoWorkHours;
import Projects.Project;

/**
 *  Trieda zamestnanca implementuje interface Sallary ktory predpisuje metody pre operaciu nad vyplatou zamestnanca
 */

public class Employee implements Sallary, java.io.Serializable{

    protected String username;
    protected String password;
    private long time_logged_in;
    private Project assigned_project;

    private double per_hour = Sallary.money_per_hour;


    public Employee(String username, String password){

        this.username = username;
        this.password = password;

    }

    /* Pretazenie pre pripad ze vytvarame zamestnavatela ktory bude specialny typ zamestnanca bude mat pravo vytvarat
     novych zamestnancov a volat funkcie GetRaise, GetPaycut, GetSallary */
    public Employee() {

    }

    /**
     *  Metoda ktora ma nastarosti nastavovanie parametra ktory urcuje odpracovany cas zamestnanca
     */
    // Nastavi prihlaseny cas toto bude simulovat odrobeny cas
    public void SetTimeLoggedIn(long time){
        this.time_logged_in = time + this.time_logged_in;
    }

    /**
     * Getter metoda na zistenie hesla zamestnanca
     */
    // Pre porovnanie hesla pri logine neni to bezpecne
    public String GetPassword(){
        return this.password;
    }

    /**
     * Getter metoda na zistenie mena zamestnanca
     */
    // Pre porovnanie mena pri logine
    public String GetUsername(){
        return this.username;
    }

    /**
     * Getter metoda na zistenie doby prihlasenia zamestnanca
     */
    // Vrati cas ktory bol uzivatel prihlaseny
    public Long GetTime(){

        return this.time_logged_in;
    }

    /**
     * Getter metoda na zistenie priradeneho projektu zamestnanca
     */
    // Vrati assignuty project
    public Project GetAssignedProject(){
        return this.assigned_project;
    }

    /**
     * Setter metoda ktora nastavi zamestnancovi novo priradeny projekt
     */
    // Nastavi projekt objektu na prideleny projekt
    public void SetProject(Project project){
        this.assigned_project = project;
    }

    @Override
    public String GetRaise(){
        per_hour += 1.5;
        return "Sallary of " + this.username + " has been raised by 1.5 euros";
    }

    @Override
    public String GetPaycut() throws LowestSallary {
        if(per_hour > 1.5){
            per_hour -= 1.5;
            return "Sallary of " + this.username + " has been lowered by 1.5 euros";
        }
        else {
            throw new LowestSallary("Sallary of " + this.username + " can't be lowered");
        }
    }

    @Override
    public String GetSallary() {

        return String.valueOf(this.per_hour * this.time_logged_in);
    }

    // This will only return the actual hourly pay for the user
    @Override
    public String GetHourlyPay(){

        return String.valueOf(this.per_hour);
    }

    @Override
    public String PaySallary() throws NoWorkHours{

        String temp;

        if(time_logged_in > 0){
            temp = this.username + " got paid: " + GetSallary() + "€";
            time_logged_in = 0;
            return temp;
        }
        else {
            throw new NoWorkHours(this.username + " doesn't have any work hours!");
        }

    }
}
