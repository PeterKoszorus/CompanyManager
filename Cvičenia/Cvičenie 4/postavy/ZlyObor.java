package postavy;

public class ZlyObor extends Obor {
	
	public ZlyObor(int energia) {
		super(energia);
	}
	public ZlyObor() {
		super();
	}	
	
	public void odveta(Rytier rytier) {
		if (zistiHladny())
			zjedz(rytier);
	}
	public void zjedz(Rytier rytier) {
		rytier.nastavEnergiu(0);
	}

}
