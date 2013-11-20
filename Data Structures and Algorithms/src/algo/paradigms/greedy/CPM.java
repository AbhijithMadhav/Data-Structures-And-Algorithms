package algo.paradigms.greedy;

import ds.graphs.WeightedDirectedEdge;
import ds.graphs.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.StdIn;

public class CPM
{
	public static void main(String[] args)
	{
		int N = StdIn.readInt();
		StdIn.readLine();

		// 2 verticies for each job, 1 for the start vertex and 1 for the
		// terminal vertex
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);
		int s = 2 * N, t = 2 * N + 1;

		// Add edges
		for (int i = 0; i < N; i++)
		{
			String[] a = StdIn.readLine().split("\\s+");
			G.addEdge(new WeightedDirectedEdge(i, i + N, Double.parseDouble(a[0]))); // Job
																				// edge
			G.addEdge(new WeightedDirectedEdge(s, i, 0.0)); // Edge from start vertex
			G.addEdge(new WeightedDirectedEdge(i + N, t, 0.0)); // Edge to end vertex

			// Precedence constraints
			for (int j = 1; j < a.length; j++)
				G.addEdge(new WeightedDirectedEdge(i + N, Integer.parseInt(a[j]), 0.0));
		}

		AcyclicLP lp = new AcyclicLP(G, s);

		System.out.println("Start times:");
		for (int i = 0; i < N; i++)
			System.out.println(String.format("%4d: %5.1f", i, lp.distTo(i)));
		System.out.println("Finish time: "
				+ String.format("%5.1f", lp.distTo(t)));

	}
}
