package ds.graphs;


public class Edge implements Comparable<Edge>
{
	private int v;
	private int w;
	private double weight;

	public Edge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public double weight()
	{
		return weight;
	}

	public int either()
	{
		return v;
	}

	public int other(int v)
	{
		if (v == this.v)
			return this.w;
		else if (v == this.w)
			return this.v;
		else
			throw new RuntimeException("Inconsistant edge");
	}

	@Override
	public int compareTo(Edge that)
	{
		if (this.weight < that.weight)
			return -1;
		if (this.weight > that.weight)
			return +1;
		else
			return 0;
	}

	public String toString()
	{
		return String.format("%d-%d %.2f", v, w, weight);
	}
}
