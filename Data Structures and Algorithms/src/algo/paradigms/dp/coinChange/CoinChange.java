/**
 * 
 */
package algo.paradigms.dp.coinChange;

public class CoinChange
{
	private int totalAmount;

	// Subproblem size is based every incremental amount for which change has to
	// be obtained

	// Data structure indexed by subproblem size
	private int n[]; // number of coins.
	private int coin[]; // last coin used        

	public CoinChange(int totalAmount, int denomination[])
	{
		this.totalAmount = totalAmount;
		coin = new int[totalAmount + 1];
		this.n = new int[totalAmount + 1];

		// Base case
		n[0] = 0;
		for (int i = 1; i <= totalAmount; i++)
			n[i] = Integer.MAX_VALUE;

		// Optimal substructure
		for (int amount = 1; amount <= totalAmount; amount++)
			for (int i = 0; i < denomination.length; i++)
				if (amount - denomination[i] >= 0)
					if (n[amount] > n[amount - denomination[i]] + 1)
					{
						n[amount] = n[amount - denomination[i]] + 1;
						coin[amount] = denomination[i];
					}
	}

	public int getNCoins()
	{
		return n[totalAmount];
	}

	public String toString()
	{
		String s = "";
		for (int amount = totalAmount; amount > 0; amount -= coin[amount])
			s += coin[amount] + " ";
		return s;
	}

	public static void main(String s[])
	{
		int denomination[] = { 1, 7, 10 };
		CoinChange cc = new CoinChange(14, denomination);
		System.out.println(cc);
		//for (int i = 0; i <= cc.totalAmount; i++)
			//System.out.print(cc.n[i] + " ");
	}
}
