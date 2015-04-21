package algo.paradigms.dc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Find the sub array whose sum of elements is the greatest from a given array
 */

/**
 * @author kempa
 * 
 */
public class MaxSubArray
{
	private int a[];
	private Triple t;

	public MaxSubArray(int a[])
	{
		this.a = a;
		t = findMaxSubArray(0, a.length - 1);
	}

	public int[] getMaximumSubArray()
	{
		int array[] = new int[t.hi - t.lo + 1];
		System.arraycopy(this.a, t.lo, array, 0, t.hi - t.lo + 1);
		return array;
	}

	private Triple findMaxSubArray(int lo, int hi)
	{
		// base case
		if (hi == lo)
			return new Triple(lo, hi, a[lo]);

		// Divide and conquer
		int mid = (lo + hi) / 2;
		Triple leftT = findMaxSubArray(lo, mid);
		Triple rightT = findMaxSubArray(mid + 1, hi);

		// Combine
		Triple crossT = findMaxCrossingSubArray(lo, hi);
		if ((leftT.getSum() >= rightT.getSum())
				&& (leftT.getSum() >= crossT.getSum()))
			return new Triple(leftT.getLo(), leftT.getHi(), leftT.getSum());
		else if ((rightT.getSum() >= leftT.getSum())
				&& (rightT.getSum() >= crossT.getSum()))
			return new Triple(rightT.getLo(), rightT.getHi(), rightT.getSum());
		else
			return new Triple(crossT.getLo(), crossT.getHi(), crossT.getSum());

	}

	// Makes use of the fact that the mid element of the given array must be 
	// a part of the max sub array
	private Triple findMaxCrossingSubArray(int lo, int hi)
	{
		int mid = (lo + hi) / 2;
		
		// Assume max sum includes the entire array
		int left = lo;
		int right = hi;

		int leftSum = Integer.MIN_VALUE;
		for (int i = mid, sum = 0; i >= lo; i--)
		{
			sum += a[i];
			if (sum > leftSum)
			{
				leftSum = sum;
				left = i;
			}
		}

		int rightSum = Integer.MIN_VALUE;
		for (int i = mid + 1, sum = 0; i <= hi; i++)
		{
			sum += a[i];
			if (sum > rightSum)
			{
				rightSum = sum;
				right = i;
			}
		}

		return new Triple(left, right, leftSum + rightSum);
	}

	public String toString()
	{
		String s = new String();
		//for (int i = t.getLo(); i < t.getHi(); i++)
			//s += a[i] + " ";
		//s += a[t.getHi()] + "\n";
		s += "sum = " + t.getSum();
		
		return s;
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		//int a[] = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
		
		//MaxSubArray max = new MaxSubArray(a);
		//System.out.println(max);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int a[] = new int[n];
			StringTokenizer tokenizer = new StringTokenizer(br.readLine());
			for (int k = 0; tokenizer.hasMoreTokens(); k++) {
				a[k] = Integer.parseInt(tokenizer.nextToken());
			}
			System.out.println(new MaxSubArray(a));
		}
	}
}

class Triple
{
	int lo, hi, sum, a[];

	public Triple(int lo, int hi, int sum)
	{
		this.lo = lo;
		this.hi = hi;
		this.sum = sum;
	}

	public int getLo()
	{
		return lo;
	}

	public int getHi()
	{
		return hi;
	}
	
	public int getSum()
	{
		return sum;
	}
}
