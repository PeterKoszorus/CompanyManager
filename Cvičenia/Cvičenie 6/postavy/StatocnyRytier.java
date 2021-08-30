package postavy;

public class StatocnyRytier extends Rytier {
	public static final long serialVersionUID = 0;

	public StatocnyRytier(int energia) {
		super(energia);
	}
	public StatocnyRytier(int energia, Mec mec) {
		super(energia, mec);
	}
	public StatocnyRytier() {
	}

	public void utoc(Obor obor) {
		mec.udri(obor, this);
		obor.odveta(this);
	}
}
