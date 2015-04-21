package algo.graphs.dfs.undirected;

import ds.graphs.Graph;

public abstract class SourceDFS
{
	/**
	 * Indicates if the indexed vertex is connected to the source vertex
	 */
	private boolean marked[];

	/**
	 * A pre-processing constructor which conducts a DFS w.r.t {@code s}.
	 * A DFS w.r.t. {@code s} does the below<br>
	 * 1. Find the vertices in {@code G} connected to {@code s}<br>
	 * 2. Find the paths in {@code G} from {@code s} to every other vertex
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param s Source vertex
	 */
	SourceDFS(Graph G)
	{
		marked = new boolean[G.V()];
	}

	/**
	 * Depth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param v Origin vertex where DFS starts
	 */
	public final void dfs(Graph G, int v)
	{
		if (marked[v])
			return;
		
		marked[v] = true;
		preAnyAdjacentVerticesVisit(v);
		
		for (int w : G.adj(v))
			if (!marked[w])
			{
				preAdjacentVertexVisit(v, w);
				dfs(G, w);
				postAdjacentVertexVisit(v, w);	
			}
	}
	
	public boolean marked(int v)
	{
		return marked[v];
	}
	// Template method pattern	
	
	public abstract void preAnyAdjacentVerticesVisit(int source);
	public abstract void preAdjacentVertexVisit(int source, int adj);
	public abstract void postAdjacentVertexVisit(int source, int adj);
	
	

}
