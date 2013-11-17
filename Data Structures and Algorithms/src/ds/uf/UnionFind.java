package ds.uf;

/**
 * Specification of a union-find task on an undirected graph
 */
public abstract class UnionFind
{
	/**
	 * id of the component
	 */
	protected int id[];
	/**
	 * Number of components
	 */
	protected int componentCount;
	/**
	 * Height of component tree rooted at this vertex
	 */
	protected int height[];
	/**
	 * Size of component tree rooted at this vertex
	 */
	protected int sz[];

	/**
	 * Initialise {@code N} components with integer names
	 * 
	 * @param N Number of components
	 */
	public UnionFind(int N)
	{
		componentCount = N; // Initially assume that all nodes are in different
		// components, components containing just themselves
		id = new int[N]; // allocate memory
		height = new int[N];
		sz = new int[N];

		// Initialise
		for (int i = 0; i < N; i++)
		{
			id[i] = i;
			height[i] = 0;
			sz[i] = 1;
		}
	}

	/**
	 * union takes an edge p-q as input and labels them with the same component
	 * id if they already aren't labelled thus. The reasoning behind doing this
	 * is obvious. As p-q is an edge, {@code p} and {@code q} are obviously
	 * connected and hence can be seen as belonging to the same component.
	 * 
	 * @param p First component of the edge
	 * @param q Second component of the edge
	 */
	public abstract void union(int p, int q);

	/**
	 * Determines the component to which {@code p} belongs
	 * 
	 * @param p The vertex to which the component is determined
	 * @return Component of {@code p}
	 */
	abstract int find(int p);

	/**
	 * Determines if {@code p} and {@code q} in the same component
	 * 
	 * @param p One vertex
	 * @param q Other vertex
	 * @return Are {@code p} and {@code q} in the same component
	 */
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}

	/**
	 * Determines the number of components in the graph
	 * 
	 * @return Number of components in the graph
	 */
	int componentCount()
	{
		return componentCount;
	}
}
