package postavy;

// trieda Mec je nakoniec abstraktna - je to nieco ako rozhranie, ale moze mat aj (nestaticke) atributy
public abstract class Mec {
	private int vyrobneCislo;

	public Mec(int vyrobneCislo) {
		this.vyrobneCislo = vyrobneCislo;
	}
	
	public int zistiVyrobneCislo() {
		return vyrobneCislo;
	}
	public void nastavVyrobneCislo(int vyrobneCislo) {
		this.vyrobneCislo = vyrobneCislo;
	}

	// metody su abstraktne, lebo nevieme, co spravi abstraktny mec
	
	// pre kazdy druh rytiera potrebujeme metodu uderu
//	public abstract void udriRytier(Obor obor, Rytier rytier);
//	public abstract void udriStatocnyRytier(Obor obor, StatocnyRytier rytier);

	// avsak mozeme pouzit pretazenie (overloading), aby sme nemuseli vymyslat nazvy metod
	// rozhodnutie, ktora sa spusti, urobi kompilator na zaklade typu parametra rytier
	public abstract void udri(Obor obor, Rytier rytier);
	public abstract void udri(Obor obor, StatocnyRytier rytier);
	public abstract void udri(Obor obor, SilnyRytier rytier);
}
