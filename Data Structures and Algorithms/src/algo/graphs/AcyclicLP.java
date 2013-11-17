package algo.graphs;
import java.util.LinkedList;

import ds.graphs.DirectedEdge;
import ds.graphs.EdgeWeightedDigraph;
import ds.graphs.IShortestPaths;
import algo.graphs.dfs.Topological;
import edu.princeton.cs.introcs.In;

/*
 * Determines the longest path from a source vertex to all others
 */
public class AcyclicLP implements IShortestPaths
{
	private double distTo[];
	private DirectedEdge edgeTo[];

	public AcyclicLP(EdgeWeightedDigraph G, int s)
	{
		distTo = new double[G.V()];
		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.NEGATIVE_INFINITY;
		distTo[s] = 0;
		edgeTo = new DirectedEdge[G.V()];

		Topological topological = new Topological(G.digraph(G));
		for (Integer v : topological.order())
			relax(G, v);
	}

	private void relax(EdgeWeightedDigraph G, int v)
	{
		for (DirectedEdge e : G.adj(v))
		{
			int w = e.to();
			if (distTo[w] < distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}

	public double distTo(int v)
	{
		return distTo[v];
	}

	public boolean hasPathTo(int v)
	{
		return distTo[v] != Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> pathTo(int v)
	{
		if (!hasPathTo(v))
			return null;
		LinkedList<DirectedEdge> stack = new LinkedList<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			stack.addFirst(e);
		return stack;
	}

	public static void main(String[] args)
	{
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		AcyclicLP sp = new AcyclicLP(G, s);

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
}