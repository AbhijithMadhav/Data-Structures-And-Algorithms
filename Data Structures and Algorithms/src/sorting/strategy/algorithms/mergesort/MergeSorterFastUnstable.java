package sorting.strategy.algorithms.mergesort;

import sorting.Sorter;

public class MergeSorterFastUnstable<T extends Comparable<T>> extends Sorter<T>
{
	/*
	 * 2.2.10 Faster merge. Implement a version of merge() that copies the
	 * second half of a[] to aux[] in decreasing order and then does the merge
	 * back to a[]. This change allows you to remove the code to test that each
	 * of the halves has been exhausted from the inner loop. Note: The resulting
	 * sort is not stable (see page 341).
	 */
	// Merge a[lo..mid] with a[mid+1..hi].
	private void mergeFasterNonStable(T[] a, T[] aux,
			int lo, int mid, int hi)
	{

		// Copy a[lo..mid] to aux[lo..mid].
		for (int k = lo; k <= mid; k++)
			aux[k] = a[k];

		// Copy a[mid+1..hi] to aux[hi..mid+1].
		// i.e. copy the 2nd half in decreasing order
		for (int k = hi, m = mid + 1; k > mid; k--, m++)
			aux[k] = a[m];

		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = hi;

		// Merge back to a[lo..hi].
		// No need to check if either halves have been exhausted. If they get
		// exhausted, their respective pointers fall into the other half and all
		// is equally well
		for (int k = lo; k <= hi; k++)
			if (less(aux[j], aux[i]))
				a[k] = aux[j--];
			else
				a[k] = aux[i++];
	}

	public void sort(T[] a)
	{
		// Non-static merge used
		// Allocate space just once.
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];
		sortFasterNonStable(a, aux, 0, a.length - 1);
	}

	private void sortFasterNonStable(T[] a, T[] aux,
			int lo, int hi)
	{ // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sortFasterNonStable(a, aux, lo, mid); // Sort left half.
		sortFasterNonStable(a, aux, mid + 1, hi); // Sort right half.
		mergeFasterNonStable(a, aux, lo, mid, hi); // Merge results (code on
													// page 271).
	}

	@Override
	public void sort(T[] a, int lo, int hi) {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];
		sortFasterNonStable(a, aux, lo, hi);
		
	}
}
