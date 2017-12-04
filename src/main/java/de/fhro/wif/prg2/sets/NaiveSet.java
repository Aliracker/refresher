package de.fhro.wif.prg2.sets;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class NaiveSet<T> implements Set<T> {
	private List<T> list = new LinkedList<>();

	@Override
	public void add(T t) {
		for (T i : list)
			if (i.equals(t))
				return;
		list.add(t);
	}

	@Override
	public boolean contains(T t) {
		for (T i : list)
			if (i.equals(t))
				return true;

		return false;
	}

	@Override
	public T remove(T t) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).equals(t))
				return list.remove(i);

		throw new NoSuchElementException();
	}
}
