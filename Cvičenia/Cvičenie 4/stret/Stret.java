package stret;
import java.util.*;

import postavy.*;

public class Stret {
	private Obor[] obor;
	private Rytier[] rytier;
	private int pocetBojovnikov;

	private List<SledovatelStretu> sledovatelia = new ArrayList<>();

	public void pridajSledovatela(SledovatelStretu sledovatelStavu) {
		sledovatelia.add(sledovatelStavu);
	}

	public void upovedomSledovatelov() {
		for (SledovatelStretu s : sledovatelia)
			s.upovedom();
	}

	public int zistiPocetBojovnikov() {
		return pocetBojovnikov;
	}
	public Rytier zistiRytiera(int i) {
		return rytier[i];
	}
	public Obor zistiObra(int i) {
		return obor[i];
	}

	public void vytvorBojovnikov(int rytieri, int statocniRytieri, int zliObri) {
		pocetBojovnikov = rytieri + statocniRytieri;
		obor = new Obor[pocetBojovnikov];
		rytier = new Rytier[pocetBojovnikov];

		for (int i = 0; i < rytieri; ++i){
			if (i % 2 == 0)
				rytier[i] = new Rytier(100, new SvetelnyMec(i));
			else
				rytier[i] = new Rytier(100, new OcelovyMec(i));				
		}
		
		for (int i = rytieri; i < rytieri + statocniRytieri; ++i){
			if (i % 2 == 0)
				rytier[i] = new StatocnyRytier(100, new SvetelnyMec(i));
			else
				rytier[i] = new StatocnyRytier(100, new OcelovyMec(i));				
		}
		
		for (int i = 0; i < zliObri; ++i){
			obor[i] = new ZlyObor(100);
		}
		
		for (int i = zliObri; i < rytieri + statocniRytieri; ++i){
			obor[i] = new Obor(100);
		}
		
		upovedomSledovatelov();
	}
	
	public void stret1na1(Rytier rytier, Obor obor) {
		rytier.utoc(obor);		
	}

	public String stret() {
		String sprava = "";
		
		for (int i = 0; i < rytier.length; ++i){
			stret1na1(rytier[i], obor[i]);		
			sprava = sprava + (i + 1) + ":"
							+ "rytier " + rytier[i].zistiEnergiu() + " / "
							+ "obor " + obor[i].zistiEnergiu() + " / "
							+ rytier[i].zistiMec().poslednyUder + " /serial.no :"
							+ rytier[i].ukazMec()
							+ "\n";
		}
	
		upovedomSledovatelov();

		return sprava;
	}		
}
