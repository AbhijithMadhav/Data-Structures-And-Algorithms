package sorting.mergesort;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.princeton.cs.introcs.StdRandom;
import sorting.common.SimpleLineChart;
import sorting.common.SortHelper;

/*
 * 2.2.6 Write a program to compute the exact value of the number of array accesses used
 * by top-down mergesort and by bottom-up mergesort. Use your program to plot the val-
 * ues for N from 1 to 512, and to compare the exact values with the upper bound 6N lg N.
 */

@SuppressWarnings("rawtypes")
public class ArrayAccessesInMergeSort
{
	/**
	 * Creates The dataset for the graph by running merg sort on different
	 * arrays.
	 * 
	 * @return The dataset containing information on the number of arrays access
	 *         vs the number of elements in the array
	 */
	private static XYDataset createDataset()
	{
		XYSeries actual = new XYSeries("Actual number of accesses");
		XYSeries upperBound = new XYSeries(
				"Upper Bound on the number of accesses  - 6N * lg(N)");

		for (int k = 1; k <= 512; k++)
		{
			Double a[] = new Double[k];
			for (int i = 0; i < k; i++)
				a[i] = StdRandom.uniform();
			int numAccess = sort(a);
			assert SortHelper.isSorted(a);
			actual.add(k, numAccess);

			// upper bound on the number of accesses is 6N lg(N)
			upperBound.add(
					k,
					Math.floor(6 * a.length * Math.log((double) a.length)
							/ Math.log(2.0)));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(actual);
		dataset.addSeries(upperBound);
		return dataset;
	}

	// Merge a[lo..mid] with a[mid+1..hi]. uses aux[]. Returns the number of
	// array accesses
	private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid,
			int hi)
	{
		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		// number of array access
		int numAccess = 0;

		// Copy a[lo..hi] to aux[lo..hi]. 2 access per copy
		for (int k = lo; k <= hi; k++, numAccess += 2)
			aux[k] = a[k];

		// Merge back to a[lo..hi].
		for (int k = lo; k <= hi; k++, numAccess += 2)
			if (i > mid) // 1st half is exhausted.
				a[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted.
				a[k] = aux[i++];
			else
			{ // 2 accesses if the elements are compared
				numAccess += 2;
				if (SortHelper.less(aux[j], aux[i]))
					a[k] = aux[j++];
				else
					a[k] = aux[i++];
			}
		return numAccess;
	}

	public static int sort(Comparable[] a)
	{
		// local aux to avoid a static aux[] for the class as this is a library
		Comparable[] aux = new Comparable[a.length];
		return sort(a, aux, 0, a.length - 1);
	}

	// sorts and returns the number of array accesses done
	private static int sort(Comparable[] a, Comparable[] aux, int lo, int hi)
	{ // Sort a[lo..hi].
		int numAccess = 0;
		if (hi <= lo)
			return 0;
		int mid = lo + (hi - lo) / 2;
		numAccess += sort(a, aux, lo, mid); // Sort left half.
		numAccess += sort(a, aux, mid + 1, hi); // Sort right half.
		numAccess += merge(a, aux, lo, mid, hi); // Merge results (code on page
													// 271).
		return numAccess;
	}

	public static void main(String[] args)
	{
		XYDataset dataset = createDataset();
		SimpleLineChart demo = new SimpleLineChart(
				"Merge Sort - Number of array accesses", dataset,
				"N - Number of elements", "A(N) - Number of array accesses");
		demo.createAndShowChart();
	}

}