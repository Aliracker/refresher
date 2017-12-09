package de.fhro.wif.prg2.iteratoren;

import java.util.Iterator;
import java.util.LinkedList;

class BinaryTreeWithIterator<T extends Comparable<T>> extends BinaryTree<T> {
	@Override
	public Iterator<T> iterator() {
		return new MeinIterator();
	}

	private class MeinIterator implements java.util.Iterator<T> {
		// verwende "originale" java.util.List
		java.util.List<Element> agenda = new LinkedList<Element>();

		MeinIterator() {
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
	}

}
