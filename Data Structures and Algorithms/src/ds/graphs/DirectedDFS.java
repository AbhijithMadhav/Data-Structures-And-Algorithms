package ds.graphs;

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.In;

/**
 * Implementation of the task, Depth-First-Search, for directed graphs
 * 
 */
public class DirectedDFS
{

	private boolean[] marked;

	/**
	 * Find the vertices in G that are reachable from sources
	 * 
	 * @param G
	 * @param sources
	 */
	public DirectedDFS(Digraph G, Iterable<Integer> sources)
	{
		marked = new boolean[G.V()];
		for (int s : sources)
			if (!marked[s])
				dfs(G, s);
	}

	/**
	 * Find verticies in G that are reachable from s
	 * 
	 * @param G
	 * @param s
	 */
	public DirectedDFS(Digraph G, int s)
	{
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Digraph G, int v)
	{
		marked[v] = true;
		for (int w : G.adj(v))
			if (!marked(w))
				dfs(G, w);
	}

	/**
	 * Is v reachable?
	 * 
	 * @param v
	 * @return
	 */
	public boolean marked(int v)
	{
		return marked[v];
	}

	/**
	 * @param args args[0] -> filename args[1-n]-> source-vertices 
	 */
	public static void main(String[] args)
	{
		Digraph G = null;
		try
		{
			G = new Digraph(new In(args[0]));
			System.out.println(args[0] + "\n" + G);
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		System.out.print("Source vertices : ");
		List<Integer> sources = new LinkedList<Integer>();
		for (int i = 1; i < args.length; i++)
		{
			System.out.println(args[i]);
			sources.add(Integer.parseInt(args[i]));
		}

		DirectedDFS reachable = new DirectedDFS(G, sources);

		System.out.print("Reachable vertices :");
		for (int v = 0; v < G.V(); v++)
			if (reachable.marked(v))
				System.out.print(v + " ");
		System.out.println();
	}
}
