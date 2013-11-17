/**
 * 
 */
package algo.paradigms.greedy.huffman;

/**
 * @author kempa
 *
 */
public class PrefixTreeNode implements Comparable<PrefixTreeNode>
{
	PrefixTreeNode left, right;
	double frequency;
	
	public int compareTo(PrefixTreeNode t)
	{
		return (int)(frequency * 100 - t.frequency * 100);
	}

	public PrefixTreeNode(PrefixTreeNode left, PrefixTreeNode right)
	{
		this.frequency = left.frequency + right.frequency;
		this.left = left;
		this.right = right;
	}

	public PrefixTreeNode(double frequency, PrefixTreeNode left, PrefixTreeNode right)
	{
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}

}
