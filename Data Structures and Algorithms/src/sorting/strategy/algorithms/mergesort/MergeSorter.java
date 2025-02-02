package sorting.strategy.algorithms.mergesort;

import sorting.Sorter;

public class MergeSorter<T extends Comparable<T>> extends Sorter<T> {
	/*
	 * 2.2.9 Use of a static array like aux[] is inadvisable in library software
	 * because multiple clients might use the class concurrently. Give an
	 * implementation of Merge that does not use a static array. Do not make
	 * aux[] local to merge() (THis avoids the overhead of creating an array for
	 * every merge, even tiny ones. This cost would dominate the running time of
	 * mergesort. Hint : Pass the auxiliary array as an argument to the
	 * recursive sort().
	 */

	// Merge a[lo..mid] with a[mid+1..hi].
	private void merge(T[] a, T[] aux, int lo, int mid, int hi) {
		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		// Copy a[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		// Merge back to a[lo..hi].
		for (int k = lo; k <= hi; k++)
			if (i > mid) // 1st half is exhausted.
				a[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted.
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	private void sort(T[] a, T[] aux, int lo, int hi) { // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid); // Sort left half.
		sort(a, aux, mid + 1, hi); // Sort right half.
		merge(a, aux, lo, mid, hi); // Merge results (code on page 271).
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort(T[] a, int lo, int hi) {

		// Non static aux[].
		// Allocate space just once.
		T[] aux = (T[]) new Comparable[a.length];
		sort(a, aux, lo, hi);

	}

	// Overridden as auxillary array had to be created
	@Override
	public void sort(T[] a) {
		// Non static aux[].
		// Allocate space just once.
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}
}
