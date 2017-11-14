package de.fhro.wif.prg2.vererbung.automaton;

public abstract class State {
	public State accept(String input) {
		if (input == null) return this;
		else return successor(input);
	}
	protected abstract State successor(String input);
}
