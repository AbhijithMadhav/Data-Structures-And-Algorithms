package ds.graphs;

import edu.princeton.cs.introcs.In;

/**
 * 
 * Implementation of {@code Bipartite} using DFS
 * 
 */
public class BipartiteDFS extends DFS implements Bipartite
{

	/**
	 * 
	 * {@link DFS#DFS(Graph)}
	 */
	public BipartiteDFS(Graph G)
	{
		super(G);
	}

	/*
	 * (non-Javadoc)
	 * @see Bipartite#isBipartite()
	 */
	@Override
	public boolean isBipartite()
	{
		return isBipartite;
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

		Bipartite b = new BipartiteDFS(G);
		if(b.isBipartite())
			System.out.println(args[0] + " is bipartite");
		else
			System.out.println(args[0] + " is NOT bipartite");
		System.out.println("\n" + G);
	}
}
