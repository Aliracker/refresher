package de.fhro.wif.prg2.vererbung.automaton;

public class Wach extends State {
	protected State successor(String input) {
		if (input.equals("lernen")) return new Fleissig();
		else if (input.equals("fernsehen")) return new Faul();
		else throw new IllegalStateException();
	}
}
