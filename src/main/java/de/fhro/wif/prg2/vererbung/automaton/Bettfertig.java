package de.fhro.wif.prg2.vererbung.automaton;

public class Bettfertig extends State {
	protected State successor(String input) {
		if (input.equals("hinlegen")) return new Schlafend();
		else throw new IllegalStateException();
	}
}
