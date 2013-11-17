package algo.graphs.dfs;

import ds.graphs.ICycle;
import ds.graphs.Digraph;
import ds.graphs.Graph;

/**
 * Implements {@code Cycle} using DFS
 *
 */
public class CycleDFS extends DFS implements ICycle
{
	/**
	 * {@link DFS#DFS(Graph)}
	 */
	public CycleDFS(Graph G)
	{
		super(G);
	}
	/* (non-Javadoc)
	 * @see Cycle#hasCycle(Graph)
	 */
	@Override
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
	//	Graph G = null;
		Digraph dg = null;
		try
		{
	//		G = new Graph(new In(args[0]));
					 dg = new Digraph(2);
		dg.addEdge(0, 1);
		dg.addEdge(1, 0);

		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		//Cycle c = new CycleDFS(G);
		ICycle c = new CycleDFS(dg);
		if(c.hasCycle())
			System.out.println(args[0] + " : Cycle detected");
		else
			System.out.println(args[0] + " : Cycle not present");
	}
		
}
