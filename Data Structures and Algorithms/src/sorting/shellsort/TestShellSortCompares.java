package sorting.shellsort;

import java.util.Random;
import java.util.Vector;

import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdRandom;

/*
 * 2.1.12 Instrument shellsort to print the number of compares divided by the
 * array size for each increment. Write a test client that tests the hypothesis
 * that this number is a small constant, by sorting arrays of random Double 
 * values, using array sizes that are  increasing powers of 10, starting at 100.
 */
public class TestShellSortCompares {

	// Test the number of compares in shell sort for an array of size N
	private static void TestRandomInput(int N) {
		Double[] a = new Double[N];

		for (int i = 0; i < N; i++)
			a[i] = StdRandom.uniform();

		Vector<IncrCmp> v = ShellSort.sortInstrumented(a);

		System.out.println("Sorted an array of size = " + N);
		System.out.println("Increment-size(h)   Comparisions");
		int nIncrements = v.size();
		for (int i = 0; i < nIncrements; i++) {
			int h = v.get(i).h;
			int nCmp = v.get(i).nCmp;

			StdOut.printf("%10d   %12d , ", h, nCmp);
			if (nCmp > nIncrements * N)
				System.out
						.println("This can't be!!! The number of comparisions is not bounded by - Number of increments times the size of the array, i.e "
								+ nIncrements
								+ " * "
								+ N
								+ " = "
								+ nIncrements
								* N);
			else
				System.out
						.println("The number of comparisions is bounded by - Number of increments times the size of the array, i.e "
								+ nIncrements
								+ " * "
								+ N
								+ " = "
								+ nIncrements
								* N);
		}
	}

	public static void main(String[] args) {
		int N = 1000 + Math.abs(new Random().nextInt(10000));
		TestRandomInput(N);
	}
}

class IncrCmp {
	int h;
	int nCmp;

	IncrCmp(int h, int nCmp) {
		this.h = h;
		this.nCmp = nCmp;
	}
}