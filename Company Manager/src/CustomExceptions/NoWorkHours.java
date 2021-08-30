package CustomExceptions;

/**
 * Trieda pre custom exception ktora je vyhadzovana ked uzivatel nema odpracovane ziadne hodiny
 */
public class NoWorkHours extends Exception {

    public NoWorkHours(String msg){
        super(msg);
    }
}
