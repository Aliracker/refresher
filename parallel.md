---
title: Parallele Verarbeitung
permalink: /parallel/
---

# Parallele Verarbeitung

Bisher waren Ihre Javaprogramme rein sequenziell, d.h., die JVM hat jede Instruktuion in der Reihenfolge ausgeführt wie Sie sie programmiert haben.

Ein Beispiel: Man nehme einen Erbsenzaehler, der als Beschäftigung ein Array von `double` Werten sortieren muss.

```java
class Erbsenzaehler {
	private final String name;
	private final double[] data;
	BeanCounter(String name, int n) {
		this.name = name;
		this.data = new double [n];
	}
	public void zaehlen() {
		System.out.println(name + " beginnt...");
		Arrays.sort(data);
		System.out.println(name + " ist fertig!");
	}
}
```

Hat man nun mehrere Erbsenzähler, dann könnte ein Programm so aussehen:

```java
public static void main(String... args) {
	Erbsenzaehler ez1 = new Erbsenzaehler("Bürokrat 1", 10000);
	Erbsenzaehler ez2 = new Erbsenzaehler("Bürokrat 2", 1000);

	ez1.zaehlen();
	ez2.zaehlen();

	System.out.println("main() fertig!");
}
```

Die Ausgabe auf `System.out` ist wie erwartet:

```
Bürokrat 1 beginnt...
Bürokrat 1 ist fertig!
Bürokrat 2 beginnt...
Bürokrat 2 ist fertig!
main() fertig!
```

![Ablaufplan](../assets/bureaucrats.svg)

Da die Erbsenzähler offensichtlich unabhängig von einander arbeiten, können wir diese _nebenläufig_, also parallel, arbeiten lassen.
Hierzu werden _Threads_ gebraucht.
Es gibt in Java zwei Möglichkeiten, Threads zu nutzen:

1. Von `java.lang.Thread` ([link](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)) ableiten, und die `run()` Methode überschreiben.
2. Ein `Runnable` ([link](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)) implementieren, welches dann an einen Thread zur ausführung übergeben wird.


### Ausführungszustand von Threads

Ein Thread kann dann mit `new` angelegt werden, und mit `.start()` zur Ausführung ans Betriebssystem gegeben werden.

Ein Thread kann also verschiede Zustände haben:
- _angelegt_, nach `new Thread()`
- _ausführungsbereit_, nach `.start()`
- _laufend_, dann ist `.isAlive()` `true`

Möchte man auf einen Thread "warten", also solange nicht weiterarbeiten bis er fertig ist, so kann man `.join()` aufrufen.
Die Methode kehrt dann zurück, wenn der Thread mit der Ausführung seiner `run` Methode fertig ist.


## Aufgabe 1

- Erstellen Sie eine Klasse `FleissigerErbsenzaehler`, welche im analog zum `Erbsenzaehler` arbeitet, aber als Thread ausgeführt werden kann.
- Schreiben Sie eine `main` Methode welche 
	* drei `FleissigerErbsenzaehler` anlegt, 
	* entsprechend drei Threads startet, 
	* wartet bis alle drei Threads fertig sind
	* und zum Schluss `"alles fertig"` ausgibt.

_Hinweis:_ Sie können sowohl von `Thread` ableiten, als auch `Runnable` implementieren; nur ist die Verwendung in der `main` dann anders.

Im Ergebnis soll der neue Ablauf dann etwa so sein:

![Ablaufplan parallel](../assets/bureaucrats_001.svg)


# Geteilte Resourcen

In manchen Anwendungen ist es nötig, dass mehrere Threads auf die gleichen Resourcen zugreifen.
In der Vorlesung haben wir dazu bereits das _consumer-producer_ Problem kennen gelernt, bei dem mehrere Threads auf die gleiche Warteschlange (Queue) lesend und schreibend zugreifen.

Greifen potentiell mehrere Threads auf die selbe Resource zu, so muss diese vor gleichzeitigem Zugriff geschützt werden.
Man definiert hierzu _kritische Abschnitte_, also Anweisungsblöcke in denen immer nur ein Thread gleichzeitig aktiv sein darf.

Auch hier gibt es zwei Möglichkeiten:

1. Verwendung einer Lockvariable: `synchronized (variable) { ... }`.
	Hier wird explizit über `variable` "gelockt", und die geschweiften Klammern definieren den kritischen Abschnitt.
2. Ergänzung der Methodensignatur um das Schlüsselwort `synchronized`.
	So ist die gesamte Methode als kritischer Abschnitt definiert; das Lockobjekt ist in diesem Fall `this`.

Die folgenden beiden Vorgehensweisen sind also äquivalent:

```java
void synchronized tuWas() {
	// ...
}
```
```java
void tuWas() {
	synchronized (this) {
		// ...
	}
}
```

Möchten sich nun zwei oder mehr Threads absprechen, wer denn nun in den Abschnitt darf, so können die Methoden `.wait()` und `.notify()` bzw. `.notifyAll()` des Lockobjekts verwendet werden:

- `.wait()` legt den aufrufenden Thread schlafen, bis ein anderer Thread `.notify()` oder `.notifyAll()` aufruft.
- `notify()` bzw. `.notifyAll()` weckt einen bzw. alle anderen Threads auf, welche auf dem Lockobjekt "warten".


## Aufgabe 2

Hier wollen wir ein einfacheres Beispiel betrachten:
Zwei Schüler stellen sich gegenseitig abwechselnd Rechenaufgaben.
Ein Schüler muss also zunächst mit einer Aufgabe beginnen; der andere muss diese dann lösen, bevor er wiederum eine Aufgabe stellen kann.
Eine Art Ping-Pong also.

Die Rechenaufgabe sei vorgegeben:

```java
class Rechenaufgabe {
	int a, b;
	Schueler von;
	
	/**
	 * Neue Aufgabe stellen und sich merken von wem!
	 */
	void neu(Schueler von) {
		this.von = von;
		this.a = (int) (Math.random() * 10) + 10;
		this.b = (int) (Math.random() * 10) + 10;
	}

	boolean meine(Schueler ich) {
		return von == ich;
	}

	/**
	 * Wartet (a+b)*0.1 Sekunden um Nachdenken zu simulieren,
	 * gibt das Produkt zurück
	 */
	int loesen() {
		try {
			Thread.sleep((a + b) * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a * b;
	}
}
```

Die Funktion sollte selbsterklärend sein, `Thread.sleep(...)` läßt den aktuellen Thread für eine Anzahl Millisekunden "schlafen", also nichts tun.
So soll ein Nachdenken simuliert werden.

- Schreiben Sie eine Klasse `Schueler` welche im Konstruktor einen Schuelernamen, eine Anzahl von zu stellenden Aufgaben sowie eine Referenz auf eine `Rechenaufgabe` erhält; es soll `Runnable` implementiert werden.
- Implementieren Sie die Spielelogik für einen Schüler.
	Das Ping-Pong funktioniert nun so, dass ein Spieler so lange warten (`wait()`) muss, bis die Rechenaufgabe nicht mehr von ihm ist (`.meine(this)`).
	Dann kann er sie lösen (`.loesen()`), das Ergebnis ausgeben, und eine neue Aufgabe stellen (`.neu(this)`).

Folgende `main()` Methode sei vorgegeben:

```java
public static void main(String[] args) throws InterruptedException {
	Rechenaufgabe r = new Rechenaufgabe();

	Schueler a = new Schueler("A", 5, r);  // 5x Spielen
	r.neu(a);

	Schueler b = new Schueler("B", 5, r);

	Thread t1 = new Thread(a);
	Thread t2 = new Thread(b);

	t1.start();
	t2.start();

	t1.join();
	t2.join();

	System.out.println("Fertig");
}
```

Die Ausgabe ist entsprechend:

```
B: Ergebnis ist 224
A: Ergebnis ist 180
B: Ergebnis ist 304
A: Ergebnis ist 252
B: Ergebnis ist 204
A: Ergebnis ist 132
B: Ergebnis ist 150
A: Ergebnis ist 132
B: Ergebnis ist 266
A: Ergebnis ist 143
Fertig
```

