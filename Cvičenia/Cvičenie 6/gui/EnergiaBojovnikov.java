package gui;

import stret.*;
import javafx.scene.control.*;
import javafx.application.*;

public class EnergiaBojovnikov extends TextField implements SledovatelStretu {
	private Stret stret;
	private int energia;

	public EnergiaBojovnikov(Stret stret) {	
		super();
		this.stret = stret;
	}
	
	public void upovedom() {
		energia = stret.energiaObrov() + stret.energiaRytierov();
//		setText(Integer.toString(energy));
		// toto je nevyhnutne pri pouziti inej nite na realizaciu stretu, lebo v takom pripade tato metoda bude vykonavana mimo aplikacnej nire JavaFX
		// problemy sa obvykle vyskytnu len s vyssim poctom bojovnikov (Exception in thread "Thread-3" java.lang.NullPointerException...)
		Platform.runLater(() -> setText(Integer.toString(energia)));
	}
}
