package algo.paradigms.dp;
public class BUMatrixChainMultiplication
{
	private int n; // Number of matrices in the chain
	private int m[][]; // minimal number of scalar multiplications needed to
	                   // multiply the matrix product Ai...Aj
	private int split[][]; // The split at which the matrix sequence Ai...Aj is
						   //  parenthesized
	
	/**
	 * 
	 * @param p Contains the sequence <p0, p1, ..., pn> containing the orders
	 *  of the sequence of matrices. The matrix Ai in the sequence has an order
	 *  p[i-1] * p[i]
	 */
	public BUMatrixChainMultiplication(int p[])
	{
		n = p.length - 1;
		m = new int[n + 1][n + 1]; // the 0'th row and column are not used
		split = new int[n + 1][n + 1]; // the 0'th row and column are not used
		matrixChainOrder(p);
	}
	
	private void matrixChainOrder(int p[])
	{
		for (int i = 1; i <= n; i++)
			m[i][i] = 0;
		
		/*
		 * The bottom up calculation needs to proceed only in the upper 
		 * triangle and that too diagonal-wise as
		 * 	1. i < j and m[i][j] for i > j does not makes sense
		 *  2. The diagonal represents the solution of the smallest
		 *     sub-problems. The elements above it(diagonal wise) represent the
		 *     next bigger subproblems which make use of the solutions below 
		 *     them and so on. Proceeding diagonal wise solves all smaller 
		 *     subproblems first which are needed by the larger subproblems later.
		 */
		for (int d = 2; d <=n; d++) // for each diagonal
		{
			 // step through the elements of the diagonal
			for(int i = 1, j = d; j <=n ; j++, i++)
			{
				m[i][j] = Integer.MAX_VALUE;
				// the number of scalar multiplications is the minimum of all
				// types of parenthesizations
				for(int k = i; k < j; k++)
				{
					int min = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if ( min < m[i][j]) {
						m[i][j] = min;
						split[i][j] = k;
					}
					
				}
			}
		}
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
		int p[] = {30, 35, 15, 5, 10, 20, 25};
		BUMatrixChainMultiplication bu = new BUMatrixChainMultiplication(p);
		
		System.out.println("Number of scalar multiplications");
		for ( int i = 1; i < p.length; i++) {
			for ( int j = i; j < p.length; j++)
				System.out.print(bu.minCost(i, j) + " ");
			System.out.println();
		}
		
		System.out.println("\nSplit");
		for ( int i = 1; i < p.length; i++) {
			for ( int j = i; j < p.length; j++)
				System.out.print(bu.split(i, j) + " ");
			System.out.println();
		}
	}
}