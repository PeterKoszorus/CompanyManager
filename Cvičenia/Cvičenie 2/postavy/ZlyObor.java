package postavy;

public class ZlyObor extends Obor {
//	boolean hladny; // pozor na skryvanie atributov!

	public ZlyObor(int energia, boolean hladny)
	{
		super(energia, hladny);
	}

	void odveta(Rytier rytier) {
		if (is_hladny())
			zjedz(rytier);
	}
	void zjedz(Rytier rytier) {
		rytier.nastavEnergiu(0);
	}
}
