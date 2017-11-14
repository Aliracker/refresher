package de.fhro.wif.prg2.vererbung;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {
	@Test
	void fahren() {
		Auto a = new Auto();
		a.fahren(10);
	}

}