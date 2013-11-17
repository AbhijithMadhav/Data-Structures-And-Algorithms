package algo.paradigms.greedy.mst;


import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;

import edu.princeton.cs.introcs.In;
import ds.graphs.Edge;
import ds.graphs.EdgeWeightedGraph;

/**
 * Eager version of Prim's algorithm to find the minimum spanning tree of a
 * graph
 * 
 * @author kempa
 * 
 */
public class PrimMST
{
	private Vertex vertex[];
	private PriorityQueue<Vertex> pq;

	private double weight; // weight of the MST
	private Collection<Edge> mst; // Iterator containing the edges of the MST

	public PrimMST(EdgeWeightedGraph G)
	{
		vertex = new Vertex[G.V()];
		for (int v = 0; v < G.V(); v++)
		{
			vertex[v] = new Vertex(v, Double.POSITIVE_INFINITY);
		}
		pq = new PriorityQueue<>();
		
		// MST can be constructed starting at any vertex. Starting with 0 here.
		// That being the case, the only candidate adjacent to the empty MST is
		// this start vertex itself. So insert it into the PQ
		vertex[0].distToMST = 0.0;
		pq.add(vertex[0]);

		// The process of finding the MST ends when all vertices are included in
		// the MST. This is true only when the PQ is empty
		while (!pq.isEmpty())
		{
			// The vertex at the smallest distance from the incomplete MST is a
			// part of the MST through that corresponding edge.
			Vertex v = pq.remove();
			v.inMST = true;

			// Now that v is a part of the MST,
			// 1. The smallest distance of adjacent vertices of v to the MST
			// may reduce. Check and update
			// 2. Some vertices from among those adjacent to v may become
			// adjacent to the MST. Need to insert those into the PQ
			update(G, v);
		}

		// Prepare the MST iterator and calculate the weight
		mst = new LinkedList<Edge>();
		for (int v = 1; v < G.V(); v++)
		{
			mst.add(vertex[v].edgeToMST);
			weight += vertex[v].distToMST;
		}

	}

	// Update the shortest-distances-to-MST of vertices adjacent to v
	// Insert newly adjacent-to-MST vertices into the PQ
	private void update(EdgeWeightedGraph G, Vertex v)
	{
		// for all adjacent vertices
		for (Edge e : G.adj(v.name))
		{
			// adjacent vertex
			Vertex w = vertex[e.other(v.name)];
			
			if (!w.inMST) // w is already in the mst. Should not insert it into
							// the PQ
			{
				// update the distances of all adjacent vertices to the MST
				if (w.distToMST > e.weight())
				{
					w.distToMST = e.weight();
					w.edgeToMST = e;
					if (pq.contains(w))
					{
						// Reflecting change inside the PQ
						pq.remove(w);
						pq.add(w);
					}
					else
						pq.add(w);
				}
			}
		}
	}

	public Iterable<Edge> edges()
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
		PrimMST mst = new PrimMST(new EdgeWeightedGraph(new In(args[0])));

		System.out.println("Minimum spanning tree of " + args[0]);
		for (Edge e : mst.edges())
			System.out.println(e);
		System.out.println("Weight : " + mst.weight());

	}
}

class Vertex implements Comparable<Vertex>
{
	int name;
	double distToMST;// Minimal cost from a vertex to the MST
	Edge edgeToMST; // The minimal costing edge connecting
	// the vertex to the MS
	// why do I need this?
	boolean inMST; // is the vertex on the MST?
	
	public Vertex(int name, double distToMST)
	{
		this.name = name;
		this.distToMST = distToMST;
		this.edgeToMST = null;
		this.inMST = false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Vertex o)
	{
		if (distToMST > o.distToMST)
			return 1;
		else if (distToMST < o.distToMST)
			return -1;
		return 0;
			
	}
}