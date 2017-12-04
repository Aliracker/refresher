package de.fhro.wif.prg2.listen;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeImpl<T> implements Liste<T> {
	private class Element {
		T value;
		Element next;
		Element(T value) { this.value = value; }

		public String toString() {
			return value.toString() +
					(next == null ? "" : ", " + next.toString());
		}
	}

	private Element first = null;
	private int size = 0;

	public String toString() {
		if (first == null) return "[]";
		else return "[" + first + "]";
	}

	@Override
	public void add(T value) {
		if (first == null) {
			first = new Element(value);
			size++;
			return;
		}

		Element it = first;
		while (it.next != null)
			it = it.next;

		Element n = new Element(value);
		it.next = n;
		size++;
	}

	@Override
	public T get(int i) {
		if (first == null)
			throw new NoSuchElementException();

		Element it = first;
		for (int j = 0; j < i; j++) {
			it = it.next;

			if (it == null)
				throw new NoSuchElementException();
		}

		return it.value;
	}

	@Override
	public T remove(int i) {
		if (first == null)
			throw new NoSuchElementException();

		Element it = first;

		for (int j = 0; j < i - 1; j++) {
			it = it.next;

			if (it == null)
				throw new NoSuchElementException();
		}

		if (it.next == null)
			throw new NoSuchElementException();

		T wert = it.next.value;

		it.next = it.next.next;
		size--;
		return wert;
	}

	@Override
	public void insert(int i, T o) {
		if (first == null || i == 0) {
			Element alt = first;
			first = new Element(o);
			first.next = alt;
			size++;
			return;
		}

		Element it = first;

		for (int j = 0; j < i-1; j++) {
			it = it.next;
			if (it == null)
				throw new NoSuchElementException();
		}

		Element hilfe = it.next;
		it.next = new Element(o);
		it.next.next = hilfe;
		size++;
	}

	@Override
	public int size() {
		// return size;
		Element it = first;
		int size = 0;
		while (it != null) {
			it = it.next;
			size++;
		}
		return size;


	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Element it = first;
			@Override
			public boolean hasNext() {
				return first != null;
			}

			@Override
			public T next() {
				T e = it.value;
				it = it.next;
				return e;
			}
		};
	}
}
