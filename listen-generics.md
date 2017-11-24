---
title: Liste und Generics
permalink: /listen-generics/
---

# Listen und Generics

## Liste

Eine Liste ist eine sequenzielle Datenstruktur, welche Elemente in der Reihenfolge des Einfügens speichert.

```java
interface List {
	void add(Object o);
	Object get(int i);
	Object remove(int i);
	void insert(int i, Object o);
	int size();
}
```

Die Methoden `add()`, `get()`, `remove()` und `size()` sind selbsterklärend.
Die `insert(int, Object)` Methode fügt ein Objekt an der vorgegeben Stelle ein.


## Generics

Das obige Interface ist mit `Object` sehr allgemein gehalten.
Da `Object` die Oberklasse von allen Klassen ist, kann man jedes Objekt in eine Liste speichern, insbesondere auch Objekte _verschiedener_ Klassen

```java
List liste = ...;
liste.add("Hallo");  // String
liste.add("Welt");
liste.add(3);        // Integer
liste.add('!');      // Character
```

Das kann im lesenden Fall zum Problem werden:

```java
for (int i = 0; i < liste.size(); i++) {
	String s = (String) liste.get(i);  // ClassCastException bei i=2
}
```

Generics erlauben es die Liste _typsicher_ zu machen ohne, aber jeweilige spezielle Typklassen zu implementieren (z.B. `IntegerList`, `StringList`, ...).
Man ersetzt dabei `Object` durch eine _Typvariable_, welche wiederum in der Klassenbeschreibung definiert wird:

```java
interface List<T> {  // hier wird T eingeführt
	void add(T value);  // ... und hier wie Typ verwendet
	T get(int i);
	T remove(int i);
	void insert(int i, T value);
	int size();
}
```

Nun kann man entsprechend eine typsichere Liste anlegen:

```java
List<String> liste = ...;
liste.add("Hallo");
liste.add("Welt");
liste.add(3);  // Compilerfehler! int != String

for (int i = 0; i < liste.size(); i++) {
	String s = liste.get(i);  // keine Typumwandlung mehr erforderlich
}
```

Ein weiterer Vorteil ist, dass man nach `get()` keine Typumwandlung mehr benötigt, da `String` ja durch den Compiler garantiert ist.


## Typkompatibilität

Wichtig bei Generic ist, dass parametrisierte Klassen nicht verwandt automatisch verwandt sind, wenn die Typargumente verwandt sind.
Ein Beispiel:

```java
class A {}
class B extends A {}

A a = new A();
B b = new B();
a = b;  // OK, da B extends A

List<A> listeA = new LinkedList<>();  // OK da LinkedList implements List
List<B> listeB = new LinkedList<>();

listA.add(new B());  // OK da B extends A

listeA = listeB;  // FEHLER! Nicht Typkompatibel.
```


## Bounds

Es ist allerdings möglich gewisse Anforderungen an die Typvariable zu stellen, was die Klassenhierarchie bzw. die implementierten Interfaces gilt.

So kann man z.B. fordern, dass eine Typvariable von einer Klasse abgeleitet sein muss, oder (mindestens) ein Interface implementieren muss.

Möchte man z.B. fordern, dass eine `SortedList` nur für Datentypen gilt, welche von von `A` abgeleitet sind und `Comparable` implementieren, so schreibt man:

```java
class SortedList<T extends A & Comparable<T>> {
	// ...
}
```

Man beachte, dass die Klasse zuerst kommt, und mehrere _Bounds_ mit `&` (und nicht mit `,`) getrennt werden.

Die Verwendung von Bounds erlaubt es ausserdem casts zu vermeiden:

```java
class SortedList<T extends A & Comparable<T>> {
	// ...
	T e1, e2;
	if (e1.compareTo(e2) > 0) // kein Cast auf Comparable nötig!
		// ...
}
```


## Implementierung

Eine Liste wird in der Regel nicht mit einem Array realisiert (da dieses ja in der Größe unveränderlich ist), sondern als dynamisch verkettete Datenstruktur, welche eine Elementklasse als Träger ("Eisenbahnwagon") für das eigentlich zu speichernde Datenobjekt verwendet.

![list-uml](../assets/list.svg)

Hier ein Beispiel mit Objektdiagramm.
Zunächst legt man eine neue Liste an:

```java
List<String> list1 = new LinkedList<>();
```

![list-od-uml](../assets/list-od.svg)

Dann werden die Wörter _Mary had a little lamb_ hinzugefügt:

```java
list1.add("Mary");
list1.add("had");
list1.add("a");
list1.add("little");
list1.add("lamb");
```

![list-od-uml](../assets/list-od_001.svg)

Um das vierte Element ("little") zu löschen ändert man nun den `next` Zeiger des _element3_ so dass er auf _element5_ verweist.

```java
String little = list1.remove(3);  // "little"
```

![list-od-uml](../assets/list-od_002.svg)

Das Objekt _element4_ ist jetzt also nicht mehr erreichbar, und damit aus sicht der _list1_ gelöscht.

# Aufgabe

Implementieren Sie das untenstehende generische Interface und testen Sie Ihre Lösung mit JUnit.

```java
interface List<T> {  // hier wird T eingeführt
	void add(T value);  // ... und hier wie Typ verwendet
	T get(int i);
	T remove(int i);
	void insert(int i, T value);
	int size();
}
```
