package ds.graphs;

import edu.princeton.cs.introcs.In;

/**
 * Task-oriented class to determine the properties of the class
 */
public class GraphProperties
{
	/**
	 * Object to do a BFS on every vertex of the graph
	 */
	private BFS bfs[];
	/**
	 * Diameter of the graph
	 */
	private int diameter;
	/**
	 * Radius of the graph
	 */
	private int radius;
	/**
	 * Center of the graph
	 */
	private int center;


	/**
	 * Determines properties of {@code G}
	 * 
	 * @param G Graph whose properties should be determined
	 */
	public GraphProperties(Graph G)
	{
		bfs = new BFS[G.V()];
		for (int v = 0; v < G.V(); v++)
			bfs[v] = new BFS(G, v);

		diameter = 0;
		radius = Integer.MAX_VALUE;
		for (int v = 0; v < G.V(); v++)
		{
			if (diameter < bfs[v].eccentricity())
				diameter = bfs[v].eccentricity();

			if (radius > bfs[v].eccentricity())
			{
				radius = bfs[v].eccentricity();
				center = v;
			}
		}

	}

	/**
	 * Eccentricity of v
	 * 
	 * @param v Vertex w.r.t which eccentricity is to be found
	 * @return eccentricity of v
	 */
	public int eccentricity(int v)
	{
		return bfs[v].eccentricity();
	}

	/**
	 * Determines diameter of the graph
	 * 
	 * @return Diameter of the graph
	 */
	public int diameter()
	{
		return diameter;
	}

	/**
	 * Determines the radius of the graph
	 * 
	 * @return Radius of the graph
	 */
	public int radius()
	{
		return radius;
	}

	/*
	 * Determines the center of the graph
	 * 
	 * @return Center of the graph
	 */
	public int center()
	{
		return center;
	}

	/**
	 * A test client
	 * 
	 * @param args {@code args[0]} = Input-file {@code args[1]} = Source-vertex
	 */
	public static void main(String[] args)
	{
		GraphProperties GP = null;
		Graph G = null;
		try
		{
			G = new Graph(new In(args[0]));
			GP = new GraphProperties(G);
		}
		catch (Exception e)
		{
			System.out.println(e);
			System.exit(1);
		}

		System.out.println(args[0] + "\n" + G.toString());
		for (int v = 0; v < G.V(); v++)
			System.out.println("Eccentricity of " + v + " = "
					+ GP.eccentricity(v));
		System.out.println("Diameter = " + GP.diameter());
		System.out.println("Radius = " + GP.radius());
		System.out.println("Center is " + GP.center());
	}
}
