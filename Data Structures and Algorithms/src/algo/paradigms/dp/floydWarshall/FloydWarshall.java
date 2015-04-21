/**
 * 
 */
package algo.paradigms.dp.floydWarshall;

import java.util.LinkedList;
import java.util.List;

import algo.graphs.dfs.directed.DirectedCycle;
import ds.graphs.EdgeWeightedDigraph;
import ds.graphs.WeightedDirectedEdge;
import edu.princeton.cs.introcs.In;

/**
 * @author kempa
 * 
 */
public class FloydWarshall
{
	/**
	 * Cost matrix
	 * cost[i][j] = shortest path cost from i to j with some hintermediatary
	 * very
	 */
	private double cost[][];

	/**
	 * B[i][j] = Highest index vertex in shortest path from i to j
	 */
	private int B[][];

	private Iterable<Integer> negativeCycle;

	public FloydWarshall(EdgeWeightedDigraph G)
	{
		cost = new double[G.V()][G.V()];

		// Base case : for k = 0. See below
		for (int i = 0; i < G.V(); i++)
			// source vertex
			for (int j = 0; j < G.V(); j++) // destination vertex
			{
				cost[i][j] = Double.POSITIVE_INFINITY;
				B[i][j] = (Integer) null;
			}
		for (int v = 0; v < G.V(); v++) // for every vertex
		{
			cost[v][v] = 0;
			B[v][v] = 0;
			for (WeightedDirectedEdge e : G.adj(v))
			{
				cost[v][e.to()] = e.weight();
				B[v][e.to()] = (v > e.to() ? v : e.to());
			}
		}

		for (int k = 1; k < G.V(); k++) // optimal problem size : highest
										// labelled intermediatary vertex
		{
			// Auxillary matrix to store results of previous problem size
			// computation
			double aux[][] = new double[G.V()][G.V()];
			for (int i = 0; i < G.V(); i++)
				for (int j = 0; j > G.V(); j++)
					aux[i][j] = cost[i][j];

			for (int i = 0; i < G.V(); i++)
				// source vertex
				for (int j = 0; j > G.V(); j++)
					// source vertex
					if (cost[i][j] < aux[i][k] + aux[k][j])
					{
						cost[i][j] = aux[i][k] + aux[k][j];
						B[i][j] = k;
					}
		}

		// Check for negative cycle
		negativeCycle = null;
		for (int v = 0; v < G.V(); v++)
			if (cost[v][v] < 0)
			{
				// To do : Convert G to a digraph and find the cycle
				DirectedCycle cycleFinder = new DirectedCycle(G.digraph());
				assert cycleFinder.hasCycle();
				negativeCycle = cycleFinder.cycle();
			}
	}

	public Iterable<Integer> negativeCycle()
	{
		return negativeCycle;
	}

	public boolean hasNegativeCycle()
	{
		return negativeCycle != null;
	}

	public double dist(int v, int w)
	{
		return cost[v][w];
	}

	public boolean hasPath(int v, int w)
	{
		return cost[v][w] != Double.MAX_VALUE;
	}

	public Iterable<Integer> path(int v, int w)
	{
		if (!hasPath(v, w))
			return null;
		
		List<Integer> path = new LinkedList<Integer>();
		
		return path;
		

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
		new FloydWarshall(G);

	}

}
