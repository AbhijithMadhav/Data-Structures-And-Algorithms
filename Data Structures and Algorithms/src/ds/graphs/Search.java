package ds.graphs;

/**
 * Specifies an implementation for the search task on a undirected graph
 * 
 */
public interface Search
{
	/**
	 * Determines if {@code v} is connected to the source vertex
	 * 
	 * @param v Vertex whose connectivity w.r.t. the source vertex is determined
	 */
	public boolean marked(int v);

	/**
	 * Determines the number of adjacent vertices w.r.t the source vertex
	 * 
	 * @return Number of adjacent vertices w.r.t. the source vertex
	 */
	public int adjCount();

}
