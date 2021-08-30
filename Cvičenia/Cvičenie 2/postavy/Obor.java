package postavy;

public class Obor implements Energia {
	private boolean hladny;
	private int energia;
	
	public void nastavEnergiu(int energia) {
		this.energia = energia;
	}
	public void zvysEnergiu(int energia) {
		this.energia += energia;
	}
	public void znizEnergiu(int energia) {
		this.energia -= energia;
	}
	public int zistiEnergiu() {
		return energia;
	}

	void nastavHlad(boolean hladny) {
		this.hladny = hladny;
	}

	public Obor(int energia, boolean hladny){
		nastavEnergiu(energia);
		nastavHlad(hladny);
	}

	public boolean is_hladny(){
		return hladny;
	}

	void odveta(Rytier rytier) {
		if (zistiEnergiu() > rytier.zistiEnergiu())
//			rytier.energia = (int) (0.9 * rytier.energia);
			// k energii sa uz neda pristupit priamo, lebo je private
			rytier.nastavEnergiu((int) (0.9 * rytier.zistiEnergiu()));
	}
}

