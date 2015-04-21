package sorting.strategy.algorithms.mergesort;

import sorting.Sorter;

public class MergeSorterBU<T extends Comparable<T>> extends Sorter<T>
{

	// Merge a[lo..mid] with a[mid+1..hi].
	private void merge(T[] a, T[] aux, int lo, int mid,
			int hi)
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
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	// bottom-up mergesort
	public void sortBU(T[] a, T[] aux, int left, int right)
	{        
        // Do lg N passes
		for (int n = 1; n < right - left + 1; n = n + n)
		{// n is the subarray size

            // i < N - n. The number of elements from position lo must be
            //  greater than the subarray size so that a split can occur and 
            //  merge can be done across the split.
            // The split always results in a two sorted arrays as the subarray
            //  size is an exponential multiple of the subarray size of the
            // previous iteration
			for (int lo = left; lo < right + 1 - n; lo += n + n)
			{
				int hi = Math.min(lo + (n + n - 1), right);
				merge(a, aux, lo, lo + n - 1, hi);
			}
		}
	}

	@Override
	public void sort(T[] a, int lo, int hi) {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];
		sortBU(a, aux, lo, hi);
	}
	
	// overridden as need to allocate auxillary array
	@Override
	public void sort(T[] a) {
		// Non static aux[].
		// Allocate space just once.
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length];
		sortBU(a, aux, 0, a.length - 1);
	}
}
