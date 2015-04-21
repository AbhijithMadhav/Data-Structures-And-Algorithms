package sorting.strategy.algorithms;

import sorting.Sorter;

public class CountingSorter extends Sorter<Integer>{

	public void sort(Integer[] a, int low, int hi) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void sort(Integer[] a) {
		// auxillary array
		int b[] = new int[a.length];
		
		// c[] contains information about the count an element i in a[]
		int k = max(a);
		int c[] = new int[k + 1];
		
		for (int i = 0; i <= k; i++)
			c[i] = 0;
		
		for (int i = 0; i < a.length; i++)
			c[a[i]]++;
		// c[i] contains the count of the number of elements equal to i in a[]
		
		
		for (int i = 1; i <=k; i++)
			c[i] += c[i-1];
		// c[i] contains the count of he number of elements less than or equal
		//   to i in a[]
		// Note that the information in c[] can now be used to answer any query 
		// about how many of the n integers fall into a given range, say [x...y]
		// in O(1) time, i.e., c[y] - c[x]
		
		
		// Starting from the last element in a[] place each element in its 
		//  sorted position based on the number of elements less than or equal
		//  to it.
		// Starting from the last element helps preserve the stability of the
		//  array.
		//  As c[a[i]] gives the number of elements less than or equal to a[i],
		//  in case there are duplicate elements(a[i] in this case), the 
		//  In case there are non-distinct elements, say x, c[x] gives a count
		//  which can be translated to the position of the last occurrence of
		//  x in a[]
		for (int i = a.length - 1; i >=0; i--)
		{
			b[c[a[i]] - 1] = a[i];
			c[a[i]]--;
		}
		
		// Copy back
		for (int i = 0; i < a.length; i++)
			a[i] = b[i];
	}
}
