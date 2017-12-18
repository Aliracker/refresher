package de.fhro.wif.prg2.rekursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeSortTests {
	@Test
	void testMergeSort() {
		List<Integer> l1 = Arrays.asList(5, 2, 3, 1, 0, 9);
		List<Integer> l2 = MergeSort.sort(l1);
		Collections.sort(l1);

		Assertions.assertEquals(l1, l2);
	}

	@Test
	void testMergeSortSolution() {
		List<Integer> l1 = Arrays.asList(5, 2, 3, 1, 0, 9);
		List<Integer> l2 = MergeSortSolution.sort(l1);
		Collections.sort(l1);

		Assertions.assertEquals(l1, l2);
	}
}
