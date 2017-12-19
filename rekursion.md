---
title: Rekursion
permalink: /rekursion/
---

# Rekursion

> Um Rekursion zu verstehen müssen Sie zuerst Rekursion verstehen.

Eine _rekursive_ Funktion ist eine Funktion, welche sich selbst wieder aufruft.
Entscheidend ist hierbei, dass eine solche Funktion zwischen zwei Fällen unterscheidet:

- Im **Rekursionsfall** wird die Methode erneut und mit veränderten Argumenten (!) aufgerufen.
- Im **Terminalfall** wird ein festgelegter Wert zurückgegeben.

Wird bei der _Iteration_ in der Regel mit einer Hilfsvariablen gearbeitet welche in einer `for`/`while` Schleife verändert wird, so wird bei der Rekursion in der Regel mit dem `return`-Wert gearbeitet.

## Beispiel 1: Fibonacci

Die Fibonacci-Zahlenreihe (0, 1, 1, 2, 3, 5, 8, ...) ist _rekursiv_ definiert: eine Zahl ist die Summe seiner vorangegangenen Zahlen; die erste und die zwei Zahlen sind _definiert_ als 1.

Es gilt also:
- $fib(0) = 0$
- $fib(1) = 1$
- $fib(n) = fib(n-2) + fib(n-1) \quad \forall n > 2$

Oder in Java formuliert:

```java
int fib_einfach(int a) {
	if (a < 2) return a;
	else return fib_einfach(a-2) + fib_einfach(a-1);
}
```

<https://github.com/hsro-wif-prg2/refresher/blob/master/src/main/java/de/fhro/wif/prg2/rekursion/Fibonacci.java#L4>

> Hinweis: Die obige Lösung hat *zwei* rekursive Aufrufe, was es sehr ineffizient macht (warum?).
> Hier ist eine [bessere ("endrekursive") Lösung](https://github.com/hsro-wif-prg2/refresher/blob/master/src/main/java/de/fhro/wif/prg2/rekursion/Fibonacci.java#L9).


## Beispiel 2: Länge einer Liste

Die `size()` Methode einer Liste kann rekursiv wie folgt definiert werden.

- Ist die Liste Leer, so ist das Ergebnis `0`; ansonsten ist es die _size_ des ersten Elements.
- Die _size_ eines Elementes ist entweder `1`, wenn es keinen Nachfolger gibt; oder `1` plus die _size_ des Nachfolgers.

```java
class ListImpl<T> ... {
	class Element {
		Element next;
		int size() {
			if (next == null) return 1;
			else return 1 + next.size();
		}
	}

	int size() {
		if (first == null) return 0;
		else return first.size();
	}
}
```

<https://github.com/hsro-wif-prg2/refresher/blob/master/src/main/java/de/fhro/wif/prg2/rekursion/ListImpl.java#L15>


## Beispiel 3: Baum als String

Die `toString()` Methode eines Baumes kann ebenso rekursiv (und tatsächlich leichter als iterativ) definiert werden.

- Ist der Baum leer, so ist der String `"()"`; ansonsten ist der Baum der String des Wurzelknotens (`root.toString()`).
- Ein Knoten ist zunächst sein eigener Wert, gefolgt von den Strings seiner links und rechts nachfolgenden Knoten, eingeschlossen von Klammern; ein `null`-Nachfolger sei mit `"-"` gekenntzeichnet.

Beispiel: Fügt man der Reihe nach 3, 1, 0, 2, und 4 in einen Binärbaum ein, so ist der String `"(3 (1 0 2) 4)"`.

```java
class Binaerbaum<T> ... {
	class Element {
		...
		public String toString() {
			String s = "(" + value.toString();
			if (left != null)
				s = s + " " + left.toString();
			else
				s = s + " -";

			if (right != null)
				s = s + " " + right.toString();
			else
				s = s + " -";

			return s + ")";
		}
	}
	...
	public String toString() {
		if (root == null) return "()";
		else return root.toString();
	}
}
```

Dabei sind die `left.toString()` und `right.toString()` Aufrufe _rekursiv_, da sie in `Element.toString` sind, und dieselbe `Element.toString` aufrufen.
Dagegen ist `value.toString()` hier _nicht_ rekursiv: Es wird in `Element.toString` die Methode `T.toString` (also z.B. `Integer.toString` aufgerufen; es handelt sich also um eine andere Methode.

## Beispiel 4: Mergesort

Mergesort ist ein _Teile-und-Herrsche_ Algorithmus der auf zwei Prinzipien beruht:

1. Eine leere oder einelementige Liste ist bereits sortiert.
2. Das Zusammenführen von bereits sortierten Listen ist einfach: es wird abwechselnd immer das kleinere erste Element der beiden Listen genommen.

Der Algorithmus geht nun so vor: 
- _sort_: 
	* Ist die Liste kürzer als 2, so wird die Liste zurückgegeben (bereits sortiert).
	* Ansonsten wird die Liste in zwei Hälften geteilt, diese jeweils sortiert (Rekursion!) und die sortierten Hälften zusammengefügt (_merge_).
- _merge_:
	* Ist eine der beiden Listen leer, so wird die andere zurückgegeben.
	* Ansonsten wird eine _neue_ Liste erstellt, welche als erstes Element das kleinere der beiden ersten Elemente enthält; an diese wird dann noch die Zusammenführung der Restlisten angehängt. 


# Kochrezept

Das Kochrezept für Rekursion ist im Prinzip einfach gehalten:

1. Identifizieren Sie den bzw. die _Terminalfälle_.
	In welchem Falle (`if`!) ist das Ergebnis bereits bekannt?
2. Identifizieren Sie den bzw. die _Rekursionsfälle_.
	Müssen die Aufrufargumente verändert werden?

Ist eine iterative Variante gegeben, so gibt die Bedingung in der `for`/`while` Schleife einen Hinweis auf den Terminalfall, und der Schleifenblock Hinweise auf die Rekursion.

> Hinweis: Ein Iterator kann nicht rekursiv implementiert werden, da dieser immer nur einen Schritt weit geht, und nicht "bis zum Ende".


# Aufgabe

Für alle Aufgaben gilt zu Übungszwecken: Es darf nur `if`, `else if`, `else` für die Fallunterscheidung, sowie `return` zur Wertrückgabe verwendet werden; `for`, `do` und `while` sind **nicht** gestattet!


1. Modifizieren Sie die gegebene Klasse [ListImpl](https://github.com/hsro-wif-prg2/refresher/blob/master/src/main/java/de/fhro/wif/prg2/rekursion/ListImpl.java#L15) so, dass alle Methoden rekursiv implementiert sind.
	- Die Methoden `toString()` und `size()` wurden bereits rekursiv implementiert.
	- Beginnen Sie mit der `contains()` Methode.
	- Für alle Methoden benötigen Sie Hilfsmethoden in der inneren `Element`-Klasse.
2. Modifizieren Sie die gegebene Klasse [BinaryTree](https://github.com/hsro-wif-prg2/refresher/blob/master/src/main/java/de/fhro/wif/prg2/rekursion/BinaryTree.java) so, dass alle Methoden rekursiv implementiert sind.
	- Für alle Methoden benötigen Sie die Hilfsmethoden in der inneren `Element`-Klasse.
	- Beginnen Sie mit den `contains()` und `size()` Methoden.	- 
	- Die Methode `toString()` wurde bereits rekursiv implementiert; siehe Bonusaufgabe unten.
3. Implementieren Sie die `MergeSort.sort()` Methode rekursiv; es wird eine Hilfsmethode `MergeSort.merge` benötigt.
    Verwenden Sie die Methode `List.subList(from, to)` um Listen aufzuteilen.


# Bonusaufgabe zum Knobeln

> Diese Aufgabe ist zu schwer für die Klausur.

Die Methode `BinaryTree.toString()` wurde bereits rekursiv implementiert.
Modifizieren Sie die Methode so, dass sie streng _iterativ_ arbeitet.
