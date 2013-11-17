package sorting.insertion;
import java.lang.Comparable;

import sorting.common.SortHelper;

@SuppressWarnings("rawtypes")
public class InsertionSort
{
	public static void sort(Comparable[] a, int lo, int hi)
	{// Sort a[] into increasing order.
		for (int i = lo + 1; i <= hi; i++)
		{ // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			for (int j = i; j > 0 && SortHelper.less(a[j], a[j - 1]); j--)
				SortHelper.exch(a, j, j - 1);
		}
	}
	
	public static void sort(Comparable[] a)
	{
		sort(a, 0, a.length - 1);
	}
}