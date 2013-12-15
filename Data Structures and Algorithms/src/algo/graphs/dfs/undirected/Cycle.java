package algo.graphs.dfs.undirected;

import java.util.Stack;

import ds.graphs.Graph;
import ds.graphs.ICycle;
import edu.princeton.cs.introcs.In;

// To do : can't I produce the cycle??
/**
 * Implements {@code Cycle} using DFS
 * 
 */
final public class Cycle implements ICycle
{
	private boolean hasCycle;
	private boolean marked[];
	Stack<Integer> cycle;

	public Cycle(Graph G)
	{
		hasCycle = false;
		cycle = new Stack<>();
		marked = new boolean[G.V()];

		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v, v);
	}

	/**
	 * Depth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param v Origin vertex where DFS starts
	 * @param p Previous vertex leading to {@code v}
	 */
	private void dfs(Graph G, int v, int p)
	{
		marked[v] = true;

		for (int w : G.adj(v))
			if (!marked[w])
			{
				if (!hasCycle())
					cycle.push(v);
				dfs(G, w, v);
				if (!hasCycle())
					cycle.pop();
			}
			else
			{
				if (w != p)
				{
					hasCycle = true;
				}
			}
	}

	public boolean hasCycle()
	{
		return hasCycle;
	}

	public Stack<Integer> cycle()
	{
		return cycle;
	}

	/**
	 * A test client
	 * 
	 * @param args {@code args[0]} = Input-file {@code args[1]} = Source-vertex
	 */
	public static void main(String[] args)
	{
		Graph G = null;
		try
		{
			G = new Graph(new In("tinyG.txt"));
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		Cycle c = new Cycle(G);
		if (c.hasCycle())
		{
			System.out.println("tinyG.txt" + " : Cycle detected");
			System.out.println(c.cycle.size());
			for (Integer v : c.cycle)
				System.out.print(v + " - ");
		}
		else
			System.out.println("tinyG.txt" + " : Cycle not present");
	}

}
