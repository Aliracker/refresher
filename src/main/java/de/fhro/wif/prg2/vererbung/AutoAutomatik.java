package de.fhro.wif.prg2.vererbung;

class AutoAutomatik extends Auto {
	private AutomatikGetriebe automatik = new AutomatikGetriebe();
	protected void anlassen() {
		bremse.druecken();
		zuendung.an();
		zuendung.start();
	}
	protected void anfahren() {
		bremse.druecken();
		automatik.drive();
		gas.druecken();
	}
	protected void abstellen() {
		bremse.druecken();
		automatik.park();
	}
}