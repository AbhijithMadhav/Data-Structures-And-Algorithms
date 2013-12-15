/**
 * 
 */
package algo.graphs.networkFlow;

/**
 * @author kempa
 *
 */
public class FlowEdge
{
	private final int to;
	private final int from;
	private final double capacity;
	private double flow;
	
	/**
	 * Create a flow edge from -> to
	 * @param to
	 * @param from
	 * @param capacity
	 */
	public FlowEdge(int to, int from, double capacity)
	{
		this.to = to;
		this.from = from;
		this.capacity = capacity;
		flow = 0;
	}
	
	public int from()
	{
		return from;
	}
	
	public int to()
	{
		return to;
	}
	
	public int other(int v)
	{
		if (v == to)
			return to;
		else if (v == from)
			return from;
		throw new RuntimeException("Illegal endpoint");
	}
	
	public double capacity()
	{
		return capacity;
	}
	
	public double flow()
	{
		return flow;
	}
	
	/**
	 * Residual capacity towards v
	 * @param v
	 * @return
	 */
	public double residualCapacity(int v)
	{
		if (v == from) // backward edge
			return flow;
		else if (v == to) // forward edge
			return capacity - flow;
		throw new IllegalArgumentException();
	}
	
	/**
	 * Add residual flow towards v
	 * @param v
	 * @param delta
	 */
	public void addResidualFlowTo(int v, double delta)
	{
		if (v == from) // backward edge
			flow -= delta;
		else if (v == to) // forward edge
			flow += delta;
		throw new IllegalArgumentException();
	}
}
