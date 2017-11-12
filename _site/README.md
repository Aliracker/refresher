# Refresher Programmieren 2

_Freiwilliger Wiederholungskurs im [Bachelorstudiengang Wirtschaftsinformatik](https://www.fh-rosenheim.de/technik/informatik-mathematik/wirtschaftsinformatik-bachelor/) an der [Hochschule Rosenheim](https://www.fh-rosenheim.de)._


# Organisatorisches

**Termin**: Dienstags um 17:15 Uhr im B0.11

**Modus**: seminaristischer Unterricht; [BYOD](https://en.wikipedia.org/wiki/Bring_your_own_device) empfohlen.

**Kommunikation**: via [Mattermost](https://inf-mattermost.fh-rosenheim.de/wif-prg2/channels/town-square) ([einschreiben](https://inf-mattermost.fh-rosenheim.de/signup_user_complete/?id=wp3dau8xmigxtmf93z5ixur1ta))

_Die Teilnahme ist freiwillig und unverbindlich, es gibt keine Prüfung._


# Inhalt

- **(14.11.2017)**
- **(21.11.2017)**
- **(28.11.2017)**
- **(5.12.2017)**
- **(12.12.2017)**
- **(19.12.2017)**

Datenstrukturen. Liste und Iterator; Generics

Interfaces. Typische Beispiele: Comparator<T>, Comparable<T>

Vererbung und abstrakte Klassen: Zustände und Zustandsübergänge (Zooübung; Automatikschaltgetriebe (abstract void schalten()), 
Getränkeautomat (abstract void getraenkMischen), ..., immer dann, wenn viel gemeinsam ist, und nur ein Detail anders).

Datenstrukturen:
allgemein: List<T>, Set<T>, Map<T>, Iterator<T>.
...und Realisierungen: einfach verkettete Liste, doppelt verkette Liste, rueckwaerts verkette Liste, sortierte Liste (prio-queue), Stack, Queue, ArrayList, Binaerbaum, n-aerer Baum, ...mit Comparable<T> oder Comparator<T>
...daraus abgeleitete Strukturen: Set und Map realisiert als Liste, Baum oder Hasharray
...Iteratoren auf diesen Strukturen: Vorwärtsiterator, Rückwärtsiterator (bei doppelt verketter Liste einfach; sonst via Stack), Baumiterator (Agenda!!!), sortierter Iterator (im Baum: depth-first-abstieg), filternder Iterator (seek!)
Algorithmen, Patterns und Sprachfeatures:
Factorypattern: Generatorfunktionen für Instanzen eines Interafaces (z.B. die iterator() Methode, oder andere)
Breiten- und Tiefensuche: Agenda als Stack oder Liste?
Verwendung von bounds um Schnittstellenkompatibilität zu erzwingen
sortieren: insertion sort, merge sort (rekursiv)
Rekursion und Iteration: Aufrufreihenfolgen, Besuchsreihenfolge im Baum (z.B. Binaerbaum: links- und rechts-absteigende Rekursion)


_Subscribe to [https://github.com/hsro-wif-prg2/refresher/](https://github.com/hsro-wif-prg2/refresher/) repository to follow updates._