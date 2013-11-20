package algo.graphs.dfs.directed;

import ds.graphs.Digraph;
import ds.graphs.ICycle;
import edu.princeton.cs.introcs.In;

import java.util.Stack;

/**
 * Implements {@code Cycle} using DFS
 */
public class DirectedCycle implements ICycle
{

	private boolean marked[];
	private boolean onStack[];
	private Stack<Integer> cycle;
	private int edgeTo[];

	public DirectedCycle(Digraph G)
	{
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];

		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v);
	}

	private void dfs(Digraph G, int v)
	{
		marked[v] = true;
		onStack[v] = true;
		for (int w : G.adj(v))
		{
			if (hasCycle())
				return;
			else if (!marked[w])
			{
				edgeTo[w] = v;
				dfs(G, w);
			}
			else if (onStack[w])
			{
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v); // starting and ending vertex of a cycle are the
								// same
			}
		}
		onStack[v] = false;
	}

	public boolean hasCycle()
	{
		return cycle != null;
	}

	public Iterable<Integer> cycle()
	{
		return cycle;
	}

	/**
	 * @param args
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

		DirectedCycle cycleFinder = new DirectedCycle(G);
		if (cycleFinder.hasCycle())
		{
			System.out.println("Cycle found");
			for (int v : cycleFinder.cycle())
				System.out.print(v + " ");
			System.out.println();
		}

	}
}