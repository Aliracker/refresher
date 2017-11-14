package de.fhro.wif.prg2.vererbung;

public class Pedal {
	private String name;
	public Pedal(String name) {
		this.name = name;
	}
	void druecken() {
		System.out.println("Druecke " + name);
	}
	void loslassen() {
		System.out.println("Lasse " + name + " los");
	}
}
