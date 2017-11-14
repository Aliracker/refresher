package de.fhro.wif.prg2.vererbung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoAutomatikTest {
	@Test
	void fahren() {
		AutoAutomatik a = new AutoAutomatik();
		a.fahren(10);
	}

}