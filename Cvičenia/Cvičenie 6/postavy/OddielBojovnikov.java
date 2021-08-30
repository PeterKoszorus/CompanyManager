package postavy;

import java.util.*;

// Vzor Composite - rola Composite
// Oddiel bojovnikov pozostava z akychkolvek bojovych zloziek,
// t. j. dalsich bojovich oddielov alebo jednotlivych bojovnikov

public class OddielBojovnikov implements BojovaZlozka {
	private List<BojovaZlozka> zlozky = new ArrayList<>();
	
	public void pridajBojovuJednotku(BojovaZlozka jednotka) {
		zlozky.add(jednotka);
	}

	public List<BojovaZlozka> vyberBojoveJednotky() {
		return zlozky;
	}
	
	public List<Rytier> vyberBojovnikov() {		
		List<Rytier> jednotlivci = new ArrayList<>();
		
		for (BojovaZlozka e : zlozky)
			jednotlivci.addAll(e.vyberBojovnikov());
		
		return jednotlivci;
	}
}
