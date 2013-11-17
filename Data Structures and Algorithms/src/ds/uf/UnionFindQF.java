package ds.uf;

import edu.princeton.cs.introcs.In;
/**
 * Implementation of union-find task using the quick-find method
 */
public class UnionFindQF extends UnionFind
{
	/**
	 * {@link UnionFindQF#UnionFindQF(int)}
	 */
	public UnionFindQF(int N)
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
		// The component to which p belongs is immediate from its component-id, id[p]
		return id[p];
	}

	/*
	 * (non-Javadoc)
	 * @see UnionFind#union(int, int)
	 */
	@Override
	public void union(int p, int q)
	{
		int pID = find(p); // component of p
		int qID = find(q); // component of q

		// Do nothing if p and q belong to the same component
		if (pID == qID)
			return;
		
		//Label all nodes with component id, pID to qID
		for(int i = 0; i < id.length; i++)
			if (id[i] == pID)
				id[i] = qID;
		
		componentCount--;
	}
	
	/**
	 * Test client - solve dynamic connectivity problem 
	 * @param args {@code args[0]} = Input-file
	 */
	public static void main(String args[])
	{
		In in = new In(args[0]);
		UnionFindQF qf = new UnionFindQF(in.readInt());
		while(!in.isEmpty())
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
