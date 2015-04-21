package sorting.strategy.algorithms.insertionsort;
import sorting.Sorter;

/*
 * 2.1.24 Insertion sort with sentinel. Develop an implementation of
 * insertion sort that eliminates the j>0 test in the inner loop by first
 * putting the smallest item into position. Use SortCompare to evaluate the
 * effectiveness of doing so. Note : It is often possible to avoid an
 * index-out-of-bounds test in this way—the element that enables the test to
 * be eliminated is known as a sentinel.
 * 
 * 2.1.25 Insertion sort without exchanges. Develop an implementation of
 * insertion sort that moves larger elements to the right one position with
 * one array access per entry, rather than using exch(). Use SortCompare to
 * evaluate the effectiveness of doing so.
 */

public class InsertionSorter<T extends Comparable<T>> extends Sorter<T>
{

	public void sort(T[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
	// Optimisation 1 : Sentinel to prevent array out of bounds checking
		exch(a, min(a, lo, hi), lo);
		
		// Sort a[] into increasing order.
		for (int i = lo + 2; i <= hi; i++)
		{ // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
			int j = i;
			T t = a[i];
			while (less(t, a[j - 1]))
			{// Optimisation 2: Half exchanges result in half the number of memory accesses
				a[j] = a[j - 1];
				j--;
			}
			a[j] = t;
		}

	}
	
	@Override
	public void sort(T[] a)
	{
		sort(a, 0, a.length - 1);
	}
}