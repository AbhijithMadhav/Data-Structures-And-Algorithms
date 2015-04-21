package sorting.mergesort;

import sorting.common.SortHelper;


public class MergeSortIndirect<T extends Comparable<T>>
{
	/*
	 * 2.2.20 Indirect sort. Develop and implement a version of mergesort that
	 * does not re- arrange the array, but returns an int[] array perm such that
	 * perm[i] is the index of the i th smallest entry in the array.
	 */

	// Merge a[lo..mid] with a[mid+1..hi].
	private void mergeIndirect(T[] a, int[] perm, int[] aux,
			int lo, int mid, int hi)
	{
		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		// Copy perm[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = perm[k];

		// Merge back to perm[lo..hi].
		for (int k = lo; k <= hi; k++)
			if (i > mid) // 1st half is exhausted.
				perm[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted.
				perm[k] = aux[i++];
			else if (SortHelper.less(a[aux[j]], a[aux[i]]))
				perm[k] = aux[j++];
			else
				perm[k] = aux[i++];
	}

	public int[] sort(T[] a)
	{
		// Allocate space just once. Non-static aux used
		int[] aux = new int[a.length];
		int[] perm = new int[a.length];
		for (int i = 0; i < a.length; i++)
			perm[i] = i;
		indirectSort(a, perm, aux, 0, a.length - 1);
		return perm;
	}

	private void indirectSort(T[] a, int[] perm, int[] aux,
			int lo, int hi)
	{ // Sort perm[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		indirectSort(a, perm, aux, lo, mid); // Sort left half.
		indirectSort(a, perm, aux, mid + 1, hi); // Sort right half.
		mergeIndirect(a, perm, aux, lo, mid, hi); // Merge results (code on page
													// 271).
	}
}
