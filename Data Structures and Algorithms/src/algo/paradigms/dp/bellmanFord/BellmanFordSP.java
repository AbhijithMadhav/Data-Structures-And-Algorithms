package algo.paradigms.dp.bellmanFord;
import java.util.LinkedList;

import algo.graphs.dfs.DirectedCycleDFS;
import ds.graphs.Digraph;
import ds.graphs.DirectedEdge;
import ds.graphs.EdgeWeightedDigraph;
import ds.graphs.IShortestPaths;
import edu.princeton.cs.introcs.In;

public class BellmanFordSP implements IShortestPaths
{
	private Vertex vertex[];
	// queue contains vertices which have thier distTo[]
	// changed in the previous pass
	private LinkedList<Vertex> queue;
	private int noPasses;

	// null if there are no negative cycles
	private Iterable<Integer> negativeCycle;

	public BellmanFordSP(EdgeWeightedDigraph G, int s)
	{
		vertex = new Vertex[G.V()];
		queue = new LinkedList<Vertex>();

		for (int i = 0; i < G.V(); i++)
			vertex[i] = new Vertex(i, Double.POSITIVE_INFINITY);

		vertex[s].distTo = 0.0;
		queue.addFirst(vertex[s]);
		vertex[s].onQ = true;
		while (!queue.isEmpty() && !this.hasNegativeCycle())
		{
			Vertex v = queue.removeLast();
			v.onQ = false;
			relax(G, v);
		}
	}

	private void relax(EdgeWeightedDigraph G, Vertex v)
	{
		for (DirectedEdge e : G.adj(v.label))
		{
			Vertex w = vertex[e.to()];
			if (w.distTo > v.distTo + e.weight())
			{
				w.distTo = v.distTo + e.weight();
				w.edgeTo = e;
				if (!w.onQ)
				{
					queue.addFirst(w);
					w.onQ = true;
				}
			}
		}
		if (noPasses == G.V())
			findNegativeCycle(G);
		noPasses++;
	}

	private void findNegativeCycle(EdgeWeightedDigraph G)
	{
		// Create a Digraph from edgeTo[]
		Digraph spt = new Digraph(G.V());
		try
		{
			for (int v = 0; v < G.V(); v++)
				if (vertex[v].edgeTo != null)
					spt.addEdge(vertex[v].edgeTo.from(), vertex[v].edgeTo.to());
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		DirectedCycleDFS cycle = new DirectedCycleDFS(spt);
		if (cycle.hasCycle())
			negativeCycle = cycle.cycle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ShortestPaths#distTo(int)
	 */
	@Override
	public double distTo(int v)
	{
		return vertex[v].distTo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ShortestPaths#hasPathTo(int)
	 */
	@Override
	public boolean hasPathTo(int v)
	{
		return vertex[v].distTo != Double.POSITIVE_INFINITY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ShortestPaths#pathTo(int)
	 */
	@Override
	public Iterable<DirectedEdge> pathTo(int v)
	{
		if (!hasPathTo(v))
			return null;
		LinkedList<DirectedEdge> stack = new LinkedList<DirectedEdge>();
		for (DirectedEdge e = vertex[v].edgeTo; e != null; e = vertex[e.from()].edgeTo)
			stack.addFirst(e);
		return stack;
	}

	public Iterable<Integer> negativeCycle()
	{
		return negativeCycle;
	}

	public boolean hasNegativeCycle()
	{
		return negativeCycle != null;
	}

	public static void main(String[] args)
	{
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		BellmanFordSP sp = new BellmanFordSP(G, s);

		if (!sp.hasNegativeCycle())
		{
			for (int t = 0; t < G.V(); t++)
			{
				System.out.print(s + " to " + t + " ("
						+ String.format("%.2f", sp.distTo(t)) + ") : ");
				if (sp.hasPathTo(t))
					for (DirectedEdge e : sp.pathTo(t))
						System.out.print(e + "  ");
				System.out.println();
			}
		}
		else
		{
			System.out
					.println("Graph has a negative cycle reachable from " + s + ". Can't find the shortest path tree");
			for (int v : sp.negativeCycle())
				System.out.println(v);
		}
	}

}

class Vertex 
{
	int label;
	double distTo; // shortest distance to source vertex
	DirectedEdge edgeTo; // edge connecting vertex to the spt
	boolean onQ; // To ensure that we don't add duplicates
	
	public Vertex(int label, double distTo)
	{
		this.label = label;
		this.distTo = distTo;
	}
}