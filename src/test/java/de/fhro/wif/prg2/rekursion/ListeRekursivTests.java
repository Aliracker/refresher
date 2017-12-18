package de.fhro.wif.prg2.rekursion;

import de.fhro.wif.prg2.iteratoren.List;
import de.fhro.wif.prg2.iteratoren.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListeRekursivTests {
	@Test
	void testListSizeIter() {
		testSize(new ListImpl<>());
	}

	@Test
	void testListSizeRek() {
		testSize(new ListImplRekursiv<>());
	}

	void testSize(List<Integer> list) {
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
	void testToStringIter() {
		testToString(new ListImpl<>());
	}

	@Test
	void testToStringRek() {
		testToString(new ListImplRekursiv<>());
	}

	void testToString(List<Integer> list) {
		// leere Liste
		assertEquals("[]", list.toString());

		int n = 3;
		for (int i = 0; i < n; i++)
			list.add(i);
		assertEquals("[0, 1, 2]", list.toString());
	}


	@Test
	void testInsertIter() {
		testInsertRemove(new ListImpl<>());
	}

	@Test
	void testInsertRek() {
		testInsertRemove(new ListImplRekursiv<>());
	}

	void testInsertRemove(List<Integer> list) {
		list.add(0);
		list.add(2);

		assertEquals("[0, 2]", list.toString());
		list.insert(1, 1);

		assertEquals("[0, 1, 2]", list.toString());
		list.insert(0, -1);
		assertEquals("[-1, 0, 1, 2]", list.toString());

		list.insert(list.size(), 3);
		assertEquals("[-1, 0, 1, 2, 3]", list.toString());

		assertEquals(new Integer(1), list.remove(2));
		assertEquals("[-1, 0, 2, 3]", list.toString());

		assertEquals(new Integer(-1), list.remove(0));
		assertEquals("[0, 2, 3]", list.toString());

		assertEquals(new Integer(3), list.remove(list.size()-1));
		assertEquals("[0, 2]", list.toString());

	}
}
