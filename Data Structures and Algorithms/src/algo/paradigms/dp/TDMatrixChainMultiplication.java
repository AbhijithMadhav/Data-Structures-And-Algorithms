package algo.paradigms.dp;
public class TDMatrixChainMultiplication
{
	private int n; // Number of matrices in the chain
	private int m[][]; // minimal number of scalar multiplications needed to
						// multiply the matrix product Ai...Aj
	private int split[][]; // The split at which the matrix sequence Ai...Aj is
							// parenthesized

	/**
	 * 
	 * @param p Contains the sequence <p0, p1, ..., pn> containing the orders
	 *            of the sequence of matrices. The matrix Ai in the sequence has
	 *            an order
	 *            p[i-1] * p[i]
	 */
	public TDMatrixChainMultiplication(int p[])
	{
		n = p.length - 1;
		m = new int[n + 1][n + 1]; // the 0'th row and column are not used
		split = new int[n + 1][n + 1]; // the 0'th row and column are not used

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
			{
				if (i == j)
					m[i][j] = 0;
				else
					m[i][j] = Integer.MAX_VALUE;
			}
		matrixChainOrder(p, 1, n);
	}

	private int matrixChainOrder(int p[], int i, int j)
	{
		if (m[i][j] < Integer.MAX_VALUE)
			return m[i][j];

		for (int k = i; k < j; k++)
		{
			int min = matrixChainOrder(p, i, k) + matrixChainOrder(p, k + 1, j)
					+ p[i - 1] * p[k] * p[j];
			if (min < m[i][j])
			{
				m[i][j] = min;
				split[i][j] = k;
			}
		}
		return m[i][j];
	}

	public int minCost(int i, int j) throws Exception
	{
		if (i > j)
			throw new Exception("Invalid Matrix sequence");
		if (i > n || j > n)
			throw new Exception("Non existent matrix specified");

		return m[i][j];
	}

	public int split(int i, int j) throws Exception
	{
		if (i > j)
			throw new Exception("Invalid Matrix sequence");
		if (i > n || j > n)
			throw new Exception("Non existent matrix specified");

		return split[i][j];
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		int p[] = { 30, 35, 15, 5, 10, 20, 25 };
		TDMatrixChainMultiplication td = new TDMatrixChainMultiplication(p);

		System.out.println("Number of scalar multiplications");
		for (int i = 1; i < p.length; i++)
		{
			for (int j = i; j < p.length; j++)
				System.out.print(td.minCost(i, j) + " ");
			System.out.println();
		}

		System.out.println("\nSplit");
		for (int i = 1; i < p.length; i++)
		{
			for (int j = i + 1; j < p.length; j++)
				System.out.print(td.split(i, j) + " ");
			System.out.println();
		}
	}
}