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
	
	public void nastavHladny(boolean hladny) {
		this.hladny = hladny;
	}
	public boolean zistiHladny() {
		return hladny;
	}
	
	public void nastav(int energia, boolean hladny) {
		this.energia = energia;
		this.hladny = hladny;
	}
	
	void odveta(Rytier rytier) {
		if (energia > rytier.zistiEnergiu())
			rytier.nastavEnergiu((int) (0.9 * rytier.zistiEnergiu()));
	}
}
