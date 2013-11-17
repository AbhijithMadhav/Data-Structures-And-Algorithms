package sorting.quicksort;

import sorting.common.SortHelper;
import java.lang.Comparable;

/*
 * 2.3.5 Give a code fragment that sorts an array that is known to consist
 * of items having just two distinct keys.
 */

@SuppressWarnings("rawtypes")
public class ArrayWithOnlyTwoDistinctKeysSortUsingQuickSort
{
	public static void sort(Comparable[] a)
	{
		partition(a, 0, a.length - 1);
	}

	@SuppressWarnings("unchecked")
	private static int partition(Comparable[] a, int lo, int hi)
	{ // Partition into a[lo..lt-1], a[lt], a[lt+1..hi].

		int lt = lo; // partitioning index
		int gt = hi; // elements to the right of gt will be greater than the
							// pivot
		int i = lo;// scanning index
		while (i <= gt)
		{
			int cmp = a[i].compareTo(a[lt]);
			if (cmp < 0) 
				SortHelper.exch(a, i++, lt++);
			else if (cmp > 0)
				SortHelper.exch(a, i, gt--);
			else
				// equal(a[lt], a[i])
				i++;
		}
		return lt;
	}
}