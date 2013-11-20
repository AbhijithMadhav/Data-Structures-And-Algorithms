package algo.paradigms.greedy.mst;

import edu.princeton.cs.introcs.In;
import ds.graphs.WeightedEdge;
import ds.graphs.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LazyPrimMST
{
	private PriorityQueue<WeightedEdge> pq; // Contains edges adjacent to the MST
	private List<WeightedEdge> mst; // Edges of the MST
	private boolean marked[]; // is the vertex on the MST?
	private double weight; // weight of the MST 

	public LazyPrimMST(EdgeWeightedGraph G)
	{
		pq = new PriorityQueue<WeightedEdge>();
		mst = new LinkedList<WeightedEdge>();
		marked = new boolean[G.V()];

		// Determine the initial candidates w.r.t. the vertex 0
		// The candidates are inserted into pq
		visit(G, 0);
		while (!pq.isEmpty())
		{
			// Remove the edge with the smallest weight from among the
			// candidates
			WeightedEdge e = pq.remove();
			int v = e.either(), w = e.other(v);

			// Lazy exclusion of ineligible edges(edges already in the mst) from
			// the candidates
			// Note that these edges were eligible when they were selected as
			// candidates as one of their vertices was a non-mst vertex. They
			// have in due course of adding other edges to the mst become
			// ineligible as they now connect vertices in the mst

			// Is the edge already on the mst?
			// Would have been more intuitive if the mst had been a ST instead
			// of a bag. Could have done a mst.exists(). This is more efficient
			// though
			if (marked[v] && marked[w])
				continue;

			// Add e to the mst
			mst.add(e);
			weight += e.weight();

			// Visit the neighbouring vertices to discover the fresh candidates
			// now that a new edge has been added to the MST
			if (!marked[v])
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}
	}

	// add all adjacent edges to v which are **not already** on the mst for
	// consideration
	private void visit(EdgeWeightedGraph G, int v)
	{
		marked[v] = true;
		for (WeightedEdge e : G.adj(v))
		{
			int w = e.other(v);
			if (!marked[w])
				pq.add(e);
		}
	}

	public Iterable<WeightedEdge> edges()
	{
		return mst;
	}

	public double weight()
	{
		return weight;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LazyPrimMST mst = new LazyPrimMST(
				new EdgeWeightedGraph(new In(args[0])));

		System.out.println("Minimum spanning tree of " + args[0]);
		for (WeightedEdge e : mst.edges())
			System.out.println(e);
		System.out.println("Weight : " + mst.weight());

	}
}