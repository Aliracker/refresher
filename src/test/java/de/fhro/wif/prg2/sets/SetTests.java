package de.fhro.wif.prg2.sets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetTests {
	@Test
	void testSet() {
		Set<Integer> s = new BinaryTree<>();

		s.add(4);
		s.add(2);
		s.add(6);
		s.add(1);
		s.add(3);

		System.out.println(s);
		System.out.println(s.contains(6));

		System.out.println(s);
		System.out.println("Removing 2");
		s.remove(2);
		Assertions.assertEquals("(4 (1 - (3 - -)) (6 - -))", s.toString());

		System.out.println(s);
		System.out.println("Removing 4 (root)");
		s.remove(4);  // root!
		Assertions.assertEquals("(1 - (3 - (6 - -)))", s.toString());

		System.out.println(s);
	}
}
