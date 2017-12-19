package de.fhro.wif.prg2.rekursion;

import java.util.LinkedList;
import java.util.List;

public class MergeSort {
	/**
	 * Returns a sorted copy of the original list.
	 * @param list
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> sort(List<T> list) {
		// TODO: rekursive Implementierung von merge-sort.

		// 0: Terminalfall?
		if (list.size() < 2) return list;


		// 1: Teile Liste in zwei Hälften...
		int teile = list.size() / 2;
		List<T> lh = list.subList(0, teile);
		List<T> rh = list.subList(teile, list.size());

		return merge(sort(lh), sort(rh));
	}

	public static <T extends Comparable<T>> List<T> merge(List<T> lh, List<T> rh) {
		if (lh.isEmpty()) return rh;
		else if (rh.isEmpty()) return lh;
		else {
			List<T> ergebnis = new LinkedList<>();
			if (lh.get(0).compareTo(rh.get(0)) < 0) {
				ergebnis.add(lh.get(0));
				ergebnis.addAll(merge(lh.subList(1, lh.size()), rh));
			} else {
				ergebnis.add(rh.get(0));
				ergebnis.addAll(merge(lh, rh.subList(1, rh.size())));
			}
			return ergebnis;
		}
	}

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(3);
		list.add(6);
		list.add(1);
		list.add(2);
		list.add(7);
		list.add(9);

		List<Integer> sortiert = sort(list);

		System.out.println(list);
		System.out.println(sortiert);
	}
}
