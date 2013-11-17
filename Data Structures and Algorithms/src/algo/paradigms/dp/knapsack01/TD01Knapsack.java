package algo.paradigms.dp.knapsack01;
/**
 * An implementation of the bounded 0-1 knapsack solution using a top-down
 * approach with memoization
 * 
 * @author kempa
 * 
 */
public class TD01Knapsack
{
	private int vKnapsack[]; // value of the knapsack of capacity 'i'
	private String contentsKnapsack[]; // contents of the knapsack of capacity
										// 'i'
	private boolean inKnapsack[]; // is the i'th item in the knapsack?

	public TD01Knapsack(int capacity, int w[], int v[])
	{
		int n = w.length;

		// capacity ranges from 0 to 'capacity'
		vKnapsack = new int[capacity + 1];
		contentsKnapsack = new String[capacity + 1];
		for (int i = 0; i <= capacity; i++)
		{
			vKnapsack[i] = Integer.MIN_VALUE;
			contentsKnapsack[i] = "";
		}
		vKnapsack[0] = 0; // base case : knapsack of capacity 0 has no value

		// initially there are no items in the knapsack
		inKnapsack = new boolean[n];
		for (int i = 0; i < n; i++)
			inKnapsack[i] = false;

		knapsack(n, capacity, w, v);
	}

	private int knapsack(int n, int capacity, int w[], int v[])
	{
		// dynamic programming
		if (vKnapsack[capacity] != Integer.MIN_VALUE)
			return vKnapsack[capacity];

		// Find the optimal value that can carried in a knapsack of given
		// capacity
		// The optimal value that can be carried is the maximum of the optimal
		// subproblem solutions.
		// Each subproblem here represents a choice of including one of the
		// items in the knapsack and optimally packing into the rest of the
		// knapsack
		int max = 0;
		for (int i = 0; i < n; i++)
		{
			// remove this to get a solution for the unbounded problem
			if (inKnapsack[i])
				continue;
		
			// Is there space(non-negative) in the knapsack to add item i
			int space = capacity - w[i];
			if (space < 0)
				continue;

			// Result of optimally packing in the remaining space
			int t = knapsack(n, space, w, v);

			// optimal solution is the max of all subproblems.
			if (v[i] + t > max)
			{
				max = v[i] + t;
				contentsKnapsack[capacity] = w[i] + " "
						+ contentsKnapsack[space];
				inKnapsack[i] = true;
			}

		}

		vKnapsack[capacity] = max;
		return vKnapsack[capacity];
	}

	public static void main(String[] args)
	{
		int capacity = 17;
		int w[] = { 3, 4, 7, 8, 9 };
		int v[] = { 4, 5, 10, 11, 13 };
		TD01Knapsack td = new TD01Knapsack(capacity, w, v);
		System.out.println(capacity + " : " + td.vKnapsack[capacity] + " : "
				+ td.contentsKnapsack[capacity]);

	}

}
