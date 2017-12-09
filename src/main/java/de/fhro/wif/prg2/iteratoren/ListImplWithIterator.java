package de.fhro.wif.prg2.iteratoren;

import java.util.Iterator;

public class ListImplWithIterator<T> extends ListImpl<T> {
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
