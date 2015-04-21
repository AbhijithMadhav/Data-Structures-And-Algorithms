package sorting.strategy.algorithms.quicksort;

import sorting.Sorter;
import edu.princeton.cs.introcs.StdRandom;

public class QuickSorter<T extends Comparable<T>> extends Sorter<T>
{
	public void sort(T[] a)
	{
		StdRandom.shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}

	public void sort(T[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		 sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		 sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
	}

	public int partition(T[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		T v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	public int partition(T[] a, int lo, int hi, int pivotIndex)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		exch(a, lo, pivotIndex);
		T v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	/*
	 * public static void sortWithSentinels(Comparable[] a)
	 * {
	 * StdRandom.shuffle(a); // Eliminate dependence on input.
	 * exch(a, max(a), a.length - 1); // To eliminate the test against the
	 * // right array. Also read the note above
	 * sortWithSentinels(a, 0, a.length - 1);
	 * }
	 * 
	 * private static void sortWithSentinels(Comparable[] a, int lo, int hi)
	 * {
	 * if (hi <= lo)
	 * return;
	 * int j = partitionWithSentinels(a, lo, hi); // Partition (see page 291).
	 * sortWithSentinels(a, lo, j - 1); // Sort left part a[lo .. j-1].
	 * sortWithSentinels(a, j + 1, hi); // Sort right part a[j+1 .. hi].
	 * }
	 * 
	 * private static int partitionWithSentinels(Comparable[] a, int lo, int hi)
	 * { // Partition into a[lo..i-1], a[i], a[i+1..hi].
	 * int i = lo, j = hi + 1; // left and right scan indices
	 * Comparable v = a[lo]; // partitioning item
	 * while (true)
	 * { // Scan right, scan left, check for scan complete, and exchange.
	 * while (less(a[++i], v))
	 * ;
	 * while (less(v, a[--j]))
	 * ;
	 * if (i >= j)
	 * break;
	 * exch(a, i, j);
	 * }
	 * exch(a, lo, j); // Put v = a[j] into position
	 * return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	 * }
	 */
}