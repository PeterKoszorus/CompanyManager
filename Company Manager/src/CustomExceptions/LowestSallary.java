package CustomExceptions;

/**
 * Trieda pre custom exception ktora je vyhadzovana ked uz zamestnanec ma najnizsi mozny plat co je 1 €
 */
public class LowestSallary extends Exception {

    public LowestSallary(String msg){
        super(msg);
    }
}
