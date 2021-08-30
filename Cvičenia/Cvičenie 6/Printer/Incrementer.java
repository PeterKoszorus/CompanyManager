package Printer;

public class Incrementer extends Thread {
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			Values.increment();
		}
	}
}
