/**
 * 
 */
package algo.paradigms.greedy.huffman;

import java.util.HashMap;
import java.util.PriorityQueue;

public class PrefixTree
{
	private PriorityQueue<PrefixTreeNode> pq;
	private HashMap<Character, String> code;
	private PrefixTreeNode root;

	public PrefixTree(char[] letter, double frequency[])
	{
		pq = new PriorityQueue<PrefixTreeNode>();
		for (int i = 0; i < letter.length; i++)
			pq.add(new PrefixTreeLeaf(letter[i], frequency[i]));

		while (pq.size() > 1)
		{
			PrefixTreeNode left = pq.remove();
			PrefixTreeNode right = pq.remove();
			pq.add(new PrefixTreeNode(left, right));
		}
		root = pq.remove();

		code = new HashMap<Character, String>();
		calculatePrefixCode(root, new String(""));
	}

	private void calculatePrefixCode(PrefixTreeNode t, String s)
	{
		if (t instanceof PrefixTreeLeaf)
		{
			code.put(((PrefixTreeLeaf)t).letter, s);
			return;
		}
		calculatePrefixCode(((PrefixTreeNode) t).left, s + '0');
		calculatePrefixCode(((PrefixTreeNode) t).right, s + '1');
	}

	public String getCode(char letter)
	{
		return code.get(letter);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		char letter[] = { 'a', 'b', 'c', 'd', 'e', 'f' };
		double frequency[] = { 0.45, 0.13, 0.12, 0.16, 0.09, 0.05 };

		PrefixTree pt = new PrefixTree(letter, frequency);
		for (int i = 0; i < letter.length; i++)
			System.out.println(letter[i] + " : " + pt.getCode(letter[i]));
	}
}