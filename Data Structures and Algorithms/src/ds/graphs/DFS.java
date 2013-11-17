package ds.graphs;

/**
 * Implementation of the task, Depth-First-Search, for undirected graphs
 * 
 */
class DFS
{
	/**
	 * Indicates if the indexed vertex is connected to the source vertex
	 */
	protected boolean marked[];
	/**
	 * Contains the last vertex on the path to this vertex w.r.t a source
	 * vertex
	 */
	protected int edgeTo[];
	/**
	 * Number of components
	 */
	int componentCount;
	/**
	 * Component-id to which this vertex belongs
	 */
	int id[];
	/**
	 * Number of adjacent vertices
	 */
	int adjCount[];
	/**
	 * Indicates if the graph has a cycle
	 */
	boolean hasCycle;
	/**
	 * Colour of this vertex
	 */
	boolean color[];
	/**
	 * Indicates if this graph is bipartite
	 */
	boolean isBipartite;

	// Initialise all data members. Method called by constructors
	private void initialise(Graph G)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		adjCount = new int[G.V()];
		id = new int[G.V()];
		componentCount = 0;
		hasCycle = false;
		color = new boolean[G.V()];
		isBipartite = true;
	}

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
		initialise(G);

		// find a vertex to serve as the starting point for a DFS search in each
		// component. 'componentCount' will not only keep a count of the
		// components but will
		// also serve as the id to use for all vertices of that component
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
			{
				dfs(G, v);
				componentCount++;
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
		initialise(G);
		dfs(G, s, s);
	}

	/**
	 * Depth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param s Origin vertex where DFS starts
	 */
	private void dfs(Graph G, int s)
	{
		dfs(G, s, s);
	}

	/**
	 * Depth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param v Origin vertex where DFS starts
	 * @param p Previous vertex leading to {@code v}
	 */
	private void dfs(Graph G, int v, int p)
	{
		marked[v] = true;
		id[v] = componentCount;
		adjCount[v]++;

		for (int w : G.adj(v))
			if (!marked[w])
			{
				color[w] = !color[v];
				edgeTo[w] = v;
				dfs(G, w, v);
			}
			else
			{
				if (w != p)
					hasCycle = true;
				if (color[w] == color[v])
					isBipartite = false;
			}
	}
}