---
title: Vererbung und abstrakte Klassen
permalink: /vererbung-und-abstrakte-klassen/
---

# Vererbung und abstrakte Klassen


## Wiederholung

Zunächst gibt es _Klassen_ (`class`) und _Schnittstellen_ (interfaces, `interface`).


### Klassen und Schnittstellen
Klassen können Attribute und Methoden enthalten, welche jeweils die Sichtbarkeiten öffentlich (`public`), privat (`protected`), geschützt (`protected`, paketweit sichtbar und in abgeleiteten Klassen) und paketweit sichtbar (_package_, ohne Schlüsselwort).

Methoden sind entweder implementiert, oder als `abstract` markiert, was wiederum die Klasse zu einer _abstrakten_ Klasse macht.
Von abstrakten Klassen können keine Instanzen erstellt werden, die Verwendung des `new` Operators mit einer abstrakten Klasse ist ein Syntaxfehler.

Attribute und Methoden können auch statisch (`static`) sein, diese sind dann für alle Instanzen die selben (sic!) und können deshalb ohne Instanzen verwendet werden.

Schnittstellen enthalten Methodensignaturen, in der Regel _ohne_ Implementierungsblock und Sichtbarkeit (default: `public`).
Seit Java 8 können mit dem Schlüsselwort `default` Implementierungen eingefügt werden, welche dann nicht zwingend überschrieben werden müssen.

![Klassen](../assets/klasse.svg)

### Implementieren und Vererbung
Soll eine Klasse eine Schnittstelle erfüllen, so definiert man die Klasse mit `class K implements I`, und implementiert die erforderlichen Methoden.
Da die Klasse `K` mindestens die Methoden implementiert, welche das Interface `I` vorgibt, kann man Instanzen der Klasse `K` einer Referenz vom Typ `I` zuweisen:

```java
interface I { 
	void methode();  // keine Implementierung
}
```
```java
class K implements I {
	public void methode() {
		System.out.println("Hallo, Welt!");
	}
}
```
```java
I inst = new K();
```

Soll eine Klasse eine andere erweitern, so definiert man sie mit `class K extends B`.
Da `K` nun als Erweiterung alle Attribute und Methoden von `B` hat, kann man entsprechend Instanzen der Klasse `K` einer Referenz vom Typ `B` zuweisen:

```java
class B {
	void methode() {
		System.out.println("Hallo, Welt!");
	}
}
```
```java
class K extends B {
	// methode() ist bereits verfügbar, und kann überschrieben werden
}
```
```java
B inst = new K();
```

Klassen können von höchstens einer Klasse Erben, aber mehrere Schnittstellen implementieren (getrennt durch `,`).

![Vererbung](../assets/vererbung.svg)


### Überschreiben von Methoden (und Attributen)
Definiert eine abgeleitete Klasse eine Methode deren Signatur bereits in der Basisklasse vorhanden ist, so wird diese Methode _überschrieben_.

```java
class B {
	void methode1() { System.out.println("Ich bin Klasse B.methode1()"); }
	void methode2() { System.out.println("Ich bin Klasse B.methode2()"); }
}
```
```java
class K extends B {
	void methode2() { System.out.println("Ich bin Klasse K.methode2()"); }
}
```
```java
K k = new B();
k.methode1();  // "Ich bin Klasse B.methode1()"
k.methode2();  // "Ich bin KLasse K.methode2()"
```

Innerhalb einer Klasse kann die Methode einer Basisklasse mit dem `super` Operator (statt `this`) aufgerufen werden.


### Abstrakte Basisklassen
Ist eine Klasse als `abstract` definiert, so können keine Instanzen erstellt werden; eine abstrakte Klasse _ohne_ ableitende Klassen macht also keinen Sinn.

Abstrakte Klassen können -- ähnlich wie Schnittstellen -- dazu verwendet werden, um gewisse Methoden zu erzwingen aber trotzdem bereits Funktionalität bereit zu stellen.

Folgendes Beispiel: Eine Klasse `AutoManuell` modelliere ein Auto mit Gangschaltung und stelle die Methoden `anlassen()`, `anfahren()`, `abstellen()` und `fahren(int km)` bereit:

![Auto](../assets/auto.svg)


```java
class AutoManuell {
	Pedal gas, bremse, kupplung;
	Schaltung schaltung;
	Zuendung zuendung;

	private void anlassen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.an();
		zuendung.start();
	}

	private void anfahren() {
		kupplung.druecken();
		schaltung.einlegen(1);
		kupplung.loslassen();
		gas.druecken();
	}

	private void abstellen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.aus();
	}
	public void fahren(int km) {
		anlassen();
		anfahren();
		// km fahren...
		abstellen();
	}
	public int anzahlGaenge() {
		return schaltung.gaenge();
	}
}
```

Anders aber bei einer Automatik, hier würde der Vorgang wohl eher so aussehen:

```java
class AutoAutomatik {
	private Pedal gas, bremse;
	private AutomatikGetriebe automatik;
	private Zuendung zuendung;
	private void anlassen() {
		bremse.druecken();
		zuendung.an();
		zuendung.start();
	}
	private void anfahren() {
		bremse.druecken();
		automatik.drive();
		gas.druecken();
	}
	private void abstellen() {
		bremse.druecken();
		automatik.park();
	}
	public void fahren(int km) {
		anlassen();
		anfahren();
		// km fahren...
		abstellen();
	}
}
```

Man sieht: Die Funktionalität ist in weiten Teilen die gleiche, warum also nicht einfach erben und nur den tatsächlich verschiedenen Teil implementieren?
Nachdem man die Sichtbarkeiten von `private` auf `protected` gesetzt hat, funktioniert das im Prinzip.

![Automatik](../assets/automatik.svg)

```java
class AutoAutomatik extends Auto {
	private AutomatikGetriebe automatik;
	protected void anlassen() {
		bremse.druecken();
		zuendung.an();
		zuendung.start();
	}
	protected void anfahren() {
		bremse.druecken();
		automatik.drive();
		gas.druecken();
	}
	protected void abstellen() {
		bremse.druecken();
		automatik.park();
	}
}
```

**Aber Vorsicht**: Ein `AutomatikAuto` hat jetzt sowohl eine `Schaltung` als auch ein `AutomatikGetriebe`, und oben drein auch noch ein Kupplungspedal!
Darüber hinaus ist die Semantik jetzt: Jedes `AutomatikAuto` ist ein `AutoManuell`, was natürlich an der Realität vorbei geht.

```java
AutoManuell a = new AutoAutomatik();
a.anzahlGaenge();  // das ist natürlich quatsch!
```


Hier helfen abstrakte Klassen: Sie erlauben es zum einen Methoden in abgeleiteten Klassen vorzuschreiben (Schlüsselwort `abstract`), zum anderen aber Programmlogik, welche für alle abgeleiteten Klassen gleich ist, bereits zu implementieren.

![Auto Abstrakt](../assets/autoabstrakt.svg)

```java
abstract class Auto {
	protected Pedal gas, bremse;  // jedes Auto hat das
	protected Zuendung zuendung;

	// das ist für manuell und automatik anders!
	protected abstract void anlassen();
	protected abstract void anfahren();
	protected abstract void abstellen();

	// wieder gleich fuer alle Autos:
	public void fahren(int km) {
		anlassen();
		anfahren();
		// km fahren...
		abstellen();
	}
}
```
```java
class AutoManuell extends Auto {
	protected Pedal kupplung;
	protected Schaltung schaltung;

	protected void anlassen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.an();
		zuendung.start();
	}

	protected void anfahren() {
		kupplung.druecken();
		schaltung.einlegen(1);
		kupplung.loslassen();
		gas.druecken();
	}

	protected void abstellen() {
		bremse.druecken();
		kupplung.druecken();
		zuendung.aus();
	}
}
```
```java
class AutoAutomatik extends Auto {
	protected AutomatikGetriebe automatik;
	protected void anlassen() {
		bremse.druecken();
		zuendung.an();
		zuendung.start();
	}
	protected void anfahren() {
		bremse.druecken();
		automatik.drive();
		gas.druecken();
	}
	protected void abstellen() {
		bremse.druecken();
		automatik.park();
	}
}
```
```java
Auto manuell = new AutoManuell();
Auto automatik = new AutoAutomatik();

manuell.fahren(10);
automatik.fahren(10);
```

Die abstrakte Klasse `Auto` kann also die Methoden `anlassen()`, `anfahren()` und `abstellen()` vorschreiben, und intern bereits benutzen.

<p style="text-align: right">&#8718;</p>


## Übungsaufgabe: Zustandsautomat

Ein deterministischer ("nicht-zufälliger") Zustandsautomat besteht aus einem Startzustand, einer beliebigen Anzahl Zuständen und, wenn er _endlich_ ist, mindestens einem Endzustand.
Zustandsübergänge als Tripel von Von-Zustand, Eingabe, Zu-Zustand definieren dabei, auf welchen Wegen man durch diesen Graphen gehen kann.
Hier ein einfaches Beispiel aus dem Studentenleben:

![Studentenzustand](../assets/zustandsautomat.svg)


## Klassische Implementierung mit Verzweigung

Die naive Herangehensweise so einen Zustandsautomaten zu implementieren ist die Verwendung eines `enum`s für die Zustände, und `switch-case` für die Übergänge:

```java
class Automat {
	enum Zustand { schlafend, aufgewacht, geduscht, ... }
	Zustand aktuell = schlafend;
	void weiterschalten(String s) {
		if (s == null) 
			return;

		switch (aktuell) {
			case schlafend:
				if (s.equals("aufwecken")) aktuell = wach;
				else throw new IllegalStateException();
				break;
			case aufgewacht:
				if (s.equals("snooze")) aktuell = schlafend;
				else if (s.equals("duschen")) aktuell = geduscht;
				else if (s.equals("fruehstuecken")) aktuell = wach;
				else throw new IllegalStateException();
				break;
			// ...
		}
	}
}
```

Diese Implementierung ist umständlich, und vermischt die Ablaufmechanik (in welchem Zustand bin ich) mit der eigentlichen Logik (welcher Zustand kommt als nächstes).

Eine bessere Implementierung ist es, diese beiden Dinge zu trennen.
Anders betrachtet hat ein Automat Zustände, wobei sich der Zustand nur ändert, wenn es eine Eingabe gibt (z.B. "aufwecken").
Jeder Zustand weiss für sich, auf welche Eingaben welcher Zustand folgt, z.B. der Zustand _schlafend_ bleibt ohne Eingabe unverändert, aber bei `"aufwecken"` wird der Zustand zu _aufgewacht_.

![Zustaende](../assets/zustaende.svg)

_Sowie weitere Zustände analog._

Der Automat kann also mit `accept` ein Array von `String`s entgegen nehmen, und gibt `true` zurück, wenn für die Sequenz von Eingaben ein Pfad in dem Automaten gefunden wurde, oder `false`, wenn die Eingabe ungültig war.

Die Eingabe: `[null, null, "aufwecken", "snooze", "aufwecken", "fruehstuecken", "lernen", "lernen", "hinlegen"]` entspricht der Zustandsfolge _schlafend_, _aufgewacht_, _schlafend_, _wach_, _fleissig_, _muede_, _schlafend_, und gibt `true` zurück.

Die Eingabe `["aufwecken", "duschen", "lernen"]` hat keinen gültigen Pfad (ohne Mampf kein Kampf!), und gibt daher `false` zurück.

Gegeben sind die Klassen `State` und `Automaton`:

```java
abstract class State {
	public State accept(String input) {
		if (input == null) 
			return this;
		else
			return successor(input);
	}
	/**
	 * Gibt den Nachfolgezustand in Abhängigkeit vom Input
	 * zurück, oder wirft eine IllegalStateException.
	 */
	protected abstract State successor(String input);
}
```
```java
class Automaton {
	private State currentState = new Schlafend();
	public boolean accept(String[] input) {
		try {
			for (String in : input) {
				currentState = currentState.accept(in);
			}

			return true;
		} catch (IllegalStateException e) {
			return false;
		}
		
	}
}
```

1. Machen Sie sich mit der Funktionsweise der `Automaton.accept()` Methode vertraut.
2. Implementieren Sie die Klassen `Schlafend`, `Aufgewacht`, `Geduscht`, `Wach`, `Fleissig`, `Faul`, `Muede`, und `Bettfertig` welche die entsprechenden Zustände des Automaten modellieren sollen.
	Als Beispiel: Der Nachfolgezustand von `Aufgewacht` ist vom Typ `Geduscht`, wenn die Eingabe `"duschen"` war, oder `Wach` wenn die Eingabe "fruehstuecken" war; bei anderen Eingaben wird eine `IllegalStateException` geworfen.
3. Testen Sie Ihre Zustände für einige Inputarrays.
