package sorting.bubblesort;

import sorting.common.SortHelper;

@SuppressWarnings("rawtypes")
public class BubbleSort
{
	public static void sort(Comparable[] a)
	{ // Sort a[] into increasing order.
		
		int N = a.length;
		
		boolean sorted = false;
		for (int i = 0 ; i < N && !sorted; i++)
		{// Bubble the i'th smallest element up if the array is still unsorted
			
			sorted = true;
			for (int j = N - 1; j > i; j--)
				if (SortHelper.less(a[j], a[j - 1]))
				{
					SortHelper.exch(a, j, j-1);
					sorted = false;
				}
		}
	}
	
	public static void main(String s[])
	{
		Integer a[] = {9, 3, 5, 1 , 8, 7, 4};
		sort(a);
		for (int i = 0; i < a.length; i++)
		System.out.println(a[i]);
	}
	
}
