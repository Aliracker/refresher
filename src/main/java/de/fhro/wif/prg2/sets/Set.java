package de.fhro.wif.prg2.sets;

public interface Set<T> {
	void add(T t);
	boolean contains(T t);
	T remove(T t);
}
