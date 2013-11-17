package algo.paradigms.greedy.scheduling;
/**
 * 
 * @author kempa
 * 
 */
public class ActivitySelection
{
	
	String maximalSet;
	int n;

	public ActivitySelection(int s[], int f[])
	{
		maximalSet = "";
		n = findMaximalActivitySet(0, s, f);
	}

	/**
	 * 
	 * @param k The item with the earliest finish time in the current
	 *            subproblem
	 * @param s
	 * @param f
	 * @return
	 */
	int findMaximalActivitySet(int k, int s[], int f[])
	{
		int n = s.length;

		// find the next compatible activity
		int m;
		for (m = k + 1; m < n; m++)
			if (s[m] >= f[k])
				break;

		if (m < n)
		{
			maximalSet += m + " ";
			return 1 + findMaximalActivitySet(m, s, f);

		}
		return 0;
	}

	public static void main(String string[])
	{
		int s[] = { 0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };
		int f[] = { 0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 };

		ActivitySelection as = new ActivitySelection(s, f);
		System.out.println(as.n + " : " + as.maximalSet);
	}
}
