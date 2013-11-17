package ds.graphs;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import ds.st.ST;
import ds.st.SeparateChainingHashST;

/**
 * A graph implementation using the adjacency list representation where
 * vertices with strings can also be represented
 * 
 * 
 */
public class StringGraph
{
	/**
	 * Used to convert string names of vertexes to integer names for internal
	 * graph processing
	 */
	private ST<String, Integer> st;
	/**
	 * Used to convert integer names of vertices to string name for the
	 * application
	 */
	private String keys[];
	/**
	 * The graph with integer names for vertices
	 */
	private Graph G;

	/**
	 * Builds graph specified in {@code filename} using {@code delim} to
	 * separate vertex names
	 * 
	 * @param filename File containing edges specifying the graph
	 * @param delim Delimiter used to separate vertex names
	 */
	public StringGraph(String filename, String delim)
	{
		In in = new In(filename);

		// Create the index
		st = new SeparateChainingHashST<String, Integer>();
		while (in.hasNextLine())
		{
			String names[] = in.readLine().split(delim);
			for (int i = 0; i < names.length; i++)
				if (!contains(names[i]))
					st.put(names[i], st.size());
		}

		// Create the reverse index
		keys = new String[st.size()];
		for (String name : st.keys())
			keys[st.get(name)] = name;

		// Create the graph
		G = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine())
		{
			String names[] = in.readLine().split(delim);
			for (int i = 1; i < names.length; i++)
				try
				{
					G.addEdge(st.get(names[0]), st.get(names[i]));
				}
				catch (Exception e)
				{
					System.out.println(e);
					System.exit(1);
				}
		}

	}

	/**
	 * Determines if the symbol-graph contains {@code key}
	 * 
	 * @param key A name representing a vertex of the underlying graph
	 * @return Does the graph contain {@code key}
	 */
	public boolean contains(String key)
	{
		return st.contains(key);

	}

	/**
	 * Determines the integer name of the vertex represented by {@code key}
	 * 
	 * @param key A name representing a vertex of the underlying graph
	 * @return The integer name of the vertex represented by {@code key}
	 */
	public int index(String key)
	{
		return st.get(key);
	}

	/**
	 * Determines the name of the vertex represented by the {@code v}
	 * 
	 * @param v The integer name of a vertex of the underlying graph
	 * @return The name of the vertex represented by the {@code v}
	 */
	public String name(int v)
	{
		return keys[v];
	}

	/**
	 * Gives the underlying graph object
	 * 
	 * @return The underlying graph object
	 */
	public Graph G()
	{
		return G;
	}

	/**
	 * Test client -
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String filename = args[0];
		String delim = args[1];
		StringGraph sg = new StringGraph(filename, delim);

		System.out.println(filename + " : Key in your Query");
		Graph G = sg.G();
		while (StdIn.hasNextLine())
		{
			String s = StdIn.readLine();
			if (sg.contains(s))
				for (int w : G.adj(sg.index(s)))
					System.out.println("   " + sg.name(w));
			else
				System.out.println("Not found in database");
		}
	}

}
