package de.fhro.wif.prg2.vererbung.automaton;

public class Fleissig extends State {
	protected State successor(String input) {
		if (input.equals("lernen")) return new Muede();
		else throw new IllegalStateException();
	}
}
