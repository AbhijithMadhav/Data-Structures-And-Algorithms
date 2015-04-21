package sorting;

/*
 * 2.1.26 Primitive types. Develop a version of insertion sort that sorts
 * arrays of int values and compare its performance with the implementation
 * given in the text (which sorts Integer values and implicitly uses
 * autoboxing and auto-unboxing to convert).
 */

public class InsertionSortPrimitiveTypes
{

	public static void sort(double[] a)
	{

		int N = a.length;

		// Sentinel
		for (int i = N - 1; i > 0; i--)
		{
			if (a[i] < a[i - 1])
			{
				double t = a[i];
				a[i] = a[i - 1];
				a[i - 1] = t;
			}
		}
		// Sort a[] into increasing order.

		for (int i = 2; i < N; i++)
		{ // Insert a[i] among a[i-1], a[i-2],
			// a[i-3]... ..
			int j = i;
			double t = a[j];
			try
			{
				while (t < a[j - 1])
				{

					a[j] = a[j - 1];
					j--;
				}
			}
			catch (Exception e)
			{
				System.out.println(j + " " + i);
				System.exit(1);
			}
			a[j] = t;
		}
	}
}