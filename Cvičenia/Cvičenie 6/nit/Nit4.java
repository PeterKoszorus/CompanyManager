package nit;

public class Nit4 {
	int n = 0;
	
	public Nit4(int n) {
		this.n = n;
	}
	
	public void doIt() {
		for (int i = 0; i < 10; i++) {
			System.out.println("T" + n + ": " + i);
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Nit4 nit = new Nit4(i);
			new Thread(() -> nit.doIt()).start();
		}
	}
}
