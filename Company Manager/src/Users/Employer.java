package Users;

/**
 * Singleton trieda ktora je vytvorena iba raz a je to vlastne zamestnavatel ktory ked sa prihlasi moze vykonavat operacie
 * nad zamestnancami
 */
class Employer extends Employee{

    private static Employer single_instance = null;

    Employer() {

        this.username = "Admin";
        this.password = "admin";

    }

    public static Employer getInstance(){

        if(single_instance == null){
          single_instance = new Employer();
        }

        return single_instance;
    }

}
