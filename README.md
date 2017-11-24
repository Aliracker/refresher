# Refresher Programmieren 2

_Freiwilliger Wiederholungskurs im [Bachelorstudiengang Wirtschaftsinformatik](https://www.fh-rosenheim.de/technik/informatik-mathematik/wirtschaftsinformatik-bachelor/) an der [Hochschule Rosenheim](https://www.fh-rosenheim.de)._


# Organisatorisches

**Termin**: Dienstags um 17:15 Uhr im B0.11

**Modus**: seminaristischer Unterricht; [BYOD](https://en.wikipedia.org/wiki/Bring_your_own_device) empfohlen.

**Kommunikation**: via [Mattermost](https://inf-mattermost.fh-rosenheim.de/wif-prg2/channels/town-square) ([einschreiben](https://inf-mattermost.fh-rosenheim.de/signup_user_complete/?id=wp3dau8xmigxtmf93z5ixur1ta))

_Die Teilnahme ist freiwillig und unverbindlich, es gibt keine Prüfung._


# Inhalt

Es werden die wichtigsten Inhalte von [Programmieren 2](https://hsro-wif-prg2.github.io/) in verkürzter Form wiederholt und an Beispielen vertieft.
**Wichtig:** Dies ist eine _Ergänzung aber kein Ersatz_ zur ursprünglichen Vorlesung, und hat keinen Anspruch auf Vollständigkeit.

- Datenstrukturen: Liste, Set und Map (assoziatives Array), und deren Realisierung als Array, verkettete Liste oder Baumstruktur.
- Algorithmen: Binäre Suche, sortieren.
- Sprachfeatures: Interfaces, Vererbung, abstrakte Klassen und Generics.
- Datenverarbeitung: Traversierung mit Iteratoren und _filter-map-reduce_.
- Parallele Verarbeitung mit Threads.

# Terminplan

- **Vererbung und abstrakte Klassen (14.11.2017, [Folien](vererbung-und-abstrakte-klassen-slides/), [Aufgabe](vererbung-und-abstrakte-klassen/))**

	Abstrakten Klassen erklärt am Beispiel der Implementierung eines Zustandsautomaten.

- **Listen und Generics (28.11.2017, [Aufgabe](listen-generics/))**

	Die Liste ist eine der wichtigsten Datenstrukturen der Informatik.
	Mit Generics können wir diese unabhängig vom Datentyp implementieren.

- **Set als Binärbaum (5.12.2017)**

	Ein Set ist definiert als eine (ungeordnete) Menge von Elementen ohne Duplikate.
	Die Interfaces `Comparable<T>` und `Comparator<T>` helfen uns beim Aufbau von Binärbäumen, welche Sets effizient modellieren können.

- **Iterator und Factory (12.12.2017)**

	Ein `Iterator<T>` ist ein Objekt, welches die Traversierung einer Datenstruktur ermöglicht, ohne deren innere Struktur zu kennen.
	Eine _Factory_ stellt Objekte her, welche einem Interface genügen.

- **Rekursion (19.12.2017)**
	
	Rekursive Funktionen rufen sich selbst wieder auf.
	Wir betrachten als Beispiele die Pre-/In-/Postfixschreibweise, binäre Suche, sowie Sortieren am Beispiel von merge sort.

- **Parallele Verarbeitung (9.1.2017)**

	Threads helfen bei der parallelen Verarbeitung von Daten.
	Gibt es sowohl Produzenten als auch Konsumenten, so kann es zu Deadlocks kommen.


# Weitere Anregungen zum Selbstudium

- Iteratoren: vorwärts, rückwärts, sortiert, sortiert mit Comparator.
- Baumtraversierung: Rekursiver Links- und Rechtsabstieg; iterativ mit Agenda
- Erweiterte Datenstrukturen: doppelt verkette Liste, n-ärer Baum, Hashset, Hashmap
- Sortieren: insertion, selection, merge, quick


_Abboniere das [https://github.com/hsro-wif-prg2/refresher/](https://github.com/hsro-wif-prg2/refresher/) Repository um bei Updates benachrichtigt zu werden._