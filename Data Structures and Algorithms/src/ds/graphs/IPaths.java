package ds.graphs;


/**
 * Specifies an implementation for the task of finding paths in an undirected graph
 */
public interface IPaths
{

	/**
	 * Determines if there is a path from the source vertex to {@code v}
	 * 
	 * @param v The vertex to whom the path is determined from the source vertex
	 * @return Is there a path from the source vertex to {@code v}
	 */
	public boolean hasPathTo(int v);

	/**
	 * Gives the path to {@code v} from the source vertex
	 * 
	 * @param v The vertex to whom the path is determined from the source vertex
	 * @return Path from source vertex to {@code v}. {@code null} if no such
	 *         path
	 */
	public Iterable<Integer> pathTo(int v);
}
