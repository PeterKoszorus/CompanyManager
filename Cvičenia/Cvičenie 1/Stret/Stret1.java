package Stret;

import com.company.Stret;

public class Stret1 {

    static void stret(Obor obor, Rytier rytier) {
        rytier.utok(obor);
    }

    public static void main(String[] args) {
        Rytier r = new Rytier();
        Obor o = new Obor();
        r.energia = 100;
        o.energia = 120;

        Stret1.stret(o, r);

        System.out.println("rytier " +
                r.energia + " / " + "obor " +
                o.energia);
    }
}
