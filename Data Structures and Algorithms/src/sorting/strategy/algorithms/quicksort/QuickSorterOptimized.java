package sorting.strategy.algorithms.quicksort;

import sorting.Sorter;
import sorting.strategy.algorithms.insertionsort.InsertionSorter;
import edu.princeton.cs.introcs.StdRandom;

public class QuickSorterOptimized<T extends Comparable<T>> extends Sorter<T>
{
	/*
	 * 2.3.17 Sentinels. Modify the code in Algorithm 2.5 to remove both bounds
	 * checks in the inner while loops. The test against the left end of the
	 * subarray is redundant since the partitioning item acts as a sentinel (v
	 * is never less than a[lo]). To enable removal of the other test, put an
	 * item whose key is the largest in the whole array into a[length-1] just
	 * after the shuffle. This item will never move (except possibly to be
	 * swapped with an item having the same key) and will serve as a sentinel in
	 * all subarrays involving the end of the array.
	 * 
	 * Note : When sorting interior subarrays, the leftmost entry in the
	 * subarray to the right serves as a sentinel for the right end of the
	 * subarray.
	 */

	/*
	 * 2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to
	 * quicksort, as described in the text (see page 296). Run doubling tests to
	 * determine the effectiveness of the change.
	 */
	private static int CUTOFF = 10;

	private void sortWithMedianOf3PartitioningAndSentinelsAndCutoff(
			T[] a)
	{
		StdRandom.shuffle(a); // Eliminate dependence on input.
		sortWithMedianOf3PartitioningAndSentinelsAndCutoff(a, 0, a.length - 1);
	}

	private void sortWithMedianOf3PartitioningAndSentinelsAndCutoff(
			T[] a, int lo, int hi)
	{
		if (hi - lo + 1 <= CUTOFF)
		{
			Sorter<T> insertionSorter = new InsertionSorter<>();
			insertionSorter.sort(a, lo, hi);
			return;
		}

		int mid = lo + (hi - lo) / 2; // mid has to be calculated relative to lo

		/*
		 * The partitioning is most efficient if the partitioning element
		 * divides the array into equal halves. There is a greater probability
		 * of achieving this if a median is used instead of the first element
		 * being picked as the partitioning element.
		 * 
		 * Here the median is calculated using 3 random elements. Picking the
		 * first, last and middle element as sample elements is random enough.
		 */

		/*
		 * Calculation of median. As a bonus, use the sample items(the element
		 * greater then and lesser than the median) as sentinels at the ends of
		 * the array and remove both array bounds tests in partition().
		 */

		// place the lowest element at lo
		compexch(a, lo, mid);
		compexch(a, lo, hi);

		// place the median at mid and the highest at hi
		compexch(a, mid, hi);

		// now place it at lo + 1
		exch(a, mid, lo + 1); // the median(in terms of the position) is
								// now the partitioning element

		// To eliminate the test against the right array. Also read the note above
		//exch(a, max(a), a.length - 1); 
		
		int j = partitionWithSentinels(a, lo + 1, hi - 1); // Partition
		// Sort left part a[lo..j-1].
		sortWithMedianOf3PartitioningAndSentinelsAndCutoff(a, lo, j - 1);
		// Sort right part a[j+1..hi].
		sortWithMedianOf3PartitioningAndSentinelsAndCutoff(a, j + 1, hi);
	}
	
	private int partitionWithSentinels(T[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		T v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				;
			while (less(v, a[--j]))
				;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}

	@Override
	public void sort(T[] a, int lo, int hi) {
		sortWithMedianOf3PartitioningAndSentinelsAndCutoff(a, lo, hi);
	}
	
	public void sort(T[] a) {
		sortWithMedianOf3PartitioningAndSentinelsAndCutoff(a);
	}


}