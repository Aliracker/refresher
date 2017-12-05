---
title: Sets und Bäume
permalink: /sets-baeume/
---

# Das Set als Allgemeine Datenstruktur

Die in der letzten Woche besprochene [_Liste_](listen-generics/) ist ein Datenstruktur: Sie erhält die Reihenfolge, in der dieElemente eingefügt wurden.
Das heisst, werden nacheinander die Elemente `'a'`, `'b'` und `'c'` eingefügt (`.add()`), so sind diese an den Stellen `0`, `1`, und `2` gespeichert.
Weiterhin können Duplikate enthalten ein; ein Element kann also mehrfach an verschiedenen Stellen gespeichert sein.

Die _Menge_ (engl. _set_) ist nun eine Datenstruktur, welche _nicht zwingend geordnet_ geordnet, aber _frei von Duplikaten_ ist: Denken Sie an die mathematische Definition einer Menge: Entweder ist ein Element enthalten, oder eben nicht.

Das unten stehende Interface `Set<T>` verdeutlicht den Unterschied.
Anders als bei `List<T>` gibt es nun kein `.get(int i)`, um ein Element an bestimmter Position zu erhalten; stattdessen kann nur geprüft werden, ob ein Element enthalten ist (`.contains(T t)`).
Ebenso ist die Signatur der `.remove()` Methode anders: Sie nimmt ein Element entgegen, keinen Index.

```java
interface Set<T> {
	void add(T t);
	boolean contains(T t);
	T remove(T t);
}
```

Da jede Methode (`add`, `contains` und `remove`) Elemente auf Gleichheit prüfen muss, und ein Set frei von Duplikaten sein soll, muss die Klasse `T` der _Elemente_ umbedingt `Object.equals(Object other)` überschreiben.


# Naive Implementierung

Es gibt nun verschiedene Methoden um Sets zu realisieren.
Eine einfache aber sehr ineffiziente Möglichkeit ist es, Elemente in einer Liste zu speichern, und nun jeweils zu prüfen ob Gleichheit besteht:

```java
class NaiveSet<T> implements Set<T> {
	Liste<T> list = new LinkedList<>();

	void add(T t) {
		for (T i : list)
			if (t.equals(i))  // bereits enthalten?
				return;

		list.add(t);
	}

	void contains(T t) {
		for (T i : list)
			if (t.equals(i))
				return true;
		// nicht gefunden...
		return false;
	}

	T remove(T t) {
		for (int i = 0; i < list.size(); i++)  // verwende index!
			if (list.get(i).equals(t))
		 		return list.remove(i);
		// nicht gefunden... 
		throw new NoSuchElementException();
	}
}
```

Nun muss aber für jede Operation im schlechtesten Falle immer die ganze Liste durchlaufen werden, der Aufwand ist also im _worst case_ O(n).


# Bäume

Eine weitaus effizientere Lösung ist die Implementierung als _Baumstruktur_.
Diese ist nun, wie der Name bereits nahe legt, nicht _sequenziell_ (also linear hintereinander gereiht), sondern _verzweigend_, es gibt also für jeden Eintrag mehr als einen Nachfolger.
Bei genau zwei Nachfolgern spricht man von einem _Binärbaum_, bei mehr als zwei Nachfolgern von _n-ären Bäumen_.

Partitioniert man nun die Elemente so, dass im linken Teilbaum alle kleineren, und im rechten Teilbaum alle größeren Elemente zu finden sind, so erhält man einen _binären Suchbaum_.

![tree](../assets/tree.svg)

Sucht man nun in so einem Binärbaum, so ist die Anzahl der "Suchschritte" im Mittel nur O(log n) -- was schon für kleine _n_ erheblich effizienter ist:

![o-n-logn](../assets/o-n-logn.png)


## Einfügen in einen Baum

Nehmen wir folgendes Beispiel: Es sollen nacheinander die Zahlen 4, 2, 3, 6, 1 in den Baum eingefügt werden, also in etwa

```java
Set<Integer> set = new Baum<>();  // Baum implements Set
set.add(4);  // 2.
set.add(2);  // 3.
set.add(3);  // 4.
set.add(6);  // 5.
set.add(1);  // 6.
```

Dann soll der Baum so aufgebaut werden:

![tree-1](../assets/tree-animated.svg)

![tree-1](../assets/tree-animated_001.svg)

![tree-1](../assets/tree-animated_002.svg)

![tree-1](../assets/tree-animated_003.svg)

![tree-1](../assets/tree-animated_004.svg)

![tree-1](../assets/tree-animated_005.svg)


## Suchen und Löschen aus einem Baum

Bei der Liste ist das Suchen und Löschen so einfach wie ineffizient: Man beginnt vorne, und geht solange zum nachfolgenden Element, bis es gefunden wurde; dort angelagt, wird das Trägerobjekt gelöscht indem die Verzeigerung so angepasst wird, dass das gesuchte Element nicht mehr erreicht werden kann.

Im Baum ist es zwar im Prinzip ähnlich, aber "mechanisch" sehr verschieden: 

- Es muss nun nicht mehr über genau einen Nachfolger iteriert werden, sondern unterschieden werden, ob man links oder rechts "absteigt."
- Wird ein Element gelöscht, so müssen dessen Teilbäume wieder erneut eingehängt (hinzugefügt) werden.

Wir bleibem am obigen Beispiel und löschen die `2`:

![tree-delete](../assets/tree-delete-anim.svg)

Die `2` ist gelöscht, aber die Teilbäume `1` und `3` müssen wieder eingehängt werden:

<div class="imgcols">
<img alt="tree-delete" src="../assets/tree-delete-anim_001.svg">
<img alt="tree-delete" src="../assets/tree-delete-anim_002.svg">
<img alt="tree-delete" src="../assets/tree-delete-anim_003.svg">
</div>

# Aufgabe

Implementieren Sie das unten stehende Interface `Set<T>`, und zwar als generische Klasse `BinaerBaum`, mit einem Typparameter _bound_ (`extends...`) auf `Comparable<T>`.

```java
interface Set<T> implements Iterable<T> {
	void add(T t);
	boolean contains(T t);
	T remove(T t);
}
```

**Hinweise:**

- Ähnlich zur Liste benötigen Sie wieder ein "Trägerelement"; diesmal muss es allerdings **zwei** Nachfolger (links und rechts) besitzen.
- Es ist hilfreich die `toString()` Methode des Trägerelements (rekursiv) zu implementieren, um den Baum auf der Konsole ausgeben zu können.
- Beginnen Sie dem Verständnis wegen mit `contains` bevor Sie `add` und zuletzt `remove` implementieren.
- Schreiben Sie eine Hilfsmethode `insert`, welche ein _Trägerelement_ (Teilbaum) entgegen nehmen kann, und verwenden Sie diese in der `remove` Methode.
- Wie ist der Sonderfall zu lösen, dass das `root`-Element gelöscht werden soll?
