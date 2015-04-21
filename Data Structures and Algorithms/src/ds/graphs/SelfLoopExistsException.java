package ds.graphs;


/**
 * Exception indicating a self loop
 */
@SuppressWarnings("serial")
public class SelfLoopExistsException extends Exception
{
	SelfLoopExistsException(String s)
	{
		super(s);
	}
}
