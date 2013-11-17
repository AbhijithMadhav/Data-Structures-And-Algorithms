package algo.paradigms.dp;
/**
 * 
 */

/**
 * @author kempa
 * 
 */
public class Test
{
	public static int max_sum(int a[])
	{
		if (a.length < 1)
			System.exit(1);

		int s = a[0], max = a[0];
		int prev = s;

		for (int i = 1; i < a.length; i++)
		{
			prev = s;
			s += a[i];

			if (s > max)
				max = s;
			if (s < prev)
				s = 0;
		}
		return max;
	}

	public static void main(String s[])
	{
		// int a[] = { -1};
		// int a[] = {100, -4, -1, -1, 100, 0, 100, -1, -1, -5, 100};
		// int a[] = {100, -2, -1, -1, 100, 0, 100, -3, -1, -1, 100, 0, 100,
		// -4, -1, -1, 100,};
		// int a[] = {0, 0, 0};
		int a[] = { 100, -2, -1, -1, 100 };
		// int a[] = { 100, 2, 1, 1, 100 };
		System.out.println(max_product(a));

		// static int a[] = {1, 2, 3, 4, -1, 6};
		// static int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
		// static int a[] = {-100, -3, -1, -1, -2, -1, -1, -3};
		// static int a[] = {1};
		// max_sum(a);
	}

	public static int max_product(int a[])
	{
		int p = a[0], m = p > Integer.MIN_VALUE ? p : Integer.MIN_VALUE, p1 = 1;
		boolean flag = false;

		/**
		 * Implementing using a single for loop
		 * 
		 * Maintain a running product in p. This takes care of the max product
		 * in case there are even number of positive numbers.
		 * 
		 * Reset the running p
		 * 
		 * The
		 * Let a[i] and a[j] be the first and last -ve number.
		 * Calculate and update the products a[0]...a[j-1] and a[i+1]...a[n-1]
		 * on the fly and update
		 */
		for (int j = 1; j < a.length; j++)
		{
			if (p == 0)
			{
				// A zero prompts the start of the product from the beginning
				p = a[j];
				flag = false;
			}
			else
				p = p * a[j];

			if (p < 0 && !flag)
			{
				p1 = p;
				flag = true;
			}

			if (p > m)
				m = p;
			if (p / p1 > m)
				m = p / p1;

			// System.out.println(a[j] + " " + p + " " + p / p1 + " " + m);
		}
		return m;
	}
}