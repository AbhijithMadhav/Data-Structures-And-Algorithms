package sorting.mergesort;

import sorting.common.SortHelper;


@SuppressWarnings("rawtypes")
public class MergeSortGeneric<T extends Comparable<T>>
{
	private T a[];
	private T aux[];

	@SuppressWarnings("unchecked")
	public MergeSortGeneric(T a[])
	{
		this.a = a;
		aux = (T[]) new Comparable[a.length];
	}

	// Merge a[lo..mid] with a[mid+1..hi].
	private void merge(int lo, int mid, int hi)
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
			else if (SortHelper.less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	public void sort()
	{
		sort(0, a.length - 1);
		for (T i : a)
		{
			System.out.println(i);
		}
	}

	private void sort(int lo, int hi)
	{ // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(lo, mid); // Sort left half.
		sort(mid + 1, hi); // Sort right half.
		merge(lo, mid, hi); // Merge results (code on page 271).
	}

	public static void main(String s[])
	{
		Integer a[] = { 1, 2, 5, 90, 43, -9, 78 };
		String s1[] = { "sdfa", "weoriu", "xcvb,mhnaksd", "haksyoqwh",
				"zdkfhjdsk" };
		MergeSortGeneric ms = new MergeSortGeneric<Integer>(a);
		ms.sort();
		ms = new MergeSortGeneric<String>(s1);
		ms.sort();
	}
}
