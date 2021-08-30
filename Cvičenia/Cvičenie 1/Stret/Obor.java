package Stret;

import com.company.Stret;

public class Obor {
    boolean hladny;
    int energia;

    void odveta(Rytier rytier) {
        if (energia > rytier.energia)
            rytier.energia = (int) (0.9 * rytier.energia);
    }
}
