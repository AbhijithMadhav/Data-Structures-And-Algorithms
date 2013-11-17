package ds.graphs;

import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.introcs.In;

/**
 * 
 */

/**
 * @author kempa
 * 
 */
public class OrderDirectedDFS
{
	private boolean marked[];
	private LinkedList<Integer> reversePostOrder; // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4475301
	private Queue<Integer> postOrder;
	private Queue<Integer> preOrder;

	public OrderDirectedDFS(Digraph G)
	{
		marked = new boolean[G.V()];
		reversePostOrder = new LinkedList<Integer>();
		postOrder = new LinkedList<Integer>();
		preOrder = new LinkedList<Integer>();
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v);
	}

	private void dfs(Digraph G, int v)
	{
		marked[v] = true;

		preOrder.add(v);

		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);

		postOrder.add(v);
		reversePostOrder.push(v);
	}

	public Iterable<Integer> reversePostOrder()
	{
		return reversePostOrder;
	}

	public Iterable<Integer> postOrder()
	{
		return postOrder;
	}

	public Iterable<Integer> preOrder()
	{
		return preOrder;
	}

	public static void main(String args[])
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

		OrderDirectedDFS order = new OrderDirectedDFS(G);

		System.out.println("Post order of G");
		for (int v : order.postOrder())
			System.out.print(v + " ");

		OrderDirectedDFS reverse = new OrderDirectedDFS(G.reverse());
		System.out.println("\nReverse post order of R");
		for (int v : reverse.reversePostOrder())
			System.out.print(v + " ");

	}
}
