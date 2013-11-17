package sorting.selectionsort;

import sorting.common.SortHelper;
@SuppressWarnings("rawtypes")
public class SelectionSort
{
	public static void sort(Comparable[] a)
	{ // Sort a[] into increasing order.
		int N = a.length;
		// array length
		for (int i = 0; i < N; i++)
		{ // Exchange a[i] with smallest entry in
			// a[i+1...N).
			int min = i; // index of minimal entr.
			for (int j = i + 1; j < N; j++)
				if (SortHelper.less(a[j], a[min]))
					min = j;
			SortHelper.exch(a, i, min); // This is the cause of instability. If the min could just be inserted efficiently selection sort would be stable.
		}

	}
}
