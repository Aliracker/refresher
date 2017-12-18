package de.fhro.wif.prg2.iteratoren;

public interface List<T> extends Iterable<T> {
	void add(T t);
	void insert(int i, T t);
	T get(int i);
	T remove(int i);
	int size();
	default boolean contains(T t) {
		throw new UnsupportedOperationException();
	}
}
