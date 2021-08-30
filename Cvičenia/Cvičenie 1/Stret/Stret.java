package Stret;

public class Stret {
    static void stret(Obor obor, Rytier rytier) {
        rytier.utok(obor);
    }

    public static void main(String[] args) {
        Rytier[] r = new Rytier[100];
        Obor[] o = new Obor[100];
/*

		//stret 100 rytierov a 100 obrov: inicializacia
		for (int i = 0; i < 100; i++) {
			r[i] = new Rytier();
			o[i] = new Obor();
			r[i].energia = 40;
			o[i].energia = 50;
		}
*/
        // pribudol dalsi druh obra: ZlyObor
        // toto je len inicializacia poli obrov a rytierov tak, aby sa prejavili rozne kombinacie
        for (int i = 0; i < 20; i++) {
            r[i] = new Rytier();
            r[i].energia = 40;
            o[i] = new ZlyObor();
            o[i].energia = 50;
            o[i].hladny = true;
        }

        for (int i = 20; i < 30; i++) {
            r[i] = new Rytier();
            r[i].energia = 40;
            o[i] = new ZlyObor();
            o[i].energia = 50;
        }

        // pridane pre dobreho obra
        for (int i = 30; i < 40; i++) {
            r[i] = new Rytier();
            r[i].energia = 40;
            o[i] = new DobryObor();
            o[i].energia = 50;
        }

        for (int i = 40; i < 90; i++) {
            r[i] = new Rytier();
            r[i].energia = 40;
            o[i] = new Obor();
            o[i].energia = 50;
        }

        for (int i = 90; i < 100; i++) {
            r[i] = new SilnyRytier();
            r[i].energia = 40;
            o[i] = new Obor();
            o[i].energia = 50;
        }

        // slucka stretu s vypisom
        for (int i = 0; i < 100; i++) {
            stret(o[i], r[i]);

            System.out.println(i + ":"
                    + "rytier " + r[i].energia + " / "
                    + "obor " + o[i].energia);
        }
    }
}
