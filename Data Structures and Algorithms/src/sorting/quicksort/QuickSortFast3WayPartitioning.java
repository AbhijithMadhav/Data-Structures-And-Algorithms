package sorting.quicksort;

import edu.princeton.cs.introcs.StdRandom;
import sorting.common.SortHelper;
class QuickSortFast3WayPartitioning
{
	public static void sort(Comparable<?>[] a)
	{
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable<?>[] a, int lo, int hi)
	{
		if (hi <= lo)
			return;
		Pair p = partition(a, lo, hi); // Partition (see page 291).
		sort(a, lo, p.i - 1); // Sort left part a[lo .. j-1].
		sort(a, p.j + 1, hi); // Sort right part a[j+1 .. hi].
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Pair partition(Comparable[] a, int lo, int hi)
	{
		//                   3-way partitioning
		//     =           <      unprocessed        >          =
		//[lo ... p-1][p ... i-1][i ........ j][j+1 ... q-1][q ... hi]
		int p = lo, i = lo, j = hi, q = hi;
		
		Comparable v = a[lo];
		while (true)
		{
			int cmp;
			while ((cmp = a[i].compareTo(v)) <= 0)
			{
				if (i == hi)
					break;
				if (cmp == 0)
					SortHelper.exch(a, i, p++);
				else // cmp < 0
					i++;
			}

			while ((cmp = a[i].compareTo(v)) >= 0)
			{
				if (j == lo)
					break;
				if (cmp == 0)
					SortHelper.exch(a, j, q--);
				else // cmp > 0
					j--;
			}
			
			if (i < j)
				SortHelper.exch(a, i, j);
			else 
			{
				// Swap the lesser and greater subarrays with the equal key 
				// subarray into positions
				//     =           <                    >          =
				//[lo ... p-1][p ... i-1][i ... j][j+1 ... q-1][q ... hi]

				System.arraycopy(a, p, a, lo, i - p);
				System.arraycopy(a, j + 1, a, hi - ( q - j + 1) + 1, q - j + 1);
				for (int k = i - p; k < hi - (q - j + 1) + 1; k++)
					a[k] = v;
				
				return new QuickSortFast3WayPartitioning().new Pair(i - p + 1, 
						hi - (q - j + 1) - 1);
			}
		}
	}
	
	public static void main(String[] args)
	{
		String s = "ZSAJFUBAKGLH";
		Comparable<Character>[] a = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
			a[i] = s.charAt(i);
		
		sort(a);
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
	}

	
	public class Pair {
		int i, j;
		public Pair(int i, int j)
		{
			this.i = i;
			this.j = j;
		}
	}
}