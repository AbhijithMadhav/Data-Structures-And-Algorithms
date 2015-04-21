package sorting.strategy.algorithms.insertionsort;
import java.lang.Comparable;

import sorting.Sorter;
import sorting.common.SortHelper;

public class NaiveInsertionSorter<T extends Comparable<T>> extends Sorter<T>
{
	@Override
	public void sort(T[] a, int lo, int hi)
	{// Sort a[] into increasing order.
		for (int i = lo + 1; i <= hi; i++)
		{ // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			for (int j = i; j > 0 && SortHelper.less(a[j], a[j - 1]); j--)
				SortHelper.exch(a, j, j - 1);
		}
	}
	
	@Override
	public  void sort(T[] a)
	{
		sort(a, 0, a.length - 1);
	}
}