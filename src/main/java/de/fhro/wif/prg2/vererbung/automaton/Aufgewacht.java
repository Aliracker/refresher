package de.fhro.wif.prg2.vererbung.automaton;

public class Aufgewacht extends State {
	protected State successor(String input) {
		if (input.equals("duschen")) return new Geduscht();
		else if (input.equals("fruehstuecken")) return new Wach();
		else if (input.equals("snooze")) return new Schlafend();
		else throw new IllegalStateException();
	}
}
