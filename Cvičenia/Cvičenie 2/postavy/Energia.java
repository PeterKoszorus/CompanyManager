package postavy;

public interface Energia {
	void nastavEnergiu(int energia);
	void zvysEnergiu(int energia);
	void znizEnergiu(int energia);
	int zistiEnergiu();
}



/*
public abstract class Energia {
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
}
*/