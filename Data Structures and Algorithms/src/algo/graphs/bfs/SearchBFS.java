package algo.graphs.bfs;

import ds.graphs.Graph;
import ds.graphs.ISearch;
import edu.princeton.cs.introcs.In;
/**
 * Implements {@code Search} using BFS
 * 
 */
public class SearchBFS extends BFS implements ISearch
{
	/**
	 * Source vertex w.r.t. which search operations are implemented
	 */
	private int s;

	/**
	 * {@link BFS#BFS(Graph, int)}
	 */
	public SearchBFS(Graph G, int s)
	{
		super(G, s);
		this.s = s;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Search#marked(int)
	 */
	@Override
	public boolean marked(int v)
	{
		return marked[v];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Search#adjCount()
	 */
	@Override
	public int adjCount()
	{
		return adjCount[s];
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

		int s = Integer.parseInt(args[1]);
		ISearch search = new SearchBFS(G, s);

		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				System.out.print(v + " ");
		System.out.println();

		if (search.adjCount() != G.V())
			System.out.print("NOT ");
		System.out.println("Connected");
	}
}
