package algo.graphs;

import algo.graphs.dfs.undirected.DFS;
import ds.graphs.Graph;
import ds.graphs.ISearch;
import ds.uf.UnionFindWQU;
import edu.princeton.cs.introcs.In;
/**
 * Implements {@code Search} using Weighted quick union
 */
public class SearchWQU extends UnionFindWQU implements ISearch
{
	/**
	 * Source vertex w.r.t. which search operations are implemented
	 */
	private int s;

	/**
	 * {@link DFS#DFS(Graph, int)}
	 */
	public SearchWQU(Graph G, int s)
	{
		super(G.V());
		this.s = s;
	}
	
	/*
	 * weight[i] will give the number of nodes in the component i, only if i is
	 * a component
	 */


	/*
	 * (non-Javadoc)
	 * @see Search#marked(int)
	 */
	@Override
	public boolean marked(int v)
	{
		return connected(s, v);
	}

	/*
	 * (non-Javadoc)
	 * @see Search#adjCount()
	 */
	@Override
	public int adjCount()
	{
		return weight(s);
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
		ISearch search = new SearchWQU(G, s);

		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				System.out.print(v + " ");
		System.out.println();

		if (search.adjCount() != G.V())
			System.out.print("NOT ");
		System.out.println("Connected");

	}
}