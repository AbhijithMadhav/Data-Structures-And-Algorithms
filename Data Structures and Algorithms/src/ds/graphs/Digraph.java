package ds.graphs;

import edu.princeton.cs.introcs.In;

/**
 * An Adjacency-list representation of a directed graph
 */
public class Digraph extends Graph
{

	/**
	 * {@link Graph#Graph(int)}
	 */
	public Digraph(int V)
	{
		super(V);
	}

	/**
	 * {@link Graph#Graph(In)}
	 */
	public Digraph(In in) throws InvalidEdgeException, SelfLoopExistsException,
			ParallelEdgeExistsException
	{
		super(in);
	}

	/**
	 * {@link Graph#Graph(Graph)}
	 */
	public Digraph(Graph G)
	{
		super(G);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int v, int w) throws InvalidEdgeException,
			SelfLoopExistsException, ParallelEdgeExistsException
	{
		// check for invalid edge
		if (v < 0 || v >= V() || w < 0 || w >= V())
			throw new InvalidEdgeException("Invalid edge : " + v + "-" + w);

		if (v == w)
			throw new SelfLoopExistsException("Self loops not allowed : " + v
					+ "-" + w);

		for (int a : adj[v])
			if (a == w)
				throw new ParallelEdgeExistsException(
						"Parallel edges not allowed : " + v + "-" + w);

		adj[v].add(w);
		E++;
	}

	/**
	 * Give a copy of the Digraph with its edges reversed
	 * 
	 * @return Edge-reversed directed graph
	 */
	public Digraph reverse()
	{
		Digraph R = new Digraph(V());
		for (int v = 0; v < V(); v++)
			for (int w : adj(v))
				try
				{
					R.addEdge(w, v);
				}
				catch (Exception e)
				{
					// No Exception can arise as R is being constructed from a
					// valid DG
					assert (v < 0 || v >= V() || w < 0 || w >= V());
					assert (v == w);
					for (int a : adj[v])
						assert (a == w);
				}
		return R;
	}

	/**
	 * Test client
	 * 
	 * @param args {@code args[0]} = Input-file
	 */
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		try
		{
			Digraph g = new Digraph(in);
			System.out.println(args[0] + " \n" + g);

			Digraph dg = new Digraph(g);
			System.out.println("Duplicate graph of the above\n" + dg);
			
			System.out.println("Reversed graph of the above\n" + g.reverse());
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

	}
}