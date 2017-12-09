---
title: Iterator und Factory
permalink: /iterator-factory/
---

# Iterator und Factory

In den vergangenen zwei Wochen haben wir die [Liste als sequenzielle Datenstruktur](../listen-generics/) sowie das [Set realisiert als Binärbaum](../sets-baeume/) wiederholt.

Hier nochmals gegenübergestellt, um die unterschiedliche Struktur zu verdeutlichen:

## Liste

![list](../assets/list-od_001.svg)

Sequenzielle Datenstruktur, erstes `Element` als `first` Referenz, nachfolgende dann jeweils unter `next`.

## Baum

![tree](../assets/tree.svg)

Baumartige Datenstruktur, erstes `Element` als `root` Referenz, dann Nachfolger z.B. mit `.left` und `.right` (Binärbaum), oder mehr als zwei Nachfolger (z.B. als `List<Element> nachfolger` oder `Element[] nachfolger`).

## Iterator

Die Aufgabe eines _Iterators_ ist nun, das Traversieren ("besuchen") dieser Containerdatenstrukturen zu abstrahieren.
Iteration heist nun, man möchte man also die Elemente von Arrays, Listen, Bäume und weiterer Containerstrukturen syntaktisch "gleich" besuchen können:

```java
// for-each Schleife, um alle Elemente zu besuchen:
String[] array = ...;
for (String s : array)
	System.out.println(s);

List<String> list = ...;
for (String s : list)
	System.out.println(s);

Set<String> set = ...;
for (String s : set)
	System.out.println(s);
```

Obige Syntax ist genau dann zu erreichen, wenn der Datentyp das Java Interface `java.lang.Iterable<T>` implementiert, was z.B. bei Arrays sowie den `java.util.List` und `java.util.Set` Interfaces bereits der Fall ist:

```java
interface Iterable<T> {
	Iterator<T> iterator();
}
```

Ein Iterator ist nun entsprechend definiert als:

```java
interface Iterator<T> {
	boolean hasNext();
	T next();
}
```

Man kann den Iterator natürlich auch "normal" verwenden:

```java
List<String> l = ...;
Iterator<String> it1 = l.iterator();
while (it1.hasNext()) {
	String naechstes = it1.next();
	System.out.println(naechstes)
}

Set<String> s = ...;
Iterator<String> it2 = s.iterator();
while (it2.hasNext()) {
	String naechstes = it2.next();
	System.out.println(naechstes)
}
```

Wie Sie sehen ist die Programmlogik für den Benutzer des Iterators jeweis dieselbe.
Aber da wir ja wissen, dass die innere Struktur von Liste und Baum grundverschieden sind, muss der Iterator selbst jeweils anders implementiert werden.

Für die *Liste* ist der Iterator einfach realisiert.
Man speichert eine Referenz auf das `Element`, welches als nächstes an der Reihe ist.
Das ist zu Beginn `first`, dann bei jedem `.next()` Schritt `.next`, und wenn es kein nächstes mehr gibt `null`.

Für Strukturen mit mehr als einem Nachfolger, wie z.B. dem Binärbaum, ist es nun schwieriger zu entscheiden, ob es noch ein nächstes Element gibt: Jedes Element kann ja keinen oder mehrere Nachfolger haben.
Der Kniff ist nun, anstatt sich eine einzelne Referenz auf das nächste Element zu merken unterhält man eine _Liste von Referenzen_, welche noch besucht werden müssen, die sog. _Agenda_.
Zum Iterieren entnimmt man nun zunächst ein Element der Agenda, und fügt die Nachfolger eben dieses Elements wieder in die Agenda ein.
Zu Beginn legt man das Wurzelelement `root` in die Agenda.
Die Iteration ist abgeschlossen, wenn die Agenda leer ist.


## Factory

Eine Fabrik (engl. _factory_) ist nun eine Klasse, welche eine Methode bereit stellt, um Objekte zu erstellen, welche ein Interface implementieren.
Dieser Mechanismus wird dann verwendet, wenn zwar das Interface _öffentlich_, die Implementierung aber _nicht-öffentlich_ ist.
Das ist z.B. für _package-private_ Klassen der Fall, aber auch für innere und anonyme Klassen.

Ein Beispiel:

```java
public interface MeinInterface {
	void machWas();
}
```
```java
class MeineImplementierung implements MeinInterface {
	void machWas() {
		System.out.println("Diese Klasse ist package-private!");
	}
}
```
```java
public class MeineFabrik {  // sichtbar nach Aussen
	MeinInterface create() {
		return new MeineImplementierung();  // sichtbar im Paket
	}
}
```

Ein Benutzer kann also von aussen das Interface `MeinInterface` und die Fabrik `MeineFabrik` sehen; nicht aber `MeineImplementierung`.
Ruft er aber `.create()` der Fabrik auf, so bekommt er eine _Instanz_ der `MeineImplementierung` Klasse.

Das gleiche Prinzip wird nun beim Iterator verwendet: Die aus `java.lang.Iterable` vorgegebene Methode `Iterator<T> iterator()` hat als Rückgabetyp das öffentliche Interface `Iterator<T>`; aber tatsächlich wird eine Instanz einer (oft anonymen) inneren Klasse zurückgegeben.

```java
class MeinIterable<T> implements Iterable<T> {
	public Iterator<T> iterator() {
		// anonyme innere Klasse!
		return new Iterator<>() {
			public boolean hasNext() {
				return false;  // TODO
			}
			public T next() {
				return null;  // TODO
			}
		};
	}
}
```



# Aufgabe

Gegeben ist das Paket [de.fhro.wif.prg2.iteratoren](https://github.com/hsro-wif-prg2/refresher/tree/master/src/main/java/de/fhro/wif/prg2/iteratoren/), mit den Klassen [de.fhro.wif.prg2.iteratoren.ListImpl](https://github.com/hsro-wif-prg2/refresher/tree/master/src/main/java/de/fhro/wif/prg2/iteratoren/ListImpl.java) und [de.fhro.wif.prg2.iteratoren.BinaryTree](https://github.com/hsro-wif-prg2/refresher/tree/master/src/main/java/de/fhro/wif/prg2/iteratoren/BinaryTree.java) sowie den dazugehörigen Tests in [de.fhro.wif.prg2.iteratoren.IteratorenTests](https://github.com/hsro-wif-prg2/refresher/tree/master/src/test/java/de/fhro/wif/prg2/iteratoren/IteratorenTests.java).

Implementieren Sie die `iterator()` Methode der beiden Klassen, so dass der Testcase erfüllt ist.
Implementieren Sie dazu den jeweiligen Iterator entweder als innere Klasse, oder als anonyme innere Klasse.

### Hinweis

Die Klassen [de.fhro.wif.prg2.iteratoren.BinaryTreeWithIterator](https://github.com/hsro-wif-prg2/refresher/tree/master/src/main/java/de/fhro/wif/prg2/iteratoren/BinaryTreeWithIterator.java) und [de.fhro.wif.prg2.iteratoren.ListImplWithIterator](https://github.com/hsro-wif-prg2/refresher/tree/master/src/main/java/de/fhro/wif/prg2/iteratoren/ListImplWithIterator.java) enthalten jeweils die Lösung.
Versuchen Sie aber diese selbst zu erarbeiten!
