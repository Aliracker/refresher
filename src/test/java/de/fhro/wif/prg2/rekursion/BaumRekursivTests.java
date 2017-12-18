package de.fhro.wif.prg2.rekursion;

import de.fhro.wif.prg2.iteratoren.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaumRekursivTests {
	@Test
	void testSizeIter() {
		testSize(new BinaryTree<>());
	}

	@Test
	void tesSizeRek() {
		testSize(new BinaryTreeRekursiv<>());
	}

	void testSize(Set<Integer> tree) {
		// Erst `n` Elemente einlegen
		int n = 10;
		for (int i = 0; i < n; i++)
			tree.add(i);
		assertEquals(n, tree.size());

		// ...dann sollten auch `n` Elemente besucht werden!
		for (Integer i : tree)
			n--;
		assertEquals(0, n);
	}

	@Test
	void testToStringIter() {
		testToString(new BinaryTree<>());
	}

	@Test
	void testToStringRek() {
		testToString(new BinaryTreeRekursiv<>());
	}

	void testToString(Set<Integer> tree) {
		// leere Liste
		assertEquals("()", tree.toString());

		int n = 3;
		for (int i = 0; i < n; i++)
			tree.add(i);
		assertEquals("(0 - (1 - (2 - -)))", tree.toString());
	}


	@Test
	void testInsertIter() {
		testInsertContainsRemove(new BinaryTree<>());
	}

	@Test
	void testInsertRek() {
		testInsertContainsRemove(new BinaryTreeRekursiv<>());
	}

	void testInsertContainsRemove(Set<Integer> tree) {
		tree.add(0);
		tree.add(2);

		assertEquals("(0 - (2 - -))", tree.toString());
		tree.add(1);

		assertEquals("(0 - (2 (1 - -) -))", tree.toString());

		assertTrue(tree.contains(1));
		assertFalse(tree.contains(3));

		assertEquals(new Integer(1), tree.remove(1));
		assertEquals("(0 - (2 - -))", tree.toString());

		assertEquals(new Integer(0), tree.remove(0));
		assertEquals("(2 - -)", tree.toString());
	}
}
