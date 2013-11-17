package sorting.shellsort;

import sorting.common.SortHelper;
import java.lang.Comparable;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class ShellSort
{

	/*
	 * 2.1.11 Implement a version of shellsort that keeps the increment sequence
	 * in an array, rather than computing it.
	 */
	public static void sortStoreIncrementsInArray(Comparable[] a)
	{
		// Sort a[] into increasing order.
		int N = a.length;

		Vector<Integer> h = new Vector<Integer>();
		for (int i = 1; i < N / 3; i = 3 * i + 1)
			h.add(i); // 1, 4, 13, 40, 121, 364, 1093, ...

		// h-sort the array.
		for (int k = (h.size() - 1); k >= 0; k--)
		{
			// Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
			for (int i = h.elementAt(k); i < N; i++)
				for (int j = i; j >= h.elementAt(k)
						&& SortHelper.less(a[j], a[j - h.elementAt(k)]); j -= h
						.elementAt(k))
					SortHelper.exch(a, j, j - h.elementAt(k));
		}
	}

	public static void sort(Comparable[] a)
	{
		// Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...

		// h-sort the array.
		while (h >= 1)
		{
			// Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && SortHelper.less(a[j], a[j - h]); j -= h)
					SortHelper.exch(a, j, j - h);
			h = h / 3;
		}
	}

	/*
	 * 2.1.12 Instrument shellsort to print the number of compares divided by
	 * the array size for each increment. Write a test client that tests the
	 * hypothesis that this number is a small constant, by sorting arrays of
	 * random Double values, using array sizes that are increasing powers of 10,
	 * starting at 100.
	 */
	public static Vector<IncrCmp> sortInstrumented(Comparable[] a)
	{
		// Sort a[] into increasing order.

		int N = a.length;
		int h = 1;
		int nIncrements = 0;
		while (h < N / 3)
		{
			h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
			nIncrements++;
		}

		Vector<IncrCmp> v = new Vector<IncrCmp>(nIncrements);

		// h-sort the array.
		while (h >= 1)
		{
			// Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
			int nCmp = 0;
			for (int i = h; i < N; i++)
				for (int j = i; j >= h && SortHelper.less(a[j], a[j - h]); j -= h, nCmp++)
					SortHelper.exch(a, j, j - h);
			v.add(new IncrCmp(h, nCmp));
			h = h / 3;
		}
		return v;
	}

	public static void main(String s[])
	{
		Character a[] = {'S', 'H', 'E', 'L', 'L', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
		
		sort(a);
		
		for (char ch : a)
			System.out.print(ch + " ");
		System.out.println();
		
	}
}