/**
 * 
 */
package algo.paradigms.greedy.huffman;

/**
 * @author kempa
 *
 */
public class PrefixTreeLeaf extends PrefixTreeNode
{
	char letter;
	
	public PrefixTreeLeaf(char letter, double frequency)
	{
		super(frequency, null, null);
		this.letter = letter;
	}

}
