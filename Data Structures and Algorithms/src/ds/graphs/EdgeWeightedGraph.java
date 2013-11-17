package ds.graphs;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;
public class EdgeWeightedGraph
{
	private int V;
	private int E;
	private List<Edge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (List<Edge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<Edge>();
	}

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(In in)
	{
		this.V = in.readInt();
		int E = in.readInt();
		adj = (List<Edge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<Edge>();
		for (int i = 0; i < E; i++)
			addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
	}

	public int V()
	{
		return V;
	}

	public int E()
	{
		return E;
	}

	public void addEdge(Edge e)
	{
		int v = e.either();
		adj[v].add(e);
		int w = e.other(v);
		adj[w].add(e);
		E++;
	}

	public Iterable<Edge> adj(int v)
	{
		return adj[v];
	}

	public Iterable<Edge> edges()
	{
		List<Edge> edges = new LinkedList<Edge>();

		for (int v = 0; v < V(); v++)
			for (Edge e : adj[v])
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
			for (Edge e : adj[v])
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
