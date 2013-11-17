package ds.graphs;

public class Topological
{
	private Iterable<Integer> order;

	public Topological(Digraph G)
	{
		DirectedCycleDFS cycleFinder = new DirectedCycleDFS(G);
		if (!cycleFinder.hasCycle())
			order = (new OrderDirectedDFS(G)).reversePostOrder();
	}

	public Iterable<Integer> order()
	{
		return order;
	}

	public boolean isDAG()
	{
		return order != null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		StringDigraph sg = new StringDigraph(args[0], args[1]);
		System.out.println(args[0] + "\n" + sg);

		Topological top = new Topological(sg.G());
		System.out.println("Scheduling order");
		System.out.println("----------------");
		for (int v : top.order())
			System.out.println(sg.name(v) + "(" + v + "): ");
	}
}