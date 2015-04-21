package sorting.mergesort;
import sorting.common.SortHelper;

/*
 * 2.2.19 Inversions. Develop and implement a linearithmic algorithm for
 * computing the number of inversions in a given array (the number of
 * exchanges that would be performed by insertion sort for that array—see
 * Section 2.1). This quantity is related to the Kendall tau distance; see
 * Section 2.5.
 */

public class InversionsUsingMergeSort<T extends Comparable<T>>
{
	// Merge a[lo..mid] with a[mid+1..hi].
	private int merge(T[] a, T[] aux,int lo, int mid, int hi)
	{
		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		int inversions = 0;

		// Copy a[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		// Merge back to a[lo..hi].
		for (int k = lo; k <= hi; k++)
			if (i > mid) // 1st half is exhausted.
				a[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted.
				a[k] = aux[i++];
			else if (SortHelper.less(aux[j], aux[i]))
			{
				a[k] = aux[j++];

				// count the number of inversions
				inversions += mid - i + 1;
			}
			else
				a[k] = aux[i++];
		return inversions;
	}

	public int count(T[] a)
	{
		// Allocate space just once. Non-static aux[] used
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[a.length]; 

		int bInversions = brute(a);
		int inversions = count(a, aux, 0, a.length - 1);

		assert bInversions == inversions;

		return inversions;
	}

	private int count(T[] a, T[] aux,int lo, int hi)
	{ // Sort a[lo..hi].
		if (hi <= lo)
			return 0;
		int mid = lo + (hi - lo) / 2;

		int inversions = 0;
		inversions += count(a, aux, lo, mid); // Sort left half.
		inversions += count(a, aux, mid + 1, hi); // Sort right half.
		inversions += merge(a, aux, lo, mid, hi); // Merge results (code on page
												// 271).

		return inversions;
	}

	private int brute(T[] a)
	{
		int inversions = 0;
		for (int i = 1; i < a.length; i++)
			for (int j = i - 1; j >= 0; j--)
				if (SortHelper.less(a[i], a[j]))
					inversions++;

		return inversions;
	}

	public static void main(String args[])
	{ // Generate an array with random elements, count the inversions. print
		// them

		final int MAX_NUM_ELEMENTS = 10000;
		final int MULTIPLIER = 1000;

		int N = Math.min(MAX_NUM_ELEMENTS,
				(int) (Math.random() * MAX_NUM_ELEMENTS));
		Integer[] a = new Integer[N];

		for (int i = 0; i < a.length; i++)
			a[i] = (int) (Math.random() * MULTIPLIER);

		// show(a);

		assert SortHelper.isSorted(a);
		System.out.println("N = " + N + "\nInversions = " + new InversionsUsingMergeSort<Integer>().count(a));
		
	}
}