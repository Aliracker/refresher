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

## Beispiel 2: Liste als String

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

Liste: Gegeben ListImpl.size()/Element.size()

Baum: Gegeben: BinaryTreeImpl.toString()

- Iterative Version von `Set.toString()`
- Rekursive Version von `List.add()` und `Set.add()`

- Iterative max Suche?


Baum toString ist rekursiv -- iterativ?


Iterator geht nicht rekursiv!

