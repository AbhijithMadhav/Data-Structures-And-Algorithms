package algo.graphs.dfs.undirected;

import ds.graphs.Graph;
import ds.graphs.IPaths;
import edu.princeton.cs.introcs.In;

import java.util.LinkedList;

/**
 * Implements {@code Paths} using DFS
 * 
 */
public class Paths extends SourceDFS implements IPaths
{
	/**
	 * Source vertex w.r.t which paths are determined 
	 */
	private int s;
	protected int edgeTo[];
	
	public Paths(Graph G, int s)
	{
		super(G);
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	@Override
	public void preAdjacentVertexVisit(int source, int adj)
	{
		edgeTo[adj] = source;
	}

	/*
	 * (non-Javadoc)
	 * @see Paths#hasPathTo(int)
	 */
	@Override
	public boolean hasPathTo(int v)
	{
		return marked(v);
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

	@Override
	public void preAnyAdjacentVerticesVisit(int source)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAdjacentVertexVisit(int source, int adj)
	{
		// TODO Auto-generated method stub
		
	}
}
