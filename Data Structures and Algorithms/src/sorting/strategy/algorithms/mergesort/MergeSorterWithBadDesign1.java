package sorting.strategy.algorithms.mergesort;

import sorting.Sorter;

@SuppressWarnings("rawtypes")
public class MergeSorterWithBadDesign1<T extends Comparable<T>> extends Sorter<T>
{
	// auxiliary array for merges. Bad design
	private static Comparable[] aux;

	// Merge a[lo..mid] with a[mid+1..hi].
	@SuppressWarnings("unchecked")
	private static void merge(Comparable[] a, int lo, int mid, int hi)
	{
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
			else if (aux[j].compareTo(aux[i]) < 0)
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	public void sort(T[] a)
	{
		aux = new Comparable[a.length]; // Allocate space just once.
		sort(a, 0, a.length - 1);
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