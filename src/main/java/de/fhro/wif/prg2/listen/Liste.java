package de.fhro.wif.prg2.listen;

public interface Liste<T> extends Iterable<T> {
	void add(T o);
	T get(int i);
	T remove(int i);
	void insert(int i, T o);
	int size();
}
