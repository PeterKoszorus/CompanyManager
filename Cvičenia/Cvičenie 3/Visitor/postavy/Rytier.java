package postavy;

public class Rytier implements Energia {
	private int energia;
//	private Mec mec;
	protected Mec mec;

	public Rytier(int energia) {
		nastavEnergiu(energia);
	}
	public Rytier(int energia, Mec mec) {
		nastavEnergiu(energia);
		this.mec = mec;
	}
	public Rytier() {
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
	
	public int ukazMec() {
		return mec.zistiVyrobneCislo();
	}

/*
// ak je zodpovednost za uder mecom na rytierovi, musime pripravit taketo metody
// a potom rozhodovat na zaklade typu meca v metode utoc()

 	// vystiznejsi nazov tejto metody by bol udriSvetelnymMecomRytier() 
	public void udriSvetelnymMecom(Obor obor) {
		if (zistiEnergiu() >= obor.zistiEnergiu())
			obor.nastavEnergiu((int) (0.9 * obor.zistiEnergiu()));
		System.out.println("r-sm");
	}
 	// udriOcelovymMecomRytier() 
	public void udriOcelovymMecom(Obor obor) {
		if (zistiEnergiu() >= obor.zistiEnergiu())
			obor.nastavEnergiu((int) (0.9 * obor.zistiEnergiu()));
		System.out.println("r-om");
	}
*/
	public void utoc(Obor obor) {
//		if (zistiEnergiu() >= obor.zistiEnergiu())
//			obor.nastavEnergiu((int) (0.9 * obor.zistiEnergiu()));
/*
		if (mec instanceof SvetelnyMec) {
			udriSvetelnymMecom(obor);
		} else if (mec instanceof OcelovyMec) {
			udriOcelovymMecom(obor);
		} else {
		}
*/
		// uplatneny navrhovy vzor Visitor
//		mec.udriRytier(obor, this);
		mec.udri(obor, this);
		
		obor.odveta(this); 
	}
}
