package de.fhro.wif.prg2.parallel;

class Rechenaufgabe {
	int a, b;
	Schueler von;

	void neu(Schueler von) {
		this.von = von;
		this.a = (int) (Math.random() * 10) + 10;
		this.b = (int) (Math.random() * 10) + 10;
	}

	boolean meine(Schueler ich) {
		return von == ich;
	}

	/**
	 * Wartet (a+b)*0.1 Sekunden um Nachdenken zu simulieren,
	 * gibt das Produkt zurück
	 */
	int loesen() {
		try {
			Thread.sleep((a + b) * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a * b;
	}
}
