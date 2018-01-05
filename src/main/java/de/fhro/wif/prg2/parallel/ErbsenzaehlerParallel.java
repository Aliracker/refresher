package de.fhro.wif.prg2.parallel;

public class ErbsenzaehlerParallel extends Erbsenzaehler implements Runnable {
	public ErbsenzaehlerParallel(String name, int n) {
		super(name, n);
	}

	@Override
	public void run() {
		zaehlen();
	}

	public static void main(String[] args) throws InterruptedException {
		ErbsenzaehlerParallel ez1 = new ErbsenzaehlerParallel("Bürokrat 1", 10000);
		ErbsenzaehlerParallel ez2 = new ErbsenzaehlerParallel("Bürokrat 2", 1000);

		Thread t1 = new Thread(ez1);
		Thread t2 = new Thread(ez2);

		// threads starten
		t1.start();
		t2.start();

		// warten bis fertig
		t1.join();
		t2.join();

		System.out.println("main() fertig!");
	}
}
