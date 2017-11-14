package de.fhro.wif.prg2.vererbung.automaton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomatonTest {
	@Test
	void testAccept() {
		Automaton a = new Automaton();
		assertTrue(a.accept(new String[] {
				null,
				null,
				"aufwecken",
				"snooze",
				"aufwecken",
				"fruehstuecken",
				"lernen",
				"lernen",
				"hinlegen"}));

		assertFalse(a.accept(new String[] {
				"aufwecken",
				"duschen",
				"lernen"}));
	}

}