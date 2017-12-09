package de.fhro.wif.prg2.iteratoren;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class IteratorenTests {
	@Test
	void testListIterator() {
		List<Integer> list = new ListImpl<>();

		// Erst `n` Elemente einlegen
		int n = 10;
		for (int i = 0; i < n; i++)
			list.add(i);
		assertEquals(n, list.size());

		// ...dann sollten auch `n` Elemente besucht werden!
		for (Integer i : list)
			n--;
		assertEquals(0, n);
	}

	@Test
	void testSetIterator() {
		Set<Integer> set = new BinaryTree<>();

		// Erst `n` Elemente einlegen
		int n = 10;
		for (int i = 0; i < n; i++)
			set.add(i);

		// ...dann sollten auch `n` Elemente besucht werden!
		for (Integer i : set)
			n--;
		assertEquals(0, n);
	}
}
