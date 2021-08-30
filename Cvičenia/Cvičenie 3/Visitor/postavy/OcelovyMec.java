package postavy;

public class OcelovyMec extends Mec {
	public OcelovyMec(int vyrobneCislo) {
		super(vyrobneCislo);
	}

//	public void udriRytier(Obor obor, Rytier rytier) {
	public void udri(Obor obor, Rytier rytier) {
		if (rytier.zistiEnergiu() >= obor.zistiEnergiu())
			obor.nastavEnergiu((int) (0.9 * obor.zistiEnergiu()));
		System.out.println("r-om");
	}
//	public void udriStatocnyRytier(Obor obor, StatocnyRytier rytier) {
	public void udri(Obor obor, StatocnyRytier rytier) {
		obor.nastavEnergiu((int) (0.9 * obor.zistiEnergiu()));
		System.out.println("sr-om");
	}

	public void udri(Obor obor, SilnyRytier rytier) {
		obor.nastavEnergiu((0 * obor.zistiEnergiu()));
		System.out.println("silnym rytierom");
	}
}
