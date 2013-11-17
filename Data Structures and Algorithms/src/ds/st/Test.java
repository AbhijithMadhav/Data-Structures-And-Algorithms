package ds.st;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author kempa
 * 
 */
public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int a = 11;
		int alphabets[] = { 19, 5, 1, 18, 3, 8, 24, 13, 16, 12 };
		int[] hash = new int[alphabets.length];
		for (int M = 3;; M++)
		{
			for (int i = 0; i < alphabets.length; i++)
			{
				hash[i] = (a * alphabets[i]) % M;
			}
			Arrays.sort(hash);
			boolean duplicate = false;
			for (int i = 0; i < hash.length - 1; i++)
			{
				// System.out.println(alphabets[i] + " " + hash[i]);
				if (hash[i] == hash[i + 1])
					duplicate = true;
			}
			if (!duplicate)
			{
				System.out.println(M + " Does produce a perfect hash");
				for (int i = 0; i < hash.length - 1; i++)
					System.out.println(alphabets[i] + " " + hash[i]);
				System.exit(0);
			}
			else ;
				//System.out.println(M + " Does not produce a perfect hash");
		}

	}
}
