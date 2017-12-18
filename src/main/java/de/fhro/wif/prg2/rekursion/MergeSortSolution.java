package de.fhro.wif.prg2.rekursion;

import java.util.LinkedList;
import java.util.List;

public class MergeSortSolution {
	public static <T extends Comparable<T>> List<T> sort(List<T> a) {
		if (a.size() < 2)
			return a;
		int h = a.size() / 2;

		return merge(sort(a.subList(0, h)), sort(a.subList(h, a.size())));
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> a, List<T> b) {
		if (a.size() == 0)
			return b;
		else if (b.size() == 0)
			return a;
		else {
			List<T> result = new LinkedList<>();
			if (a.get(0).compareTo(b.get(0)) < 0) {
				result.add(a.get(0));
				result.addAll(merge(a.subList(1, a.size()), b));
			} else {
				result.add(b.get(0));
				result.addAll(merge(a, b.subList(1, b.size())));
			}
			return result;
		}
	}
}
