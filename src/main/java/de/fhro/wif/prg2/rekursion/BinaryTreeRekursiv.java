package de.fhro.wif.prg2.rekursion;

import de.fhro.wif.prg2.iteratoren.Set;

import java.util.*;

public class BinaryTreeRekursiv<T extends Comparable<T>> implements Set<T> {

	protected class Element {
		T value;
		Element(T value) {
			this.value = value;
		}
		Element left, right;

		void insert(Element element) {
			if (element.value.compareTo(value) < 0) {
				if (left == null)
					left = element;
				else
					left.insert(element);
			} else {
				if (right == null)
					right = element;
				else
					right.insert(element);
			}
		}

		boolean contains(T other) {
			int c = other.compareTo(value);
			if (c == 0)
				return true;
			if (c < 0) {
				if (left == null) return false;
				else return left.contains(other);
			} else {
				if (right == null) return false;
				else return right.contains(other);
			}
		}

		T removeChild(T other) {
			if (other.compareTo(value) < 0) {
				if (left == null)
					throw new NoSuchElementException();
				else if (left.value.equals(other)) {
					Element e1 = left.left;
					Element e2 = left.right;
					T value = left.value;
					left = null;
					if (e1 != null) root.insert(e1);
					if (e2 != null) root.insert(e2);
					return value;
				} else
					return left.removeChild(other);
			} else {
				if (right == null)
					throw new NoSuchElementException();
				else if (right.value.equals(other)) {
					Element e1 = right.left;
					Element e2 = right.right;
					T value = right.value;
					right = null;
					if (e1 != null) right.insert(e1);
					if (e2 != null) right.insert(e2);
					return value;
				} else
					return right.removeChild(other);
			}
		}

		int size() {
			int n = 1;
			if (left != null)
				n += left.size();
			if (right != null)
				n += right.size();
			return n;
		}
	}

	protected Element root;

	@Override
	public String toString() {
		if (root == null)
			return "()";

		StringBuffer sb = new StringBuffer();

		// Marker! Immer wenn wir den sehen, muessen wir eine Klammer schliessen.
		Element close = new Element(null);

		Stack<Element> agenda = new Stack<>();
		agenda.push(root);

		while (agenda.size() > 0) {
			Element e = agenda.pop();
			if (e == null) sb.append(" -");
			else if (e == close) sb.append(")");
			else {
				sb.append(" (").append(e.value);
				// Achtung, Stack! Also in umgekehrter Reihenfolge einfügen.
				agenda.push(close);
				agenda.push(e.right);
				agenda.push(e.left);
			}
		}

		return sb.toString().trim();  // trim wegen erstem leerzeichen!
	}

	@Override
	public void add(T t) {
		insert(new Element(t));
	}

	private void insert(Element e) {
		// nichts zu tun...
		if (e == null)
			return;

		// neues Wurzelelement?
		if (root == null) {
			root = e;
			return;
		}

		root.insert(e);
	}

	@Override
	public boolean contains(T t) {
		if (root == null)
			return false;

		return root.contains(t);
	}

	@Override
	public T remove(T t) {
		if (root == null)
			throw new NoSuchElementException();
		else if (root.value.equals(t)) {
			// Achtung: wir wollen das Wurzelelement entfernen!
			T help = root.value;
			if (root.left == null)
				root = root.right;
			else if (root.right == null)
				root = root.left;
			else {
				// links ist neue Wurzel, rechts wird eingehängt
				// Anm: könnte man genauso umgekehrt machen!
				Element r = root.right;
				root = root.left;
				insert(r);
			}
			return help;
		}

		return root.removeChild(t);
	}

	public int size() {
		if (root == null)
			return 0;
		else
			return root.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			java.util.List<Element> agenda = new LinkedList<>();

			{
				if (root != null)
					agenda.add(root);
			}

			@Override
			public boolean hasNext() {
				return agenda.size() > 0;
			}

			@Override
			public T next() {
				Element it = agenda.remove(0);

				if (it.left != null)
					agenda.add(it.left);
				if (it.right != null)
					agenda.add(it.right);

				return it.value;
			}
		};
	}
}
