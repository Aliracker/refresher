package de.fhro.wif.prg2.listen;

public class Beispiel {
	public static void main(String[] args) {
		Liste<String> li = new ListeImpl<>();

		li.add("Hans");
		li.add("Dampf");
		li.add("geht");  // 2
		li.add("nach");
		li.add("Hause");  // 4

		li.insert(4, "gerne");

		System.out.println(li);
	}
}
