package Users;

import CustomExceptions.LowestSallary;
import CustomExceptions.NoWorkHours;

/**
 *  Interface ktory ma preddefinovane metody a parameter na riadenie vyplaty zamestnanca
 */

public interface Sallary {

    double money_per_hour = 7;

    /**
     * Metoda ktora zvysi hodnivu mzdu zamestnancovi
     */
    String GetRaise();

    /**
     * Metoda ktora znizi hodinovu mzdu zamestnancovi. Hadze custom exception ked uz hodinova mzda nemoze byt znizena
     */
    String GetPaycut() throws LowestSallary;

    /**
     * Vrati celkovu vyplatu zamestnanca vynasobi pocet odpracovanych hodin s hodinovou mzdou
     */
    String GetSallary();

    /**
     * Getter metoda ktora vrati hodinovu mzdu
     */
    String GetHourlyPay();

    /**
     * Metoda ktora vyplati zamestnanca a veresetuje sa udaj o odpracovanej dobe
     */
    String PaySallary() throws NoWorkHours;

}
