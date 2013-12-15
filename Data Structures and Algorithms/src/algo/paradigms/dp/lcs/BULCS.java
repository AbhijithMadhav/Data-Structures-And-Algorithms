package algo.paradigms.dp.lcs;

import java.util.LinkedList;


public class BULCS
{
	private int len[][]; // length of LCS of Xi and Yj
	private String X;
	private String Y;

	public BULCS(String X, String Y)
	{
		this.X = X;
		this.Y = Y;

		len = new int[X.length()][Y.length()];

		// base case
		len[0][0] = 0;
		if (X.charAt(0) == Y.charAt(0))
			len[0][0] = 1;

		for (int i = 1; i < X.length(); i++)
		{
			len[i][0] = len[i - 1][0];
			if (X.charAt(i) == Y.charAt(0))
				len[i][0] = 1;
		}

		for (int j = 1; j < Y.length(); j++)
		{
			len[0][j] = len[0][j - 1];
			if (Y.charAt(j) == X.charAt(0))
				len[0][j] = 1;
		}

		lengthLCS();
	}

	private void lengthLCS()
	{
		// Subproblems are encountered in the increasing order of their size if
		// we proceed in the row major order or in column major order
		for (int i = 1; i < X.length(); i++)
			for (int j = 1; j < Y.length(); j++)
				if (X.charAt(i) == Y.charAt(j))
					len[i][j] = len[i - 1][j - 1] + 1;
				else
				{
					if (len[i - 1][j] >= len[i][j - 1])
					{
						len[i][j] = len[i - 1][j];
					}
					else
					{
						len[i][j] = len[i][j - 1];
					}
				}
	}

	public int getLength(int i, int j)
	{
		return len[i][j];
	}

	public String getLCS(int i, int j)
	{
		LinkedList<Character> lst = new LinkedList<>();
		while (i >= 0 && j >= 0)
		{
			if (X.charAt(i) == Y.charAt(j))
			{
				lst.addFirst(X.charAt(i));
				i--;
				j--;
			}
			else
			{
				if (len[i][j] == len[i][j - 1])
					j--;
				else
					i--;
			}
		}

		return lst.toString();
	}

	public static void main(String s[])
	{
		String X = "ABCABBA";
		String Y = "CBABACA";
		BULCS bu = new BULCS(X, Y);
		for (int i = 0; i < X.length(); i++)
		{
			for (int j = 0; j < Y.length(); j++)
				System.out.print(bu.getLength(i, j) + " ");
			System.out.println();
		}

		/*
		 * for (int i = 0; i < X.length(); i++)
		 * {
		 * for (int j = 0; j < Y.length(); j++)
		 * 
		 * System.out.print(bu.getLCS(i, j) + " ");
		 * System.out.println();
		 * }
		 */

		System.out.println(bu.getLCS(X.length() - 1, Y.length() - 1));
	}
}