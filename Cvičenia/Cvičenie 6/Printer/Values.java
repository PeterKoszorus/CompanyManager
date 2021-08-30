package Printer;

public class Values {
	static int i = 0, j = 0;

	//treba odstranit synchronized
	static void increment(){
		i++;
		j++;
	}

	/*
	static void increment() {
		synchronized(Values.class) { // vylucny pristup k objektu Values.class ma blok kodu, ktory nasleduje
			i++;
			j++;
		}
	}
	*/
	/*static synchronized void print() {
		if (i != j)
			System.out.println("i=" + i + " j=" + j);
	}
	*/

	// synchronizovane metody su vlastne metody pozostavajuce z
	// bloku kodu, pre ktory je uzamknuty objekt,
	// ku ktoremu z tohto bloku treba zabezpecit vylucny pristup,
	// co je v pripade statickej metody objekt triedy (inak je to this)
	static void print() {
		synchronized(Values.class) { // vylucny pristup k objektu Values.class ma blok kodu, ktory nasleduje
			if (i != j)
				System.out.println("i=" + i + " j=" + j);
		}
	}

	public static void main(String[] args) {
		new Incrementer().start();
		new Printer().start();
	}
}
