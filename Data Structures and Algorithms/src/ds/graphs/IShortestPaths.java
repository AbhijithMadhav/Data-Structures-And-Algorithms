package ds.graphs;

public interface IShortestPaths
{
	double distTo(int v);
	boolean hasPathTo(int v);
	Iterable<WeightedDirectedEdge> pathTo(int v);
}
