package algo.graphs.bfs;

import ds.graphs.Graph;
import ds.graphs.IPaths;
import edu.princeton.cs.introcs.In;

import java.util.LinkedList;
/**
 *Implements {@code Paths} using BFS
 * 
 */
public class PathsBFS extends BFS implements IPaths
{
	private int s;
	
	public PathsBFS(Graph G, int s)
	{
		super(G, s);
		this.s = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Paths#hasPathTo(int)
	 */
	@Override
	public boolean hasPathTo(int v)
	{
		return marked[v];
	}

	/*
	 * (non-Javadoc)
	 * 
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
		IPaths paths = new PathsBFS(G, s);

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
