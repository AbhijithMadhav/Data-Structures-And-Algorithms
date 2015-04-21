package sorting.quicksort;
import java.util.Random;

import sorting.strategy.algorithms.quicksort.QuickSorter;


/**
 * @author kempa
 * 
 */
public class RSelect<T extends Comparable<T>>
{
	/**
	 * Finds the element with the given rank in the given unordered array in
	 * O(n) time
	 * 
	 * @param a Unordered array
	 * @param r Rank of the desired element
	 * @param i Index of leftmost element of a
	 * @param j Index of rightmost element of a
	 * @return The element with rank r
	 */
	T select(T a[], int r, int i, int j)
	{
		int n = a.length;
		int pIndex = findPivot(a, i, j);
		int k = new QuickSorter<T>().partition(a, i, j, pIndex);

		int rPivot = n - k + 1;
		if (r == rPivot)
			return a[r];
		else if (r < rPivot)
			return select(a, r, k + 1, j);
		else if (r > rPivot)
			return select(a, r - (n - k + 1), i, k - 1);
		return null;

	}

	/**
	 * Gets a pivot in a randomized fashion. To ensure that the pivot is a
	 * "good" one.
	 * A "good" pivot is one which ensures that the array is partitioned to a
	 * 1:4 ratio in the worst case.
	 * The expected number of trials of a random number generator to get this
	 * is 2
	 * 
	 * @param a Unordered array
	 * @param i Index of leftmost element of a
	 * @param j Index of rightmost element of a
	 * @return Index of a "good" pivot
	 */
	int findPivot(Comparable<?> a[], int i, int j)
	{
		Random r = new Random();
		int k =(i + (Math.abs(r.nextInt()) % (j - i))); 
		System.out.println(k);
		return k;
	}

	public static void main(String s[])
	{
		Integer a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		RSelect<Integer> rselect = new RSelect<>();
		for (int rank = 1; rank < 9; rank++)
		{
			System.out.println("Element with rank " + rank + " "
					+ rselect.select(a, 1, 0, a.length - 1));
		}
	}
}