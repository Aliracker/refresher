package de.fhro.wif.prg2.parallel;


class Schueler implements Runnable {
	String name;
	int anzahl;
	final Rechenaufgabe aufgabe;

	Schueler(String name, int anzahl, Rechenaufgabe aufgabe) {
		this.name = name;
		this.anzahl = anzahl;
		this.aufgabe = aufgabe;
	}

	@Override
	public void run() {
		while (anzahl-- > 0) {
			synchronized (aufgabe) {
				while (aufgabe.meine(this)) {
					try {
						aufgabe.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				int erg = aufgabe.loesen();
				System.out.println(name + ": Ergebnis ist " + erg);

				aufgabe.neu(this);
				aufgabe.notifyAll();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Rechenaufgabe r = new Rechenaufgabe();

		Schueler a = new Schueler("A", 5, r);
		r.neu(a);

		Schueler b = new Schueler("B", 5, r);

		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Fertig");
	}
}
