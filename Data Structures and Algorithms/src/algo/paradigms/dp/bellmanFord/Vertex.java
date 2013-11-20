/**
 * 
 */
package algo.paradigms.dp.bellmanFord;

import ds.graphs.WeightedDirectedEdge;

public class Vertex 
{
	int label;
	double distTo; // shortest distance to source vertex
	WeightedDirectedEdge edgeTo; // edge connecting vertex to the spt
	boolean onQ; // To ensure that we don't add duplicates
	
	public Vertex(int label, double distTo)
	{
		this.label = label;
		this.distTo = distTo;
	}
}