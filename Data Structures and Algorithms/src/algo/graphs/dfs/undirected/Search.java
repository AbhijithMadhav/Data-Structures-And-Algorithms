package algo.graphs.dfs.undirected;

import ds.graphs.Graph;
import ds.graphs.ISearch;
import edu.princeton.cs.introcs.In;
/**
 * Implements {@code Search} using DFS
 */
public class Search implements ISearch
{
	/**
	 * Source vertex w.r.t. which search operations are implemented
	 */
	private int s;
	protected boolean marked[];
	int adjCount[];

	/**
	 * {@link DFS#DFS(Graph, int)}
	 */
	public Search(Graph G, int s)
	{
		adjCount = new int[G.V()];
		dfs(G, s);
		this.s = s;
	}

	private void dfs(Graph G, int v)
	{
		marked[v] = true;
		adjCount[v]++;

		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
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
		ISearch search = new Search(G, s);

		for (int v = 0; v < G.V(); v++)
			if (search.marked(v))
				System.out.print(v + " ");
		System.out.println();

		if (search.adjCount() != G.V())
			System.out.print("NOT ");
		System.out.println("Connected");
	}
}