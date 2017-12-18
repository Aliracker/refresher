package de.fhro.wif.prg2.iteratoren;

public interface Set<T> extends Iterable<T> {
	void add(T t);
	boolean contains(T t);
	T remove(T t);
	default int size() {
		throw new UnsupportedOperationException();
	}
}
