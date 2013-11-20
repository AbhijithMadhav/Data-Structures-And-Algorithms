package algo.graphs.dfs.undirected;

import ds.graphs.Graph;
import ds.graphs.IBipartite;
import edu.princeton.cs.introcs.In;

final public class Bipartite implements IBipartite
{
	private boolean marked[];
	private boolean color[];
	private boolean isBipartite;

	public Bipartite(Graph G)
	{
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		isBipartite = true;

		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
			{
				dfs(G, v);
			}
	}

	private void dfs(Graph G, int v)
	{
		marked[v] = true;

		for (int w : G.adj(v))
			if (!marked[w])
			{
				color[w] = !color[v];
				dfs(G, w);
			}
			else
			{
				if (color[w] == color[v])
					isBipartite = false;
			}
	}

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

		Bipartite b = new Bipartite(G);
		if (b.isBipartite())
			System.out.println(args[0] + " is bipartite");
		else
			System.out.println(args[0] + " is NOT bipartite");
		System.out.println("\n" + G);
	}
}
