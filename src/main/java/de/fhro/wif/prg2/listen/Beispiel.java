package de.fhro.wif.prg2.listen;

public class Beispiel {
	public static void main(String[] args) {
		Liste<String> li = new ListeImpl<>();

		li.add("Hans");
		li.add("Dampf");
		li.add(3);

		for (int i = 0; i < li.size(); i++) {
			System.out.println((String) li.get(i));
		}
	}
}
