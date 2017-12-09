package de.fhro.wif.prg2.iteratoren;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T extends Comparable<T>> implements Set<T> {
	@Override
	public Iterator<T> iterator() {
		// TODO
		throw new UnsupportedOperationException();
	}

	protected class Element {
		T value;
		Element(T value) {
			this.value = value;
		}
		Element left, right;

		public String toString() {
			return String.format("(%s %s %s)", value.toString(),
					left == null ? "-" : left.toString(),
					right == null ? "-" : right.toString());
		}
	}

	protected Element root;

	public String toString() {
		if (root == null) return "()";
		else return root.toString();
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

		Element it = root;
		while (it != null) {
			int c = e.value.compareTo(it.value);
			if (c == 0)
				return;
			else if (c < 0) {
				if (it.left == null) {
					it.left = e;
					return;
				} else
					it = it.left;
			} else {
				if (it.right == null) {
					it.right = e;
					return;
				} else
					it = it.right;
			}
		}

	}

	@Override
	public boolean contains(T t) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			int c = t.compareTo(it.value);
			if (c == 0)
				return true;
			else if (c < 0) {
				it = it.left;
			} else {
				it = it.right;
			}
		}

		return false;
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

		Element it = root, prev = root;
		while (it != null) {
			int c = t.compareTo(it.value);
			if (c == 0) {
				// Treffer!
				T help = it.value;

				// Kamen wir von links oder rechts?
				// 'it' aus der Verzeigerung nehmen
				if (it == prev.left)
					prev.left = null;
				else
					prev.right = null;

				// beide Nachfolger einhängen
				insert(it.left);
				insert(it.right);

				return help;
			}

			prev = it;
			if (c < 0)
				it = it.left;
			else
				it = it.right;
		}

		throw new NoSuchElementException();
	}
}
