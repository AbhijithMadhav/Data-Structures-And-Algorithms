package algo.paradigms.dp.lcs;
public class TDLCS
{
	private int m, n; // Length of the sequences X and Y
	private int l[][]; // length of LCS of Xi and Yj
	private String lcs[][]; // LCS of Xi and Yi

	public TDLCS(String X, String Y)
	{
		// The definition of the recursive solution is dependent on the indexing
		// of the sequence starting from 1
		X = " " + X;
		Y = " " + Y;

		m = X.length() - 1; // length of sequence X
		n = Y.length() - 1; // length of sequence Y

		l = new int[X.length()][Y.length()];
		lcs = new String[X.length()][Y.length()];

		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= n; j++)
			{
				if ( i == 0 || j == 0)
					l[i][j] = 0;
				else
					l[i][j] = Integer.MIN_VALUE;
				lcs[i][j] = "";
			}

		lengthLCS(X, Y, m, n);
	}

	private int lengthLCS(String X, String Y, int m, int n)
	{

		if (l[m][n] != Integer.MIN_VALUE)
			return l[m][n];

		if (X.charAt(m) == Y.charAt(n))
		{
			l[m][n] = lengthLCS(X, Y, m - 1, n - 1) + 1;
			lcs[m][n] += lcs[m - 1][n - 1] + X.charAt(m);
		}
		else
		{
			int l1 = lengthLCS(X, Y, m - 1, n);
			int l2 = lengthLCS(X, Y, m, n - 1);
			if (l1 > l2)
			{
				l[m][n] = l1;
				lcs[m][n] += lcs[m - 1][n];
			}
			else
			{
				l[m][n] = l2;
				lcs[m][n] += lcs[m][n - 1];
			}
		}

		if (l[m][n] == Integer.MIN_VALUE)
		{
			System.out.println(m + " " + n);
			System.exit(1);
		}
		return l[m][n];
	}

	public int getLength(int i, int j)
	{
		return l[i + 1][j + 1];
	}

	public String getLCS()
	{
		return lcs[m][n];
	}

	public static void main(String s[])
	{
		String X = "ABCBDAB";
		String Y = "BDCABA";
		TDLCS bu = new TDLCS(X, Y);
		System.out.print(bu.getLCS());
	}
}