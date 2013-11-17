package ds.uf;

import edu.princeton.cs.introcs.In;
import ds.graphs.Graph;

/**
 * Implementation of union-find task using the weighted-quick-union method
 */
public class UnionFindWQU extends UnionFindQU
{
	/**
	 * {@link UnionFindQF#UnionFindQF(int)}
	 */
	public UnionFindWQU(int N)
	{
		super(N);
	}

	/**
	 * Create a search graph using weighted quick union
	 * @param G Adjacency-list representation of the graph
	 * @param s Origin vertex where DFS starts
	 */
	protected void wqu(Graph G, int s)
	{
		for (int i = 0; i < G.V(); i++)
			for (int j : G.adj(i))
				union(i, j);
	}

	/*
	 * (non-Javadoc)
	 * @see UnionFindQU#union(int, int)
	 */
	@Override
	public void union(int p, int q)
	{
		int pRoot = find(p); // component of p
		int qRoot = find(q); // component of q

		// Do nothing if p and q belong to the same component
		if (pRoot == qRoot)
			return;

		// Take care to make smaller tree point to larger one thus keeping the
		// component-tree height relatively smaller
		if (height[pRoot] < height[qRoot])
		{
			// Label component-id of pRoot as qRoot, thus effectively making the
			// component-id of all vertices whose actual component-id was pRoot
			// (by the virtue of being led to pRoot through their
			// component-id's)
			// now as qRoot
			id[pRoot] = qRoot;

			height[qRoot] = height[pRoot] + 1;
			sz[qRoot] += sz[pRoot];
		}
		else
		{
			// vice-versa
			id[qRoot] = pRoot;
			height[pRoot] = height[qRoot] + 1;
			sz[pRoot] += sz[qRoot];
		}
		componentCount--;
	}

	// return weight of component containing v
	public int weight(int v)
	{
		return sz[find(v)];
	}

	/**
	 * A test client
	 * 
	 * @param args {@code args[0]} = Input-file
	 */
	public static void main(String args[])
	{
		In in = new In(args[0]);
		int N = in.readInt();
		UnionFindWQU qf = new UnionFindWQU(N);
		while (!in.isEmpty())
		{
			int p = in.readInt();
			int q = in.readInt();

			if (qf.connected(p, q))
				continue;

			qf.union(p, q);
			System.out.println(p + " " + q);
		}
		System.out.println(qf.componentCount() + " components");
	}
}
