package de.fhro.wif.prg2.parallel;

import java.util.Arrays;

public class Erbsenzaehler {
	private final String name;
	private final double[] data;
	Erbsenzaehler(String name, int n) {
		this.name = name;
		this.data = new double [n];
	}
	public void zaehlen() {
		System.out.println(name + " beginnt...");
		Arrays.sort(data);
		System.out.println(name + " ist fertig!");
	}

	public static void main(String... args) throws InterruptedException {
		Erbsenzaehler ez1 = new Erbsenzaehler("Bürokrat 1", 10000);
		Erbsenzaehler ez2 = new Erbsenzaehler("Bürokrat 2", 1000);

		ez1.zaehlen();
		ez2.zaehlen();

		System.out.println("main() fertig!");
	}
}
