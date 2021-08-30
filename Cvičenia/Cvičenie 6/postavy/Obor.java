package postavy;

import java.io.*;
import java.util.*;

public class Obor implements Energia, Bojovnik, Serializable {
	public static final long serialVersionUID = 0;
	private boolean hladny;
	private int energia;
	
	public Obor(int energia) {
		nastavEnergiu(energia);
	}
	public Obor() {
	}	
	
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

	// Vzor Composite - rola Leaf
	public List<Obor> vyberBojovnikov() {
		List<Obor> jednotlivci = new ArrayList<>();
		jednotlivci.add(this);
		return jednotlivci;
	}
}
