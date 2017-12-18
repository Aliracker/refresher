package de.fhro.wif.prg2.rekursion;

public class Fibonacci {
	static int fib_einfach(int a) {
		if (a < 2) return a;
		else return fib_einfach(a-2) + fib_einfach(a-1);
	}

	static int fib_besser(int a) {
		return help(a, 0, 1);
	}

	private static int help(int n, int a, int b) {
		if (n == 0) return a;
		else if (n == 1) return b;
		else return help(n-1, b, a+b);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(fib_einfach(i));

		for (int i = 0; i < 10; i++)
			System.out.println(fib_besser(i));
	}
}
