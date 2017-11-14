package de.fhro.wif.prg2.vererbung.automaton;

public class Schlafend extends State {
	protected State successor(String input) {
		if (input.equals("aufwecken")) return new Aufgewacht();
		else throw new IllegalStateException();
	}
}
