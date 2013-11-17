package ds.graphs;

/**
 * Specifies an implementation for the task of finding connected components in
 * an undirected graph
 * 
 */
public interface IConnectedComponents
{
	/**
	 * Determines if {@code v} and {@code w} are connected
	 * 
	 * @param v First vertex
	 * @param w Second vertex
	 * @return Are {@code v} and {@code w} connected?
	 */
	public boolean connected(int v, int w);

	/**
	 * Determines the number of connected components in the graph
	 * 
	 * @return Number of connected components in the graph
	 */
	public int componentCount();

	/**
	 * Determines the component identifier for {@code v}
	 * 
	 * @param v The vertex for whom the component identifier is to be determined
	 * @return Component identifies of {@code v}
	 */
	public int id(int v);

}
