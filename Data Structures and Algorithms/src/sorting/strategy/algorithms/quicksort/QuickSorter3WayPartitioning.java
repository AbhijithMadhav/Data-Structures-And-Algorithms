package sorting.strategy.algorithms.quicksort;

import sorting.Sorter;
import edu.princeton.cs.introcs.StdRandom;
public class QuickSorter3WayPartitioning<T extends Comparable<T>> extends Sorter<T>
{
	/*
	 * public static void sortWithFastThreeWayPartitioning(Comparable[] a) {
	 * StdRandom.shuffle(a); sortWithFastThreeWayPartitioning(a, 0, a.length -
	 * 1); }
	 * 
	 * @SuppressWarnings("unchecked") private static void
	 * sortWithFastThreeWayPartitioning(Comparable[] a, int lo, int hi) { if (hi
	 * - lo + 1 <= CUTOFF) { InsertionSort.optimizedSort(a, lo, hi); return; }
	 * int p = lo; // a[lo..p-1] has elements equal to the pivot int i = lo; //
	 * a[p..i-1] has elements less than the pivot // a[i..j] has unprocessed
	 * elements int j = hi; // a[j+1..q] has elements greater than the pivot int
	 * q = hi; // a[q+1..hi] has elements equal to the pivot
	 * 
	 * Comparable v = a[lo]; // partitioning item, pivot while (i <= j) // while
	 * there are unprocessed elements { int cmp = a[i].compareTo(v); if (cmp ==
	 * 0) exch(a, i++, p++);
	 * 
	 * if (a[j].compareTo(v) == 0) exch(a, j--, q--);
	 * 
	 * if (cmp < 0) i++; if (cmp > 0) exch(a, i, j--); }
	 * 
	 * // Copy the i-p lesser elements from [p..i-1] to [lo..i-p-1]
	 * System.arraycopy(a, p, a, lo, i - p);
	 * 
	 * // Copy the q-j greater elements from [j+1..q] to [hi-(q-j)+1..hi]
	 * System.arraycopy(a, j + 1, a, hi - (q - j) + 1, q - j);
	 * 
	 * // Write the (p-lo)+(hi-q) equal elements from [lo..p-1] and [q+1..hi] //
	 * to [lo+(i-p)..hi-(q-j)+1] for (int m = lo + (i - p); m < hi - (q - j) +
	 * 1; m++) a[m] = v;
	 * 
	 * // Note: Only the lesser and greater parts of the array need to be //
	 * sorted sortWithFastThreeWayPartitioning(a, lo, lo + i - p - 1); // sort
	 * the // lesser // part sortWithFastThreeWayPartitioning(a, hi - (q - j) +
	 * 1, hi); // sort the // greater // part }
	 */

	public void sort(T[] a, int lo,
			int hi)
	{
		if (hi <= lo)
			return;

		int lt = lo;
		int gt = hi;
		int i = lo;

		T v = a[lo];
		while (i <= gt)
		{
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				exch(a, i++, lt++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}

		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}

	public void sort(T[] a)
	{
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
}