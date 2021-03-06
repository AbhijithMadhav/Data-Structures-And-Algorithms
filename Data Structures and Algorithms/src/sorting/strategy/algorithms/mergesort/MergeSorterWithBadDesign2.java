package sorting.strategy.algorithms.mergesort;

import sorting.Sorter;
import sorting.common.SortHelper;
/*
 * 2.2.26 Array creation. Use SortCompare to get a rough idea of the effect
 * on perfor- mance on your machine of creating aux[] in merge() rather than
 * in sort().
 */

public class MergeSorterWithBadDesign2<T extends Comparable<T>> extends Sorter<T>
{
	// Merge a[lo..mid] with a[mid+1..hi].
	private void merge(T[] a, int lo, int mid, int hi)
	{
	// new aux is created everytime merge is called
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];

		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		// Copy a[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		for (int k = lo; k <= hi; k++)
			// Merge back to a[lo..hi].
			if (i > mid) // 1st half is exhausted. Copy the elements of the 2nd
							// half
				a[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted. Copy the elements of the
								// 1st half
				a[k] = aux[i++];
			else if (SortHelper.less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	public void sort(T[] a, int lo, int hi)
	{ // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid); // Sort left half.
		sort(a, mid + 1, hi); // Sort right half.
		merge(a, lo, mid, hi); // Merge results (code on page 271).
	}
}