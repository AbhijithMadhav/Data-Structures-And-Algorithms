package sorting.quicksort;

import edu.princeton.cs.introcs.StdRandom;
import sorting.common.SortHelper;
import java.util.Stack;

// to do - which insertion sort to use
@SuppressWarnings("rawtypes")
public class QuickSortIterative 
{
	/*
	 * 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of
	 * quicksort based on a main loop where a subarray is popped from a stack to
	 * be partitioned, and the re- sulting subarrays are pushed onto the stack.
	 * Note : Push the larger of the subarrays onto the stack first, which
	 * guarantees that the stack will have at most lg N entries.
	 */
	public static void sortIterative(Comparable[] a)
	{
		StdRandom.shuffle(a);
		Stack<ArrayBounds> s = new Stack<ArrayBounds>();
		s.push(new ArrayBounds(0, a.length - 1));
		do
		{
			ArrayBounds p = s.pop();
			int hi = p.getHi();
			int lo = p.getLo();

			int j = partition(a, lo, hi);
			if (j - 1 - lo <= hi - (j + 1))
			{
				if (j + 1 < hi)
					s.push(new ArrayBounds(j + 1, hi));
				if (j - 1 > lo)
					s.push(new ArrayBounds(lo, j - 1));
			}
			else
			{
				if (j - 1 > lo)
					s.push(new ArrayBounds(lo, j - 1));
				if (j + 1 < hi)
					s.push(new ArrayBounds(j + 1, hi));
			}
		} while (!s.empty());
	}
	
	private static int partition(Comparable[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (SortHelper.less(a[++i], v))
				if (i == hi)
					break;
			while (SortHelper.less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			SortHelper.exch(a, i, j);
		}
		SortHelper.exch(a, lo, j); // Put v = a[j] into position
		return j; // with a[lo..j-1] <= a[j] <= a[j+1..hi].
	}
}

class ArrayBounds
{
	private int hi;
	private int lo;

	ArrayBounds(int lo, int hi)
	{
		this.lo = lo;
		this.hi = hi;
	}

	public int getHi()
	{
		return hi;
	}

	public int getLo()
	{
		return lo;
	}
}

