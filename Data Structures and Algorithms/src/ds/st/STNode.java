package ds.st;
/*
 * Represents a key-value pair in the symbol table
 */
public class STNode<Key extends Comparable<Key>, Value>
{
	Key key; // key
	Value val; // associated value
	STNode<Key, Value> left, right; // links to subtrees
	int N; // # of links in the subtree rooted here
	int h; // height of subtree rooted here

	public STNode()
	{
		
	}
	public STNode(Key key, Value val, int N, int h)
	{
		this.key = key;
		this.val = val;
		this.N = N;
		this.h = h;
	}

	boolean color; // color of link from parent to this node. Not used in

	// plain

	// BST. Only in redBlack version

	/*
	 * Construct specified coloured nodes: Used by the red-black st
	 * implementation
	 */
	public STNode(Key key, Value val, int N, int h, boolean color)
	{
		this.key = key;
		this.val = val;
		this.N = N;
		this.h = h;
		this.color = color;
	}

}
