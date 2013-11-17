package ds.graphs;
import java.util.LinkedList;

import edu.princeton.cs.introcs.In;

public class BellmanFordSP implements ShortestPaths
{
	// Vertex indexed arrays
	private double distTo[]; // shortest distance to source vertex
	private DirectedEdge edgeTo[]; // edge connecting vertex to the spt

	// queue contains vertices which have thier distTo[]
	// changed in the previous pass
	private LinkedList<Integer> queue;
	private boolean onQ[]; // To ensure that we don't add duplicates
	private int noPasses;

	// null if there are no negative cycles
	private Iterable<Integer> negativeCycle;

	public BellmanFordSP(EdgeWeightedDigraph G, int s)
	{
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		queue = new LinkedList<Integer>();
		onQ = new boolean[G.V()];

		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;

		distTo[s] = 0.0;
		queue.addFirst(s); // enqueue
		onQ[s] = true;
		while (!queue.isEmpty() && !this.hasNegativeCycle())
		{
			int v = queue.removeLast();
			onQ[v] = false;
			relax(G, v);
		}
	}

	private void relax(EdgeWeightedDigraph G, int v)
	{
		for (DirectedEdge e : G.adj(v))
		{
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w])
				{
					queue.addFirst(w);
					onQ[w] = true;
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
				if (edgeTo[v] != null)
					spt.addEdge(edgeTo[v].from(), edgeTo[v].to());
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
		return distTo[v];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ShortestPaths#hasPathTo(int)
	 */
	@Override
	public boolean hasPathTo(int v)
	{
		return distTo[v] != Double.POSITIVE_INFINITY;
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
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
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