package algo.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

import ds.graphs.Graph;
import ds.graphs.NonExistentVertexException;

/**
 * Implementation of the task, Breadth-First-Search w.r.t. a source vertex, for
 * undirected graphs
 * 
 */
public class BFS
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
	 * Indicates if the graph has a cycle
	 */
	protected int adjCount[];
	/**
	 * Number of edges on the path from the source vertex, i.e., length of the
	 * shortest path
	 */
	protected int distTo[];
	/**
	 * Eccentricity of graph w.r.t a source vertex
	 */
	protected int eccentricity;

	/**
	 * A pre-processing constructor which conducts a BFS w.r.t {@code s}.
	 * A BFS w.r.t. {@code s} does the below<br>
	 * 1. Find the vertices in {@code G} connected to {@code s}<br>
	 * 2. Find the paths in {@code G} from {@code s} to every other vertex
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param s Source vertex
	 */
	public BFS(Graph G, int s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		adjCount = new int[G.V()];
		distTo = new int[G.V()];
		bfs(G, s);
	}

	/**
	 * Breadth first search on {@code G}
	 * 
	 * @param G Adjacency-list representation of the graph
	 * @param s Origin vertex where DFS starts
	 */
	private void bfs(Graph G, int s)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		

		q.add(s);
		marked[s] = true;
		while (!q.isEmpty())
		{
			adjCount[s]++;
			int v = q.remove();
			for (int w : G.adj(v))
				if (!marked[w])
				{
					edgeTo[w] = v;
					marked[w] = true;
					distTo[w] = distTo[v] + 1;
					q.add(w);
				}
		}

		// calculate eccentricity of s
		for (int i = 0; i < distTo.length; i++)
			if (distTo[i] > eccentricity)
				eccentricity = distTo[i];

	}

	/**
	 * Determine eccentricity of the source vertex
	 * 
	 * @return Eccentricity of the source vertex
	 */
	public int eccentricity()
	{
		return eccentricity;
	}

	/**
	 * Return the shortest path length from the source vertex to {@code v}
	 * 
	 * @param v The vertex to which the shortest path is to be found from the
	 *            source vertex
	 * @return Shortest path from source vertex to {@code v}
	 */
	int distTo(int v) throws NonExistentVertexException
	{
		if (v < 0 || v >= distTo.length)
			throw (new NonExistentVertexException("BFS : distTo() : " + v
					+ " does not exist"));
		return distTo[v];
	}
}