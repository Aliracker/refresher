package de.fhro.wif.prg2.rekursion;

import de.fhro.wif.prg2.iteratoren.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplRekursiv<T> implements List<T> {
	protected class Element {
		T value;
		Element next;
		Element(T value) { this.value = value; }

		@Override
		public String toString() {
			String s = value.toString();
			if (next != null)
				s = s + ", " + next.toString();
			return s;
		}

		void append(T value) {
			if (next == null)
				next = new Element(value);
			else
				next.append(value);
		}

		T get(int i) {
			if (i == 0)
				return value;
			else
				return next.get(i-1);
		}

		T remove(int i) {
			if (i == 0) {
				if (next == null)
					throw new NoSuchElementException();
				T value = next.value;
				next = next.next;
				return value;
			} else
				return next.remove(i-1);
		}

		void insert(int i, T o) {
			if (i == 0) {
				Element n = new Element(o);
				n.next = next;
				next = n;
			} else
				next.insert(i-1, o);
		}

		int size() {
			if (next == null)
				return 1;
			else
				return 1 + next.size();
		}
	}

	protected Element first = null;

	@Override
	public String toString() {
		if (first == null) return "[]";
		else return "[" + first.toString() + "]";
	}

	@Override
	public void add(T value) {
		if (first == null) {
			first = new Element(value);
			return;
		}

		first.append(value);
	}

	@Override
	public T get(int i) {
		if (first == null)
			throw new NoSuchElementException();

		return first.get(i);
	}

	@Override
	public T remove(int i) {
		if (first == null)
			throw new NoSuchElementException();

		if (i == 0) {
			T value = first.value;
			first = first.next;
			return value;
		}

		return first.remove(i-1);
	}

	@Override
	public void insert(int i, T o) {
		if (first == null || i == 0) {
			Element alt = first;
			first = new Element(o);
			first.next = alt;
			return;
		}

		first.insert(i-1, o);
	}

	@Override
	public int size() {
		if (first == null)
			return 0;
		else
			return first.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Element it = first;
			@Override
			public boolean hasNext() {
				return it != null;
			}

			@Override
			public T next() {
				T h = it.value;
				it = it.next;
				return h;
			}
		};
	}
}
