package de.fhro.wif.prg2.vererbung.automaton;

public class Automaton {
	protected State current = new Schlafend();
	public boolean accept(String[] input) {
		try {
			for (String in : input)
				current = current.accept(in);
			return true;
		} catch (IllegalStateException e) {
			return false;
		}
	}
}
