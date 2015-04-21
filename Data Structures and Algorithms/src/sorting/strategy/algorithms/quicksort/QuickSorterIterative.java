package sorting.strategy.algorithms.quicksort;

import edu.princeton.cs.introcs.StdRandom;

import java.util.Stack;

import sorting.Sorter;


public class QuickSorterIterative<T extends Comparable<T>> extends Sorter<T>
{
	/*
	 * 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of
	 * quicksort based on a main loop where a subarray is popped from a stack to
	 * be partitioned, and the re- sulting subarrays are pushed onto the stack.
	 * Note : Push the larger of the subarrays onto the stack first, which
	 * guarantees that the stack will have at most lg N entries.
	 */
	public void sort(T[] a, int lo, int hi)
	{
		StdRandom.shuffle(a);
		Stack<ArrayBounds> s = new Stack<ArrayBounds>();
		s.push(new ArrayBounds(lo, hi));
		do
		{
			ArrayBounds p = s.pop();
			int h = p.getHi();
			int l = p.getLo();

			int j = partition(a, l, h);
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
	
	private int partition(T[] a, int lo, int hi)
	{ // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		T v = a[lo]; // partitioning item
		while (true)
		{ // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
				;
			// if (j == lo) // redundant as v = a[lo] is never less than a[lo]
			// break;
			if (i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j); // Put v = a[j] into position
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

