package stret;

import java.util.*;
import java.io.*;
import postavy.*;

// trieda Stret je serializovatelna
// atributy, ktorych hodnoty nechceme pri serializacii zaznamenavat, oznacime ako transient
public class Stret implements Serializable {
	public static final long serialVersionUID = 0;

	private List<Obor> obor;
	private List<Rytier> rytier;

	transient private Iterator<Obor> iterObor;
	transient private Iterator<Rytier> iterRytier;
	
	transient private Obor bojujuciObor;
	transient private Rytier bojujuciRytier;

	private int pocetBojovnikov;

	transient private List<SledovatelStretu> sledovatelia = new ArrayList<>();

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

	public void vytvorBojovnikov(int rytieri, int statocniRytieri, int zliObri) throws NerovnyPocetBojovnikovException {
		if (zliObri > rytieri + statocniRytieri)
			throw new NerovnyPocetBojovnikovException();
//	public void vytvorBojovnikov(int rytieri, int statocniRytieri, int zliObri) {
		pocetBojovnikov = rytieri + statocniRytieri;
		obor = new ArrayList<>();
		rytier = new ArrayList<>();

/*
		for (int i = 0; i < rytieri; ++i) {
			if (i % 2 == 0)
				rytier.add(new Rytier(100, new SvetelnyMec(i)));
			else
				rytier.add(new Rytier(100, new OcelovyMec(i)));
		}	

		for (int i = rytieri; i < rytieri + statocniRytieri; ++i) {
			if (i % 2 == 0)
				rytier.add(new StatocnyRytier(100, new SvetelnyMec(i)));
			else
				rytier.add(new StatocnyRytier(100, new OcelovyMec(i)));
		}
*/
		// uplatnenie vzoru Composite
		// namiesto jednoducheho zoznamu rytierov, rytieri budu organizovani do oddielov podla ich typu
		// (toto je len programaticka demonstracia - nie je podporena v GUI)
		OddielBojovnikov rytierskaVyprava = new OddielBojovnikov();
		OddielBojovnikov oddielObycajnychRytierov = new OddielBojovnikov();
		OddielBojovnikov oddielStatocnychRytierov = new OddielBojovnikov();

		for (int i = 0; i < rytieri; ++i) {
			if (i % 2 == 0)
				oddielObycajnychRytierov.pridajBojovuJednotku(new Rytier(100, new SvetelnyMec(i)));
			else
				oddielObycajnychRytierov.pridajBojovuJednotku(new Rytier(100, new OcelovyMec(i)));
		}	

		// jedneho statocneho rytiera nechame zvlast - bude mimo oddielov
		for (int i = rytieri; i < rytieri + statocniRytieri - 1; ++i) {
			if (i % 2 == 0)
				oddielStatocnychRytierov.pridajBojovuJednotku(new StatocnyRytier(100, new SvetelnyMec(i)));
			else
				oddielStatocnychRytierov.pridajBojovuJednotku(new StatocnyRytier(100, new OcelovyMec(i)));
		}
		
		// samostatny statocny rytier a dva vytvorene oddiely su sucastou celej rytierskej vypravy, ktora je tiez oddielom
		rytierskaVyprava.pridajBojovuJednotku(new StatocnyRytier(150, new SvetelnyMec(rytieri + statocniRytieri - 1)));
		rytierskaVyprava.pridajBojovuJednotku(oddielStatocnychRytierov);
		rytierskaVyprava.pridajBojovuJednotku(oddielObycajnychRytierov);
		// alternativne, oddiel obycajnych rytierov mozno zavesit pod oddeiel statocnych rytierov - predchadzajuci riadok treba vymenit za:
		//oddielStatocnychRytierov.pridajBojovuJednotku(oddielObycajnychRytierov);
		
		// nakoniec, pre usporiadanie stretu potrebujeme ziskat zoznam vsektych rytierov v hlavnom oddiele
		rytier = rytierskaVyprava.vyberBojovnikov();

		for (int i = 0; i < zliObri; ++i){
			obor.add(new ZlyObor(100));
		}
		
		for (int i = zliObri; i < pocetBojovnikov; ++i){
			obor.add(new Obor(100));
		}
		
		iterObor = obor.iterator();
		iterRytier = rytier.iterator();

		upovedomSledovatelov();
	}
	
	public void stret1na1(Rytier rytier, Obor obor) {
		rytier.utoc(obor);		
	}

	public String stret() {
		String sprava = "";

		if (!iterObor.hasNext()) {
			iterObor = obor.iterator();
			iterRytier = rytier.iterator();
		}

		// do premennej sprava akumulujeme informacie o boji
		for (int i = 0; i < pocetBojovnikov; ++i)
			sprava = sprava + dalsiStret1na1();
	
		upovedomSledovatelov();

		return sprava;
	}

	public String dalsiStret1na1() {
		bojujuciRytier = iterRytier.next();
		bojujuciObor = iterObor.next();
		
		stret1na1(bojujuciRytier, bojujuciObor);
		
		upovedomSledovatelov();

		return rytier.indexOf(bojujuciRytier) + ":"
			+ "rytier " + bojujuciRytier.zistiEnergiu()
			+ " / " + "obor " + bojujuciObor.zistiEnergiu()
			+ " " + bojujuciRytier.zistiMec().poslednyUder
			+ "\n";
	}

	private int zratajEnergiu(List<? extends Energia> zoznam) {
		int energia = 0;
		
		for (Energia o : zoznam)
			energia += o.zistiEnergiu();
		
		return energia;
	}
	
	public int energiaObrov() {
		return zratajEnergiu(obor);
	}

	public int energiaRytierov() {
		return zratajEnergiu(rytier);
	}

	public void uloz() throws ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("hra.out"));
		out.writeObject(this);
		out.close();
	}
	
	public void nacitaj() throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("hra.out"));
		Stret nacitanyStret = (Stret) in.readObject();
		in.close();
		
		obor = nacitanyStret.obor;
		rytier = nacitanyStret.rytier;
		
		iterObor = obor.iterator();
		iterRytier = rytier.iterator();

		upovedomSledovatelov();
	}

	public int spocitajBojovnikov(Class<? extends Bojovnik> Typ) {
//	public int spocitajBojovnikov(Class<Bojovnik> Typ) {
//	public int spocitajBojovnikov(Class Typ) {
		int n = 0;

		for (Bojovnik bojovnik : rytier)
			if (Typ.isInstance(bojovnik))
				n++;

		for (Bojovnik bojovnik : obor)
			if (Typ.isInstance(bojovnik))
				n++;

		return n;
	}
}
