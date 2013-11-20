package algo.paradigms.greedy.mst;

import ds.uf.UnionFind;
import ds.uf.UnionFindWQU;
import edu.princeton.cs.introcs.In;
import ds.graphs.WeightedEdge;
import ds.graphs.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST
{
	private Queue<WeightedEdge> mst;
	private double weight;

	public KruskalMST(EdgeWeightedGraph G)
	{
		PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>();
		for (WeightedEdge e : G.edges())
			pq.add(e);

		UnionFind uf = new UnionFindWQU(G.V());
		mst = new LinkedList<WeightedEdge>();

		while (!pq.isEmpty())
		{
			WeightedEdge e = pq.remove();
			int v = e.either(), w = e.other(v);

			// 2 find operations
			if (uf.connected(v, w))
				continue; // Adding v->w would result in a cycle as v and w are
							// already connected

			// 1 union operation
			uf.union(v, w);

			mst.add(e);
			weight += e.weight();
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

	public static void main(String[] args)
	{
		KruskalMST mst = new KruskalMST(new EdgeWeightedGraph(new In(args[0])));

		System.out.println("Minimum spanning tree of " + args[0]);
		for (WeightedEdge e : mst.edges())
			System.out.println(e);
		System.out.println("Weight : " + mst.weight());

	}
}
