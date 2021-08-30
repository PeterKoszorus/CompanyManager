package postavy;

public class Mec {
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
}
