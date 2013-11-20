package ds.graphs;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;
public class EdgeWeightedGraph
{
	private int V;
	private int E;
	private List<WeightedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (List<WeightedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<WeightedEdge>();
	}

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(In in)
	{
		this.V = in.readInt();
		int E = in.readInt();
		adj = (List<WeightedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<WeightedEdge>();
		for (int i = 0; i < E; i++)
			addEdge(new WeightedEdge(in.readInt(), in.readInt(), in.readDouble()));
	}

	public int V()
	{
		return V;
	}

	public int E()
	{
		return E;
	}

	public void addEdge(WeightedEdge e)
	{
		int v = e.either();
		adj[v].add(e);
		int w = e.other(v);
		adj[w].add(e);
		E++;
	}

	public Iterable<WeightedEdge> adj(int v)
	{
		return adj[v];
	}

	public Iterable<WeightedEdge> edges()
	{
		List<WeightedEdge> edges = new LinkedList<WeightedEdge>();

		for (int v = 0; v < V(); v++)
			for (WeightedEdge e : adj[v])
				if (e.other(v) > v)
					edges.add(e);
		return edges;
	}

	public String toString()
	{
		String s = new String();
		s += V() + " vertices " + E() + " edges\n";
		for (int v = 0; v < V(); v++)
		{
			s += v + ": ";
			for (WeightedEdge e : adj[v])
				s += e + ", ";
			s += "\n";
		}
		return s;
	}

	public static void main(String[] args)
	{
		EdgeWeightedGraph G = new EdgeWeightedGraph(new In(args[0]));

		System.out.println("Edge Weighted Graph of " + args[0]);
		System.out.println(G);

	}
}
