package algo.graphs.dfs.undirected;

import ds.graphs.Graph;

/**
 * Implementation of the task, Depth-First-Search, for undirected graphs
 * 
 */
public class DFS
{
	/**
	 * Indicates if the indexed vertex is connected to the source vertex
	 */
	protected boolean marked[];

	/**
	 * A pre-processing constructor which conducts a DFS w.r.t every vertex of
	 * {@code G}.
	 * A DFS does the below<br>
	 * 1. Find the component to which each vertex belongs<br>
	 * 2. Find the number of components in {@code G}<br>
	 * 3. Determine if {@code G} has a cycle<br>
	 * 4. Determine is {@code G} is bipartite<br>
	 * 
	 * @param G Adjacency-list representation of the graph
	 */
	DFS(Graph G)
	{
		marked = new boolean[G.V()];

		// find a vertex to serve as the starting point for a DFS search in each
		// component. 'componentCount' will not only keep a count of the
		// components but will
		// also serve as the id to use for all vertices of that component
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
			{
				dfs(G, v);
				postEachSourceDFS(G, v);
			}
	}

	/**
	 * A pre-processing constructor which conducts a DFS w.r.t {@code s}.
	 * A DFS w.r.t. {@code s} does the below<br>
	 * 1. Find the vertices in {@code G} connected to {@code s}<br>
	 * 2. Find the paths in {@code G} from {@code s} to every other vertex
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param s Source vertex
	 */
	DFS(Graph G, int s)
	{
		marked = new boolean[G.V()];
		sourceDFSInitialize(G, s);
		dfs(G, s);
	}

	/**
	 * Depth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param v Origin vertex where DFS starts
	 */
	public void dfs(Graph G, int v)
	{
		marked[v] = true;
		preAnyAdjacentVerticesVisit(G, v);
		
		for (int w : G.adj(v))
			if (!marked[w])
			{
				preAdjacentVertexVisit(v, w);
				dfs(G, w);
				postAdjacentVertexVisit(v, w);	
			}
	}
	
	// Template method pattern
	
	public void allDFSInitialize(Graph G){}
	public void postEachSourceDFS(Graph G, int source){}
	
	public void sourceDFSInitialize(Graph G, int source) {}
	public void preAnyAdjacentVerticesVisit(Graph G, int source){}
	public void preAdjacentVertexVisit(int source, int adj){}
	public void postAdjacentVertexVisit(int source, int adj){}
	
	
}