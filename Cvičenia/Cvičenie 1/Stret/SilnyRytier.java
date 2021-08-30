package Stret;

// ak je rytier silny zabie obra na jednu ranu a obor nema sancu na odvetu

import com.company.Stret;

public class SilnyRytier extends Rytier {

    void utok(Obor obor) {
        obor.energia = (int) (0 * obor.energia); // obrovy da insta kill
        System.out.println(this);
    }
}
