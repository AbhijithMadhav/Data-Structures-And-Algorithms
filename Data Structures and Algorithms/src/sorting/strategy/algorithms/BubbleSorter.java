package sorting.strategy.algorithms;

import sorting.Sorter;

public class BubbleSorter<T extends Comparable<T>> extends Sorter<T>{

	@Override
	public void sort(T[] a, int lo, int hi) 
	{ // Sort a[] into increasing order.
		
		boolean sorted = false;
		for (int i = lo ; i <= hi && !sorted; i++)
		{// Bubble the i'th smallest element up if the array is still unsorted
			
			sorted = true;
			for (int j = hi ; j > i; j--)
				if (less(a[j], a[j - 1]))
				{
					exch(a, j, j-1);
					sorted = false;
				}
		}
	}
}