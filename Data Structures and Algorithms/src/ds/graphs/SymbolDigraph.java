package ds.graphs;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * A graph implementation using the adjacency list representation where
 * vertices with any type can also be represented
 * 
 * 
 */
public class SymbolDigraph<T>
{
	/**
	 * Used to convert string names of vertexes to integer names for internal
	 * graph processing
	 */
	private Map<T, Integer> st;
	/**
	 * Used to convert integer names of vertices to string name for the
	 * application
	 */
	private Vector<T> keys;
	/**
	 * The graph with integer names for vertices
	 */
	private Digraph G;

	public SymbolDigraph(int V)
	{
		st = new HashMap<T, Integer>();
		keys = new Vector<T>(V);
		for (int i = 0; i < V; i++)
			keys.add(null);
		G = new Digraph(V);
	}

	public void addEdge(T v, T w) throws InvalidEdgeException,
			SelfLoopExistsException, ParallelEdgeExistsException
	{
		// / Insert names into the hash table. Associate current size as the
		// value
		if (!contains(v))
			st.put(v, st.size());
		if (!contains(w))
			st.put(w, st.size());

		// Reverse index
		keys.set(st.get(v), v);
		keys.set(st.get(w), w);
		
		G.addEdge(st.get(v), st.get(w));
	}

	/**
	 * Determines if the symbol-graph contains {@code key}
	 * 
	 * @param key A name representing a vertex of the underlying graph
	 * @return Does the graph contain {@code key}
	 */
	public boolean contains(T key)
	{
		return st.containsKey(key);

	}

	/**
	 * Determines the integer name of the vertex represented by {@code key}
	 * 
	 * @param key A name representing a vertex of the underlying graph
	 * @return The integer name of the vertex represented by {@code key}
	 */
	public int index(T key)
	{
		return st.get(key);
	}

	/**
	 * Determines the name of the vertex represented by the {@code v}
	 * 
	 * @param v The integer name of a vertex of the underlying graph
	 * @return The name of the vertex represented by the {@code v}
	 */
	public T name(int v)
	{
		return keys.elementAt(v);
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
		
		SymbolDigraph<String> sg = new SymbolDigraph<String>(4);
		try
		{
			sg.addEdge("Main", "Object"); 
			sg.addEdge("A", "B");
			sg.addEdge("B", "A");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(sg);
		System.out.println(sg.keys);
	}

}
