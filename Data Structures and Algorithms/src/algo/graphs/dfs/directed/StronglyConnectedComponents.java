package algo.graphs.dfs.directed;

import java.util.HashSet;
import java.util.Set;

import ds.graphs.Digraph;
import ds.graphs.Graph;
import edu.princeton.cs.introcs.In;
/**
 * @author kempa
 * 
 */
public class StronglyConnectedComponents
{

	private boolean marked[];
	private int id[];
	private int count;

	public StronglyConnectedComponents(Digraph G)
	{
		marked = new boolean[G.V()];
		id = new int[G.V()];

		OrderDirectedDFS order = new OrderDirectedDFS(G);
		for (int v : order.reversePostOrder())
			if (!marked[v])
			{
				dfs(G.reverse(), v);
				count++;
			}
	}

	private void dfs(Graph G, int v)
	{
		marked[v] = true;
		id[v] = count;

		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean stronglyConnected(int v, int w)
	{
		return id[v] == id[w];
	}

	public int id(int v)
	{
		return id[v];
	}

	public int count()
	{
		Set<Integer> components = new HashSet<>();
		for (int v = 0; v < id.length; v++)
			components.add(id[v]);
		return components.size();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Digraph G = null;
		try
		{
			G = new Digraph(new In(args[0]));
			System.out.println(args[0] + "\n" + G);

		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		System.out.println("Vertex: Component");
		StronglyConnectedComponents scc = new StronglyConnectedComponents(G);
		for (int v = 0; v < G.V(); v++)
			System.out.println(v + ": " + scc.id(v));
	}
}