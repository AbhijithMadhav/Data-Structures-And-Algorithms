package algo.graphs.dfs.undirected;

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
	
	public Cycle(Graph G)
	{
		hasCycle = false;
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
				dfs(G, w, v);
			else
			{
				if (w != p) // vi s reachable from both w and p
					hasCycle = true;
			}
	}

	public boolean hasCycle()
	{
		return hasCycle;
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
			G = new Graph(new In(args[0]));
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		Cycle c = new Cycle(G);
		if(c.hasCycle())
			System.out.println(args[0] + " : Cycle detected");
		else
			System.out.println(args[0] + " : Cycle not present");
	}
		
}
