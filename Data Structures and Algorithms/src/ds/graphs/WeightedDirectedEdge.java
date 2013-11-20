package ds.graphs;


public class WeightedDirectedEdge implements Comparable<WeightedDirectedEdge>
{
	private int from;
	private int to;
	private double weight;
	
	public WeightedDirectedEdge(int v, int w, double weight)
	{
		from = v;
		to = w;
		this.weight = weight;
	}
	
	public int from()
	{
		return from;
	}
	
	public int to()
	{
		return to;
	}
	
	public double weight()
	{
		return weight;
	}
	public String toString()
	{
		return String.format("%d->%d %.2f", from, to, weight);
	}
	
	public int compareTo(WeightedDirectedEdge e)
	{
		if (weight < e.weight)
			return -1;
		else if (weight > e.weight)
			return +1;
		else
			return 0;
	}
}
