package algo.paradigms.dp.lcs;
public class BULCS
{
	private int m; // length of sequence X
	private int n; // length of sequence Y
	private int c[][]; // length of LCS of Xi and Yj
	private String lcs[][]; // LCS of Xi and Yi

	public BULCS(String X, String Y)
	{
		// The definition of the recursive solution is dependent on the indexing
		// of the sequence starting from 1
		X = " " + X;
		Y = " " + Y;

		m = X.length() - 1;
		n = Y.length() - 1;

		c = new int[X.length()][Y.length()];
		lcs = new String[X.length()][Y.length()];

		// base case : There is no common sequence if only one string is being
		// considered
		for (int i = 1; i <= m; i++)
			c[i][0] = 0;

		for (int j = 1; j <= n; j++)
			c[0][j] = 0;

		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= n; j++)
				lcs[i][j] = "";

		lengthLCS(X, Y);
	}

	private void lengthLCS(String X, String Y)
	{
		// Subproblems are encountered in the increasing order of their size if
		// we proceed in the row major order
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++)
				if (X.charAt(i) == Y.charAt(j))
				{
					c[i][j] = c[i - 1][j - 1] + 1;
					lcs[i][j] += lcs[i - 1][j - 1] + X.charAt(i);
				}
				else
				{
					if (c[i - 1][j] >= c[i][j - 1])
					{
						c[i][j] = c[i - 1][j];
						lcs[i][j] += lcs[i - 1][j];
					}
					else
					{
						c[i][j] = c[i][j - 1];
						lcs[i][j] += lcs[i][j - 1];
					}
				}
	}

	public int getLength(int i, int j)
	{
		return c[i + 1][j + 1];
	}

	public String getLCS(int i, int j)
	{
		return lcs[i + 1][j + 1];
	}

	public static void main(String s[])
	{
		String X = "ABCBDAB";
		String Y = "BDCABA";
		BULCS bu = new BULCS(X, Y);
		for (int i = 0; i < X.length(); i++)
		{
			for (int j = 0; j < Y.length(); j++)
				System.out.print(bu.getLength(i, j) + " ");
			System.out.println();
		}

		for (int i = 0; i < X.length(); i++)
		{
			for (int j = 0; j < Y.length(); j++)

				System.out.print(bu.getLCS(i, j) + " ");
			System.out.println();
		}
	}
}