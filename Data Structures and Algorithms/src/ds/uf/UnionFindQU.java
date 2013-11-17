package ds.uf;

import edu.princeton.cs.introcs.In;

/**
 * Implementation of union-find task using the quick-union method
 */
public class UnionFindQU extends UnionFind
{
	/**
	 * {@link UnionFindQF#UnionFindQF(int)}
	 */
	public UnionFindQU(int N)
	{
		super(N);
	}

	/*
	 * (non-Javadoc)
	 * @see UnionFind#find(int)
	 */
	@Override
	public int find(int p)
	{
		// The component to which p belongs is immediate from its component-id,
		// id[p] only if id[p] is the same as the vertex itself
		// Else the component-id is just a link in the path which leads to the
		// actual component-id
		while (id[p] != p)
			p = id[p];
		return p;
	}

	/*
	 * (non-Javadoc)
	 * @see UnionFind#union(int, int)
	 */
	@Override
	public void union(int p, int q)
	{
		int pRoot = find(p); // component of p
		int qRoot = find(q); // component of q

		// Do nothing if p and q belong to the same component
		if (pRoot == qRoot)
			return;

		// Label the component-id of pRoot as qRoot, thus effectively making the
		// component-id of all vertices whose actual component-id was pRoot
		// (by the virtue of being led to pRoot through their component-id's)
		// now as qRoot
		id[pRoot] = qRoot;

		componentCount--;
	}

	/**
	 * Test client - solve dynamic connectivity problem 
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
