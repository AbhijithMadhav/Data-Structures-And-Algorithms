package algo.paradigms.greedy;
import java.util.LinkedList;

import ds.graphs.WeightedDirectedEdge;
import ds.graphs.EdgeWeightedDigraph;
import ds.graphs.IShortestPaths;
import algo.graphs.dfs.directed.Topological;
import edu.princeton.cs.introcs.In;

/*
 * Determines the shortest path from a source vertex to all others
 */
public class AcyclicSP implements IShortestPaths
{
	double distTo[];
	WeightedDirectedEdge edgeTo[];

	public AcyclicSP(EdgeWeightedDigraph G, int s)
	{
		distTo = new double[G.V()];
		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		edgeTo = new WeightedDirectedEdge[G.V()];

		Topological topological = new Topological(G.digraph());
		for (Integer v : topological.order())
			relax(G, v);
	}

	private void relax(EdgeWeightedDigraph G, int v)
	{
		for (WeightedDirectedEdge e : G.adj(v))
		{
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight())
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

	public Iterable<WeightedDirectedEdge> pathTo(int v)
	{
		if (!hasPathTo(v))
			return null;
		LinkedList<WeightedDirectedEdge> stack = new LinkedList<WeightedDirectedEdge>();
		for (WeightedDirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			stack.addFirst(e);
		return stack;
	}

	public static void main(String[] args)
	{
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		AcyclicSP sp = new AcyclicSP(G, s);

		for (int t = 0; t < G.V(); t++)
		{
			System.out.print(s + " to " + t + " ("
					+ String.format("%.2f", sp.distTo(t)) + ") : ");
			if (sp.hasPathTo(t))
				for (WeightedDirectedEdge e : sp.pathTo(t))
					System.out.print(e + "  ");
			System.out.println();
		}
	}

}
