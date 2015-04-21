package sorting;


public abstract class Sorter<T extends Comparable<T>> {

	public abstract void sort(T a[], int lo, int hi);
	
	public void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}

	// exchange if a[j] < a[j]
	protected void compexch(T[] a, int i, int j) {
		if (less(a[j], a[i]))
			exch(a, j, i);
	}

	protected void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected boolean isSorted(T[] a) {
		return isSorted(a, 0, a.length - 1);
	}

	// Test whether the array  entries
	// are in
	// order.
	protected boolean isSorted(T[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[i], a[i - 1]))
				return false;
		return true;
	}

	protected boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
	
	protected boolean lessOrEqual(T v, T w) {
		return v.compareTo(w) <= 0;
	}

	protected int max(T[] a) {
		return max(a, 0, a.length - 1);
	}

	protected int max(T[] a, int lo, int hi) {
		int max = lo;
		for (int i = lo + 1; i <= hi; i++)
			if (less(a[max], a[i]))
				max = i;
		return max;

	}

	protected int min(T[] a) {
		return min(a, 0, a.length - 1);
	}

	protected int min(T[] a, int lo, int hi) {
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
/*	public void putFewDistinctKeysInput(T[] a) {
		try {
			int maxDistinctKeys = (int) Math.ceil(Math.cbrt((double) a.length));
			putFewDistinctKeysInput(a,
					Math.abs((new Random()).nextInt(maxDistinctKeys)));
		} catch (IllegalArgumentException e) {
			System.out.println(e + "\n SortHelper : PutFewDistinctKeysInput");
			System.exit(1);
		}
	}*/

	/**
	 * Populates 'a' with an unsorted sequence of integers which have a maximum
	 * of 'maxDistinctKeys' unique keys
	 * 
	 * @param a
	 *            Container for the to-be-generated unsorted elements
	 * @param maxDistinctKeys
	 *            Maximum number of distinct keys allowed
	 */
/*	protected void putFewDistinctKeysInput(T[] a,
			int maxDistinctKeys) {
		Random r = new Random();
		for (int i = 0; i < a.length; i++)
			a[i] = Math.abs(r.nextInt(maxDistinctKeys));
	}

	/**
	 * Populate a[] with random input
	 */
/*	public void putRandomInput(T[] a) {
		Random r = new Random();
		for (int i = 0; i < a.length; i++)
			a[i] = Math.abs(r.nextInt());
	}*/

/*	public void putReverseSortedInput(T[] a) {
		for (int i = a.length; i > 0; i--)
			a[i] = i;
	}*/

	/**
	 * Populate a[] with sorted input
	 */
/*	public void putSortedInput(T[] a) {
		for (int i = 0; i < a.length; i++)
			a[i] = i;
	}*/

/*	public void putTwoDistinctKeysOnlyInput(T[] a) {
		putFewDistinctKeysInput(a, 2);
	}
*/

	/**
	 * For indirect sort
	 */
	public void show(T[] a, int[] perm) { // Print the array, on a
														// single line.
		for (int i = 0; i < a.length; i++)
			System.out.print(a[perm[i]] + " ");
		System.out.println();
	}
	
	protected boolean isSorted(T[] a, int[] perm) {
		return isSorted(a, perm, 0, perm.length - 1);
	}

	protected boolean isSorted(T[] a, int[] perm, int lo, int hi) { 
		for (int i = lo; i <= hi; i++)
			if (less(a[perm[i]], a[perm[i - 1]]))
				return false;
		return true;
	}
}
