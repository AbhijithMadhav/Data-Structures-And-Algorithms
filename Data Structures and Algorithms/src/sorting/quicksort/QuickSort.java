package sorting.quicksort;

import edu.princeton.cs.introcs.StdRandom;
import sorting.common.SortHelper;

@SuppressWarnings("rawtypes")
public class QuickSort
{
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a); // Eliminate dependence on input.
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		 sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		 sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
	}

	static int partition(Comparable[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortHelper.less(a[++i], v))
				if (i == hi)
					break;
			while (SortHelper.less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			SortHelper.exch(a, i, j);
		}
		SortHelper.exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	
	static int partition(Comparable[] a, int lo, int hi, int pivotIndex)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		SortHelper.exch(a, lo, pivotIndex);
		Comparable v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortHelper.less(a[++i], v))
				if (i == hi)
					break;
			while (SortHelper.less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			SortHelper.exch(a, i, j);
		}
		SortHelper.exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
	

	public static void main(String[] args)
	{
		String s = "BBB";
		Comparable[] a = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
			a[i] = s.charAt(i);
		
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
		sort(a);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
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