package algo.paradigms.dp.knapsack01;
public class BU01Knapsack
{
	private int knap[];

	public BU01Knapsack(int n, int capacity, int w[], int v[])
	{
		knap = new int[capacity + 1];
		knap[0] = 0;
		for (int i = 1; i <= capacity; i++)
			knap[i] = Integer.MIN_VALUE;
		knapsack(n, capacity, w, v);
	}

	private void knapsack(int n, int capacity, int w[], int v[])
	{
		for (int c = 1; c <= capacity; c++) // From the smallest subproblem to
											// the
											// biggest
		{
			int max = 0;
			for (int i = 0; i < n; i++)
			{
				if (capacity - w[i] >= 0 && c - w[i] >= 0)
					if (v[i] + knap[c - w[i]] > max)
						max = v[i] + knap[c - w[i]];
			}
			knap[c] = max;
		}
	}

	public static void main(String[] args)
	{
		int capacity = 17;
		int w[] = { 3, 4, 7, 8, 9 };
		int v[] = { 4, 5, 10, 11, 13 };
		BU01Knapsack td = new BU01Knapsack(5, capacity, w, v);
		for (int i = 0; i <= capacity; i++)
			System.out.println(i + " : " + td.knap[i]);

	}


}
