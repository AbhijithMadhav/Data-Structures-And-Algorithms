package sorting.common;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author kempa
 * 
 */
@SuppressWarnings("rawtypes")
final public class SortHelper
{

	private SortHelper()
	{
		
	}
	// exchange if a[j] < a[j]
	public static void compexch(Comparable[] a, int i, int j)
	{
		if (less(a[j], a[i]))
			exch(a, j, i);
	}

	public static void exch(Comparable[] a, int i, int j)
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static LinkedList<String> getAlgorithmList()
	{
		LinkedList<String> l = new LinkedList<String>();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(
					"sortAlgorithms.lst"));

			String algo;
			while ((algo = in.readLine()) != null)
				l.add(algo);
			in.close();
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
		return l;
	}

	public static LinkedList<String> getInputNatureList()
	{
		LinkedList<String> l = new LinkedList<String>();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(
					"nature-of-input.lst"));

			String algo;
			while ((algo = in.readLine()) != null)
				l.add(algo);
			in.close();
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
		}
		return l;
	}

	public static boolean isSorted(Comparable[] a)
	{
		return isSorted(a, 0, a.length - 1);
	}

	public static boolean isSorted(Comparable[] a, int lo, int hi)
	{ // Test whether the array entries are in order.
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	public static boolean isSorted(Comparable[] a, int[] perm)
	{
		return isSorted(a, perm, 0, perm.length - 1);
	}

	public static boolean isSorted(Comparable[] a, int[] perm, int lo, int hi)
	{ // Test whether the array entries are in order.
		for (int i = lo; i <= hi; i++)
			if (less(a[perm[i]], a[perm[i - 1]]))
				return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}

	@SuppressWarnings({ "unchecked" })
	public static boolean lessOrEqual(Comparable v, Comparable w)
	{
		return v.compareTo(w) <= 0;
	}

	public static int max(Comparable[] a)
	{
		return max(a, 0, a.length - 1);
	}

	public static int max(Comparable[] a, int lo, int hi)
	{
		int max = lo;
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[max], a[i]))
				max = i;
		return max;

	}

	public static int min(Comparable[] a)
	{
		return min(a, 0, a.length - 1);
	}

	public static int min(Comparable[] a, int lo, int hi)
	{
		int min = lo;
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[min]))
				min = i;
		return min;
	}

	/**
	 * Populates 'a' with an unsorted sequence of integers. The maximum number
	 * of distinct keys is restricted to (a.length)^1/3
	 * 
	 * @param a
	 *            Container for the to-be-generated unsorted elements
	 */
	public static void putFewDistinctKeysInput(Comparable[] a)
	{
		try
		{
			int maxDistinctKeys = (int) Math.ceil(Math.cbrt((double) a.length));
			putFewDistinctKeysInput(a,
					Math.abs((new Random()).nextInt(maxDistinctKeys)));
		}
		catch (IllegalArgumentException e)
		{
			System.out.println(e + "\n SortHelper : PutFewDistinctKeysInput");
			System.exit(1);
		}
	}

	/**
	 * Populates 'a' with an unsorted sequence of integers which have a maximum
	 * of 'maxDistinctKeys' unique keys
	 * 
	 * @param a
	 *            Container for the to-be-generated unsorted elements
	 * @param maxDistinctKeys
	 *            Maximum number of distinct keys allowed
	 */
	protected static void putFewDistinctKeysInput(Comparable[] a,
			int maxDistinctKeys)
	{
		Random r = new Random();
		for (int i = 0; i < a.length; i++)
			a[i] = Math.abs(r.nextInt(maxDistinctKeys));
	}

	/**
	 * Populate a[] with random input
	 */
	public static void putRandomInput(Comparable[] a)
	{
		Random r = new Random();
		for (int i = 0; i < a.length; i++)
			a[i] = Math.abs(r.nextInt());
	}

	public static void putReverseSortedInput(Comparable[] a)
	{
		for (int i = a.length; i > 0; i--)
			a[i] = i;
	}

	/**
	 * Populate a[] with sorted input
	 */
	public static void putSortedInput(Comparable[] a)
	{
		for (int i = 0; i < a.length; i++)
			a[i] = i;
	}

	public static void putTwoDistinctKeysOnlyInput(Comparable[] a)
	{
		putFewDistinctKeysInput(a, 2);
	}

	public static void show(Comparable[] a)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	/**
	 * For indirect sort
	 */
	public static void show(Comparable[] a, int[] perm)
	{ // Print the array, on a single line.
		for (int i = 0; i < a.length; i++)
			System.out.print(a[perm[i]] + " ");
		System.out.println();
	}
}