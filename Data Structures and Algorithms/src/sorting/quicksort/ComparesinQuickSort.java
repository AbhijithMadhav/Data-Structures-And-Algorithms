package sorting.quicksort;

import sorting.common.SimpleLineChart;
import sorting.common.SortHelper;
/*

 * 2.3.6 Write a program to compute the exact value of CN, and compare the
 * exact value with the approximation 2N ln N, for N = 100, 1,000, and
 * 10,000.
 */



import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import edu.princeton.cs.introcs.StdRandom;

//Application frame is the gui container which contains the plotted graph
@SuppressWarnings("rawtypes")
public class ComparesinQuickSort
{
	private static int numCmp = 0;

	/**
	 * Creates The dataset for the graph by running quick sort on different
	 * arrays and by calculating the average number of comparisions.
	 * 
	 * @return The dataset containing information on the number of comparisions
	 *         vs the number of elements in the array
	 */
	private static XYDataset createDataset()
	{
		XYSeries actual = new XYSeries("Actual number of comparsions");
		XYSeries average = new XYSeries(
				"Calculated average number of comparsions  - 2N * ln(N)");

		for (int k = 100; k <= 10000; k *= 10)
		{
			Double a[] = new Double[k];
			for (int i = 0; i < k; i++)
				a[i] = StdRandom.uniform();
			int numCmp = sort(a);
			assert SortHelper.isSorted(a);
			actual.add(k, numCmp);

			// average number of accessses is 2N * ln(N)
			average.add(k,
					Math.floor(2 * a.length * Math.log((double) a.length)));
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(actual);
		dataset.addSeries(average);
		return dataset;
	}

	public static int sort(Comparable[] a)
	{
		StdRandom.shuffle(a); // Eliminate dependence on input.
		numCmp = 0;
		sort(a, 0, a.length - 1);
		return numCmp;
	}

	public static void sort(Comparable[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi); // Partition (see page 291).
		sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
	}

	private static int partition(Comparable[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortHelper.less(a[++i], v))
			{
				numCmp++;
				if (i == hi)
					break;
			}
			while (SortHelper.less(v, a[--j]))
			{
				numCmp++;
				if (j == lo)
					break;
			}
			if (i >= j)
				break;
			SortHelper.exch(a, i, j);
		}
		SortHelper.exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}

	public static void main(String[] args)
	{
		XYDataset dataset = createDataset();
		SimpleLineChart demo = new SimpleLineChart(
				"Quick Sort - Number of comparisions", dataset,
				"N - Number of elements", "C(N) - Number of comparisions");
		demo.createAndShowChart();
	}

}
