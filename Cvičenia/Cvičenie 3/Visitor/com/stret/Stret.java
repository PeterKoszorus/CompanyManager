package stret;
//import postavy.Energia;
//import postavy.Obor;
//import postavy.Rytier;
//import postavy.StatocnyRytier;
//import postavy.ZlyObor;
import postavy.*;

public class Stret {
	static void stret(Obor obor, Rytier rytier) {
		rytier.utoc(obor);
	}

	static int zistiRozdielEnergii(postavy.Energia bytost1, Energia bytost2) {
		return bytost1.zistiEnergiu() - bytost2.zistiEnergiu();
	}
	
	public static void main(String[] args) {
		Obor[] o = new Obor[100];
		Rytier[] r = new Rytier[100];

		for (int i = 0; i < 20; i++) {
			r[i] = new StatocnyRytier(140, new SvetelnyMec(i));
			o[i] = new ZlyObor();
			o[i].nastav(50, true);
		}

		for (int i = 20; i < 40; i++) {
			r[i] = new Rytier(140, new OcelovyMec(i));
			o[i] = new ZlyObor();
			o[i].nastavEnergiu(50);
		}

		for (int i = 40; i < 50; i++) {
			r[i] = new Rytier(140, new SvetelnyMec(i));
			o[i] = new Obor();
			o[i].nastavEnergiu(50);
		}

		for (int i = 50; i < 60; i++) {
			r[i] = new Rytier(140, new DrevenyMec(i));
			o[i] = new Obor();
			o[i].nastavEnergiu(50);
		}

		for (int i = 60; i < 100; i++) {
			r[i] = new SilnyRytier(140, new SvetelnyMec(i));
			o[i] = new Obor();
			o[i].nastavEnergiu(50);
		}
		
		for (int i = 0; i < 100; i++) {
			stret(o[i], r[i]);
			System.out.println(i + ":" + "rytier " + r[i].zistiEnergiu() +
					" / " + "obor " + o[i].zistiEnergiu() + " mec: " + r[i].ukazMec());
		}

		//o[57].zjedz(r[57]); // metoda zjedz() nie je v rozhrani triedy Obor!		

		// metodu zistiRozdielEnergii() mozeme uplatnit na akekolvek dve bytosti, ktore implementuju rozhranie Energia
		//System.out.println(zistiRozdielEnergii(o[11], r[11]));
		//System.out.println(zistiRozdielEnergii(r[10], o[0]));
		//System.out.println(zistiRozdielEnergii(r[2], r[1]));
	}
}
