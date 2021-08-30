package postavy;

public abstract class Mec {
	private int vyrobneCislo;
	public String poslednyUder;

	public Mec(int vyrobneCislo) {
		this.vyrobneCislo = vyrobneCislo;
	}
	public int zistiVyrobneCislo() {
		return vyrobneCislo;
	}
	public void nastavVyrobneCislo(int vyrobneCislo) {
		this.vyrobneCislo = vyrobneCislo;
	}

	public abstract void udri(Obor obor, Rytier rytier);
	public abstract void udri(Obor obor, StatocnyRytier rytier);
}
