package ds.array;
/**
 * @author kempa
 * 
 *         The Sieve of Eratosthenes marks all the prime numbers upto a given
 *         number.
 *         Arrays as underlying data structures are suitable because of the
 *         non-linear
 *         access in the algorithm
 */
public class SieveOfEratosthenes
{
	// Each entry of the array tells if its index is prime or non-prime
	private boolean a[];

	public SieveOfEratosthenes(int n)
	{
		a = new boolean[n + 1];
		for (int i = 1; i <= n; i++) // n iterations
			a[i] = true;

		// An iteration marks all numbers which are multiples of i as non prime
		for (int i = 2; i < n; i++)
		{
			// If a number, c, is already marked prime then its multiples are
			//  already marked prime by the factors of c earlier
			if (a[i])
				for (int j = 2; i * j <= n; j++) // n/2 + n/3 + n/5 + n/7 + n/11 + .... iterations
					a[i * j] = false; // non-linear access
		}
	}

	public String toString()
	{
		String s = "";
		for (int i = 2; i < a.length; i++)
			s += (a[i] ? i + " " : "");
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SieveOfEratosthenes s = new SieveOfEratosthenes(13);
		System.out.println(s);
	}
}
