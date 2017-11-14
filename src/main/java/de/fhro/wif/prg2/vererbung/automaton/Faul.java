package de.fhro.wif.prg2.vererbung.automaton;

public class Faul extends State {
	protected State successor(String input) {
		if (input.equals("gammeln")) return new Muede();
		else throw new IllegalStateException();
	}
}
