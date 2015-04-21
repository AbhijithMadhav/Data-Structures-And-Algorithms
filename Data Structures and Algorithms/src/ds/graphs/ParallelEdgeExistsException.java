package ds.graphs;


/**
 * Exception indicating a parallel edge
 */
@SuppressWarnings("serial")
public
class ParallelEdgeExistsException extends Exception
{
	ParallelEdgeExistsException(String s)
	{
		super(s);
	}
}
