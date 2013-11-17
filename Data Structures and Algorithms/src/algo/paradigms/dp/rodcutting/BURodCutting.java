package algo.paradigms.dp.rodcutting;
/**
 * A bottom-up implementation of the Rod cutting problem
 * 
 * @author kempa
 * 
 */
public class BURodCutting
{
	int n; // Length of the given rod
	private int p[]; // Price list
	private int r[]; // Table to store maximal revenues
	private String cuts[]; // The cuts which make up the maximal revenue

	public BURodCutting(int n, int price[])
	{
		this.n = n;
		p = price;
		r = new int[price.length];
		cuts = new String[price.length];

		p[0] = 0;
		r[0] = 0;
		cuts[0] = "";

		for (int i = 1; i < price.length; i++)
			r[i] = Integer.MIN_VALUE;

		cutRod(n);
	}

	private void cutRod(int n)
	{
		// For each incrementally greater length of the rod
		// determine the cut which will produce the greatest revenue
		for (int i = 1; i <= n; i++)
		{ 
			int max = Integer.MIN_VALUE;
			
			// The maximal revenue of all possible cuts of length i
			for (int j = 1; j <= i; j++)
				if (p[j] + r[i - j] > max)
				{
					max = p[j] + r[i - j];
					cuts[i] = new String(j + " " + cuts[i - j]);
				}
			r[i] = max;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int p[] = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

		int n = 10;
		BURodCutting bu = new BURodCutting(n, p);
		System.out.println("Read as, Length of Rod : Cuts : Maximal Revenue");
		for (int i = 1; i <= bu.n; i++)
			System.out.println(i + " : " + bu.cuts[i] + " : " + bu.r[i]);
	}
}