package sorting.mergesort;

import sorting.common.SortHelper;
import sorting.insertion.InsertionSortOptimized;
// TO do - which insertion sort to use
@SuppressWarnings("rawtypes")
public class MergeSortOptimized
{
	/*
	 * Optimized sort
	 * ------------------------------------------------------------
	 * --------------
	 */
	/*
	 * 2.2.11 Improvements. Implement the three improvements to mergesort that
	 * are de- scribed in the text on page 275: Add a cutoff for small
	 * subarrays, test whether the array is already in order, and avoid the copy
	 * by switching arguments in the recursive code.
	 */

	// value below which a subarray is insertion sorted
	private final static int CUTOFF = 15;

	// Merge src[lo..mid] with src[mid+1..hi] and place it in dst[lo..hi]
	// Note that the definition of merge here is different from the others
	// There given a[] and aux[], you used to copy a[] to aux[] and then merge
	// back to a[]. Here given src[] and dst[] you just merge src[] to dst[]
	private static void mergeWithoutCopy(Comparable[] src, Comparable[] dst,
			int lo, int mid, int hi)
	{// Though 'mid' can be calculated in the body, it is sent as a parameter as
     //  one may always not want to merge 2 equal subarrays. Look at bottem up
     //  merge sort

		// i and j is used to point to the currently processing element in
		// the 1st and 2nd half respectively
		int i = lo, j = mid + 1;
			// Merge back to a[lo..hi]
			for (int k = lo; k <= hi; k++)
				if (i > mid) // 1st half exhausted
					dst[k] = src[j++];
				else if (j > hi) // 2nd half exhausted
					dst[k] = src[i++];
				else if (SortHelper.less(src[j], src[i])) // Only this instruction counts 
                                               //  for the comparisons accounted
                                               //   in the analysis
					dst[k] = src[j++];
				else
					dst[k] = src[i++];
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo,
			int hi)
	{ // Sort a[lo..hi].
		if (hi - lo + 1 <= CUTOFF)
		{ // Improvement - 1
			// sort using insertion sort if subarray is small
			InsertionSortOptimized.sort(a, lo, hi);
			return;
		}

		int mid = lo + (hi - lo) / 2; // mid has to be calculated relative to lo
		// sort both halves into aux[] so that they can then be merged into a[]
		sort(aux, a, lo, mid); // Sort left half.
		sort(aux, a, mid + 1, hi); // Sort right half.

		// Improvement - 2
		// No need for merge if array is already sorted
		if (SortHelper.lessOrEqual(aux[mid], aux[mid + 1]))
			System.arraycopy(aux, lo, a, lo, hi - lo + 1);
		else
			// merge aux[lo..mid] and aux[mid+1..hi] to a[lo..mid]
			// here aux[] is src and a[] is dst
			mergeWithoutCopy(aux, a, lo, mid, hi);
	}

	public static void optimizedSort(Comparable[] a)
	{
		// faster copy instead of using a 'for' loop
		Comparable[] aux = a.clone();
		sort(a, aux, 0, a.length - 1);
	}
}
