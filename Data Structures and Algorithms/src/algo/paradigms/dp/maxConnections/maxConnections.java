package algo.paradigms.dp.maxConnections;

public class maxConnections {

	public static int connect(int a[], int al, int ar, int b[], int bl, int br)
	{
		int max = 0;
		for (int i = al; i <= ar; i++)
		{
			int j = find(b, bl, br, a[i]);
			if (j == -1)
				continue;
			int n = 0;
			if (i - 1 >= 0 && j - 1 >= 0)
				n += connect(a, al, i - 1, b, bl, j - 1);
			if (i + 1 < a.length && j + 1 < b.length)
				n += connect(a, i + 1, ar, b, j + 1, br);
			if (max < n + 1)
				max = n + 1;
		}
		return max;
	}
	
	
	public static int find(int array[], int left, int right, int key)
	{
		int index = -1;
		for (int i = left; i <= right; i++)
			if (array[i] == key)
				return i;
		return index;
	}
	
	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 4, 5};
		int b[] = { 1, 2, 3, 4, 5};
		System.out.println(connect(a, 0, a.length - 1, b, 0, b.length - 1));
	}
}
