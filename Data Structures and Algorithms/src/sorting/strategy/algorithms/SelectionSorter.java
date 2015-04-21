package sorting.strategy.algorithms;

import sorting.Sorter;
public class SelectionSorter<T extends Comparable<T>> extends Sorter<T>
{
	public void sort(T[] a, int lo, int hi)
	{ // Sort a[] into increasing order.
		for (int i = lo; i <= hi; i++)
		{ // Exchange a[i] with smallest entry in
			// a[i+1...hi].
			int min = i; // index of minimal entr.
			for (int j = i + 1; j <= hi; j++)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min); // This is the cause of instability. If the min could just be inserted efficiently selection sort would be stable.
		}

	}
}
