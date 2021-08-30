package CustomExceptions;

/**
 * Trieda pre custom exception ktora je vyhadzovana ked je projekt uz zaplneny a neni v nom miesto pre dalsieho
 * zamestnanca
 */
public class InsufficientCapacity extends Exception {

    public InsufficientCapacity(String msg){
        super(msg);
    }
}
