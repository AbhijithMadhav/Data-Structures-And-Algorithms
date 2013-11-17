package ds.graphs;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;

public class EdgeWeightedDigraph
{
	private int V;
	private int E;
	private List<DirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V)
	{
		this.V = V;
		adj = (List<DirectedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<DirectedEdge>();
	}

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(In in)
	{
		this.V = in.readInt();
		int E = in.readInt();

		adj = (List<DirectedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<DirectedEdge>();

		for (int i = 0; i < E; i++)
		{
			int from = in.readInt();
			int to = in.readInt();
			double weight = in.readDouble();
			adj[from].add(new DirectedEdge(from, to, weight));
		}

	}

	public int V()
	{
		return V;
	}

	public int E()
	{
		return E;
	}

	public void addEdge(DirectedEdge e)
	{
		adj[e.from()].add(e);
		E++;
	}

	public Iterable<DirectedEdge> adj(int v)
	{
		return adj[v];
	}

	public Iterable<DirectedEdge> edges()
	{
		List<DirectedEdge> b = new LinkedList<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				b.add(e);
		return b;
	}

	public Digraph digraph(EdgeWeightedDigraph G)
	{
		Digraph g = new Digraph(G.V());

		try
		{
			for (DirectedEdge e : G.edges())
				g.addEdge(e.from(), e.to());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return g;
	}

	public String toString()
	{
		String s = new String();
		s += V() + " vertices " + E() + " edges\n";
		for (int v = 0; v < V(); v++)
		{
			s += v + ": ";
			for (DirectedEdge e : adj[v])
				s += e + ", ";
			s += "\n";
		}
		return s;
	}
}