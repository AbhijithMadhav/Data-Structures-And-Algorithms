package algo.graphs.dfs.undirected;

import ds.graphs.Graph;

public abstract class AllDFS extends SourceDFS
{

	/**
	 * A pre-processing constructor which conducts a DFS w.r.t every vertex of
	 * {@code G}. A DFS does the below<br>
	 * 1. Find the component to which each vertex belongs<br>
	 * 2. Find the number of components in {@code G}<br>
	 * 3. Determine if {@code G} has a cycle<br>
	 * 4. Determine is {@code G} is bipartite<br>
	 * 
	 * @param G
	 *            Adjacency-list representation of the graph
	 */
	public AllDFS(Graph G)
	{
		super(G);
	}

	public void allDFS(Graph G)
	{
		// find a vertex to serve as the starting point for a DFS search in each
		// component. 'componentCount' will not only keep a count of the
		// components but will
		// also serve as the id to use for all vertices of that component
		for (int v = 0; v < G.V(); v++)
		{
				dfs(G, v);
				postEachSourceDFS(v);
		}
	}

	public abstract void postEachSourceDFS(int source);
	
	@Override
	public void preAnyAdjacentVerticesVisit(int source)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preAdjacentVertexVisit(int source, int adj)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAdjacentVertexVisit(int source, int adj)
	{
		// TODO Auto-generated method stub
		
	}
}
