package de.fhro.wif.prg2.vererbung.automaton;

public class Muede extends State {
	protected State successor(String input) {
		if (input.equals("umziehen")) return new Bettfertig();
		else if (input.equals("hinlegen")) return new Schlafend();
		else throw new IllegalStateException();
	}
}
