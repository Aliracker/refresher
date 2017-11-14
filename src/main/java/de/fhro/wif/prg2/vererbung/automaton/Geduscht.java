package de.fhro.wif.prg2.vererbung.automaton;

public class Geduscht extends State {
	protected State successor(String input) {
		if (input.equals("fruehstuecken")) return new Wach();
		else throw new IllegalStateException();
	}
}
