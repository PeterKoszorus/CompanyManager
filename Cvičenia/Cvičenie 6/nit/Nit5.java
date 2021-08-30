package nit;

public class Nit5 {
	int n =0;

	public Nit5(int n) {this.n = n;}
	public void run(){
		for(int i = 0; i < 10; i++){
			System.out.println("T" + n + ":" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			final int n = i; // anonymne triedy maju pristup iba k finalnym premennym

			Nit5 nit  = new Nit5(i);

			new Thread(() -> nit.run()).start();
		}
	}
}
