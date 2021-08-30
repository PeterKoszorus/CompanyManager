package com.company;

import postavy.*;

public class Stret {
	static void stret(Obor obor, Rytier rytier) {
		rytier.utok(obor);
	}

	static int zratajEnergiu(int[] Energia){

		int vyslednaEnergia = 0;

		for(int i = 0; i < 10; i++){

			vyslednaEnergia = Energia[i] + vyslednaEnergia;
		}

		return vyslednaEnergia;
	}

	// aj obor, aj rytier su bytosti, ktore disponuju energiou
	// rozhranie umozni jednotny pristup
	// potom mozeme definovat metodu, ktora zisti rozdiel energii akychkolvek dvoch bytosti
	static int zistiRozdielEnergii(Energia bytost1, Energia bytost2) {
		return bytost1.zistiEnergiu() - bytost2.zistiEnergiu();
	}

	public static void main(String[] args) {
		Obor[] o = new Obor[100];
		Rytier[] r = new Rytier[100];
		int[] arr = new int[10];
		
		// toto je len inicializacia poli obrov a rytierov tak, aby sa prejavili rozne kombinacie
		for (int i = 0; i < 20; i++) {
			r[i] = new StatocnyRytier(140, new Mec(i));
			o[i] = new ZlyObor(50, true);

	//		o[i].zjedz(); // metoda zjedz() nie je v rozhrani triedy Obor!
/*			
			System.out.println(zistiRozdielEnergii(o[i], r[i]) + " " +
					zistiRozdielEnergii(r[i], o[i]) + " " +
					zistiRozdielEnergii(r[i], r[i]));
*/
		}

		for (int i = 20; i < 40; i++) {
			r[i] = new Rytier(50, new Mec(i));
			o[i] = new ZlyObor(50, false);
		}

		for (int i = 40; i < 90; i++) {
			r[i] = new Rytier(50, new Mec(i));
			o[i] = new Obor(50, false);
		}

		for (int i = 90; i < 100; i++) {
			r[i] = new Rytier(50, new Mec(i));
			o[i] = new StrasidelnyObor(50, true);
		}

		for(int i = 0; i < 10; i++)
		{
			arr[i] = o[i].zistiEnergiu();
		}

		System.out.println("Celkova enrgia prvych 10 obrov je " + zratajEnergiu(arr));

		for (int i = 0; i < 100; i++) {
			stret(o[i], r[i]);
			System.out.println(i + ":" + "rytier " + r[i].zistiEnergiu() +
					" / " + "obor " + o[i].zistiEnergiu() + " mec: " + r[i].ukazMec());
		}
	}
}
