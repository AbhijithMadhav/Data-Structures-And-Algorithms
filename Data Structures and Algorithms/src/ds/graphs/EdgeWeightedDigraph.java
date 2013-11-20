package ds.graphs;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;

public class EdgeWeightedDigraph
{
	private int V;
	private int E;
	private List<WeightedDirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V)
	{
		this.V = V;
		adj = (List<WeightedDirectedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<WeightedDirectedEdge>();
	}

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(In in)
	{
		this.V = in.readInt();
		int E = in.readInt();

		adj = (List<WeightedDirectedEdge>[]) new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<WeightedDirectedEdge>();

		for (int i = 0; i < E; i++)
		{
			int from = in.readInt();
			int to = in.readInt();
			double weight = in.readDouble();
			adj[from].add(new WeightedDirectedEdge(from, to, weight));
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
	
	public Iterable<WeightedDirectedEdge> pred(int v)
	{
		List<WeightedDirectedEdge> pred = new LinkedList<>();
		for (int i = 0; i < V(); i++)
		{
			for (WeightedDirectedEdge e : adj(i))
				if (e.to() == v)
					pred.add(e);
		}
		return pred;
		
	}

	public void addEdge(WeightedDirectedEdge e)
	{
		adj[e.from()].add(e);
		E++;
	}

	public Iterable<WeightedDirectedEdge> adj(int v)
	{
		return adj[v];
	}

	public Iterable<WeightedDirectedEdge> edges()
	{
		List<WeightedDirectedEdge> b = new LinkedList<WeightedDirectedEdge>();
		for (int v = 0; v < V; v++)
			for (WeightedDirectedEdge e : adj[v])
				b.add(e);
		return b;
	}

	public Digraph digraph()
	{
		Digraph g = new Digraph(V());

		try
		{
			for (WeightedDirectedEdge e : edges())
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
			for (WeightedDirectedEdge e : adj[v])
				s += e + ", ";
			s += "\n";
		}
		return s;
	}
}