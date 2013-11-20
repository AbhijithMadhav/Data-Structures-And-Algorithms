/**
 * 
 */
package ds.graphs;
/**
 * Exception indicating a non-existent vertex
 */
@SuppressWarnings("serial")
public
class NonExistentVertexException extends Exception
{
	public NonExistentVertexException(String s)
	{
		super(s);
	}
}