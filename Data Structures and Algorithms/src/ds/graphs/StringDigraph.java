package ds.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import edu.princeton.cs.introcs.In;

/**
 * A graph implementation using the adjacency list representation where
 * vertices with strings can also be represented
 * 
 * 
 */
public class StringDigraph
{
	/**
	 * Used to convert string names of vertexes to integer names for internal
	 * graph processing
	 */
	private Map<String, Integer> st;
	/**
	 * Used to convert integer names of vertices to string name for the
	 * application
	 */
	private String keys[];
	/**
	 * The graph with integer names for vertices
	 */
	private Digraph G;

	/**
	 * Builds graph specified in {@code filename} using {@code delim} to
	 * separate vertex names
	 * 
	 * @param filename File containing edges specifying the graph
	 * @param delim Delimiter used to separate vertex names
	 */
	public StringDigraph(String filename, String delim)
	{
		In in = new In(filename);

		// Create the index
		st = new HashMap<String, Integer>();
		while (in.hasNextLine())
		{
			StringTokenizer tokenizer = new StringTokenizer(in.readLine(), delim);
			while (tokenizer.hasMoreTokens())
			{
				String s = tokenizer.nextToken();
				if (!contains(s))
					st.put(s, st.size());
			}
		}

		// Create the reverse index
		keys = new String[st.size()];
		for (String name : st.keySet())
			keys[st.get(name)] = name;

		// Create the graph
		G = new Digraph(st.size());
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

	public StringDigraph(int V)
	{
		st = new HashMap<String, Integer>();
		keys = new String[V];
		G = new Digraph(V);
	}

	public void addEdge(String v, String w) throws InvalidEdgeException,
			SelfLoopExistsException, ParallelEdgeExistsException
	{
		// / Insert names into the hash table. Associate current size as the
		// value
		if (!contains(v))
			st.put(v, st.size());
		if (!contains(w))
			st.put(w, st.size());

		// Reverse index
		keys[st.get(v)] = v;
		keys[st.get(w)] = w;

		G.addEdge(st.get(v), st.get(w));
	}

	/**
	 * Determines if the symbol-graph contains {@code key}
	 * 
	 * @param key A name representing a vertex of the underlying graph
	 * @return Does the graph contain {@code key}
	 */
	public boolean contains(String key)
	{
		return st.containsKey(key);

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
	public Digraph G()
	{
		return G;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String g = G().V() + " vertices " + G().E() + " edges\n";
		for (int v = 0; v < G().V(); v++)
		{
			g += name(v) + "(" + v + "): ";
			for (int w : G().adj(v))
				g += name(w) + "(" + w + "), ";
			g += "\n";
		}
		return g;
	}

	/**
	 * Test client -
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		 * String filename = args[0];
		 * String delim = args[1];
		 * SymbolDigraph sg = new SymbolDigraph(filename, delim);
		 * 
		 * System.out.println(filename + " : Key in your Query");
		 * Digraph G = sg.G();
		 * while (StdIn.hasNextLine())
		 * {
		 * String s = StdIn.readLine();
		 * if (sg.contains(s))
		 * for (int w : G.adj(sg.index(s)))
		 * System.out.println("   " + sg.name(w));
		 * else
		 * System.out.println("Not found in database");
		 * }
		 */
		/*SymbolDigraph sg = new SymbolDigraph(4);
		sg.addEdge("Main", "Object");
		sg.addEdge("A", "B");
		sg.addEdge("B", "A");
		System.out.println(sg);*/
	}

}
