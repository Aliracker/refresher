package de.fhro.wif.prg2.vererbung;

class Auto {
	protected Pedal gas = new Pedal("Gaspedal");
	protected Pedal bremse = new Pedal("Bremspedal");
	protected Pedal kupplung = new Pedal("Kupplungspedal");
	protected Schaltung schaltung = new Schaltung();
	protected Zuendung zuendung = new Zuendung();

	protected void anlassen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.an();
		zuendung.start();
	}

	protected void anfahren() {
		kupplung.druecken();
		schaltung.einlegen(1);
		kupplung.loslassen();
		gas.druecken();
	}

	protected void abstellen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.aus();
	}

	public void fahren(int km) {
		anlassen();
		anfahren();
		// km fahren...
		abstellen();
	}
}
