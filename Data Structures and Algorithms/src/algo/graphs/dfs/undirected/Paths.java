package algo.graphs.dfs.undirected;

import ds.graphs.Graph;
import ds.graphs.IPaths;
import edu.princeton.cs.introcs.In;

import java.util.LinkedList;

/**
 * Implements {@code Paths} using DFS
 * 
 */
public class Paths implements IPaths
{
	/**
	 * Source vertex w.r.t which paths are determined 
	 */
	private int s;
	protected boolean marked[];
	protected int edgeTo[];
	
	/**
	 * {@link DFS#DFS(Graph, int)}
	 * 
	 */
	public Paths(Graph G, int s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G, s);
		this.s = s;
	}
	
	private void dfs(Graph G, int v)
	{
		marked[v] = true;

		for (int w : G.adj(v))
			if (!marked[w])
			{
				edgeTo[w] = v;
				dfs(G, w);
			}
	}

	/*
	 * (non-Javadoc)
	 * @see Paths#hasPathTo(int)
	 */
	@Override
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}

	/*
	 * (non-Javadoc)
	 * @see Paths#pathTo(int)
	 */
	@Override
	public Iterable<Integer> pathTo(int v)
	{
		if (!hasPathTo(v))
			return null;

		LinkedList<Integer> path = new LinkedList<Integer>();
		for (int x = v; x != s; x = edgeTo[x])
			path.addFirst(x);

		path.addFirst(s);
		return path;
	}

	/**
	 * A test client
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

		int s = Integer.parseInt(args[1]);
		IPaths paths = new Paths(G, s);

		for (int v = 0; v < G.V(); v++)
		{
			System.out.print(s + " to " + v + " : ");
			if (paths.hasPathTo(v))
				for (int x : paths.pathTo(v))
					if (x == s)
						System.out.print(x);
					else
						System.out.print("-" + x);
			System.out.println();
		}
	}
}
