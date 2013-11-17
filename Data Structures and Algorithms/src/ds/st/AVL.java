package ds.st;

/**
 * 
 * @author kempa
 * 
 * @param <Key>
 * @param <Value>
 */
public class AVL<Key extends Comparable<Key>, Value> extends
		BinarySearchTreeST<Key, Value>
{

	// Constants identifying the shape of an unbalanced tree
	private final static int LLEFT = 0;
	private final static int LRIGHT = 1;
	private final static int RRIGHT = 2;
	private final static int RLEFT = 3;
	private final static int BALANCED = 4;

	public AVL()
	{
		super();
	}

	public void put(Key key, Value val)
	{
		root = put(root, key, val);
	}

	/**
	 * Inserts given key-value pair in the subtree rooted by x
	 * 
	 * @param x
	 * @param key
	 * @param val
	 * @return A subtree which contains the inserted key-value pair
	 */
	private STNode<Key, Value> put(STNode<Key, Value> x, Key key, Value val)
	{
		// Found the place for the given key
		if (x == null)
			return new STNode<Key, Value>(key, val, 1, 0);

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		// Update the size and height of each node above
		x.N = size(x);
		x.h = height(x);

		// On the way up balance the subtree rooted at x
		return balance(x);
	}

	/**
	 * Balance tree rooted at x
	 * 
	 * @param x root of tree
	 * @return Balanced tree rooted at x
	 */
	private STNode<Key, Value> balance(STNode<Key, Value> x)
	{
		switch (getShape(x))
		{
		case LLEFT:
			return rotateRight(x);
		case LRIGHT:
			x.left = rotateLeft(x.left);
			return rotateRight(x);
		case RRIGHT:
			return rotateLeft(x);
		case RLEFT:
			x.right = rotateRight(x.right);
			return rotateLeft(x);
		default:
			// System.out.println(x.key + " Balanced");
			return x;
		}
	}

	/**
	 * Determines the shape of the tree
	 * 
	 * @param x
	 * @return
	 */
	private int getShape(STNode<Key, Value> x)
	{
		/*
		 * Balance factor of a node is the difference between the height of its
		 * left subtree and right subtree in that order.
		 */
		switch (height(x.left) - height(x.right))
		{
		case 2:
			switch (height(x.left.left) - height(x.left.right))
			{
			case 0: // can return LLEFT or LRIGHT
			case 1:
				return LLEFT;
			case -1:
				return LRIGHT;
			}
			break;
		case -2:
			switch (height(x.right.left) - height(x.right.right))
			{
			case 0: // can return LLEFT or LRIGHT
			case 1:
				return RLEFT;
			case -1:
				return RRIGHT;
			}
			break;
		}
		return BALANCED;
	}

	/**
	 * Rotate the subtree rooted at x to the left
	 * 
	 * @param x
	 * @return The rotated subtree
	 */
	private STNode<Key, Value> rotateLeft(STNode<Key, Value> x)
	{
		STNode<Key, Value> t = x.right;
		x.right = t.left;
		t.left = x;

		x.N = size(x);
		t.N = size(t);

		x.h = height(x);
		t.h = height(t);

		return t;
	}

	/**
	 * Rotate the subtree rooted at x to the right
	 * 
	 * @param x
	 * @return The rotated subtree
	 */
	private STNode<Key, Value> rotateRight(STNode<Key, Value> x)
	{
		STNode<Key, Value> t = x.left;
		x.left = x.left.right;
		t.right = x;

		x.N = size(x);
		t.N = size(t);

		x.h = height(x);
		t.h = height(t);

		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ST#delete(java.lang.Object)
	 */
	@Override
	public void delete(Key key)
	{
		root = delete(root, key);
	}

	private STNode<Key, Value> delete(STNode<Key, Value> x, Key key)
	{
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else
		{// cmp == 0, delete x
			if (x.left == null)
				return x.right;
			else if (x.right == null)
				return x.left;

			STNode<Key, Value> t = x; // temporary pointer to the node to be
										// deleted
			x = min(t.right); // get the inorder successor of t. successor
								// cannot have a left child
			x.right = deleteMin(t.right); // right subtree = t.right - succesor
			x.left = t.left;
		}

		// Update size and height on the way up
		x.N = size(x);
		x.h = height(x);

		return balance(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#deleteMin()
	 */
	@Override
	public void deleteMin()
	{
		deleteMin(root);
	}

	protected STNode<Key, Value> deleteMin(STNode<Key, Value> x)
	{

		if (x == null)
			return null;

		// No left child. This is the minimum node. Orphan it by returning its
		// right subtree
		if (x.left == null)
			return x.right;

		// the minimum lies in the left subtree
		x.left = deleteMin(x.left);

		// Update size and height on the way up(from the recursive calls)
		x.N = size(x);
		x.h = height(x);

		return balance(x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#deleteMax()
	 */
	@Override
	public void deleteMax()
	{
		deleteMax(root);

	}

	private STNode<Key, Value> deleteMax(STNode<Key, Value> x)
	{
		if (x == null)
			return null;

		// No right child. This is the maximum node. Orphan it by returning its
		// left subtree
		if (x.right == null)
			return x.left;

		// the maximum lies in the right subtree
		x.right = deleteMin(x.right);

		// Update size and height on the way up(from the recursive calls)
		x.N = size(x);
		x.h = height(x);

		return balance(x);
	}

	public boolean isBalanced()
	{
		return isBalanced(root);
	}

	private boolean isBalanced(STNode<Key, Value> x)
	{
		if (x == null)
			return true;

		int leftH = (x.left == null) ? -1 : x.left.h;
		int rightH = (x.right == null) ? -1 : x.right.h;
		if (Math.abs(leftH - rightH) > 1)
			return false;

		if (isBalanced(x.left))
			return isBalanced(x.right);
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AVL<String, Integer> st = new AVL<String, Integer>();
		/*
		 * st.put("C", 0);
		 * st.put("B", 0);
		 * st.put("A", 0);
		 */

		/*
		 * st.put("D", 0);
		 * st.put("B", 0);
		 * st.put("C", 0);
		 */

		/*
		 * st.put("A", 0);
		 * st.put("B", 0);
		 * st.put("C", 0);
		 */

		/*
		 * st.put("B", 0);
		 * st.put("D", 0);
		 * st.put("C", 0);
		 */

		// RR
		/*
		 * st.put("H", 0);
		 * st.put("C", 0);
		 * st.put("M", 0);
		 * st.put("R", 0);
		 * st.put("A", 0);
		 * st.put("E", 0);
		 * st.put("L", 0);
		 * st.put("P", 0);
		 * st.put("S", 0);
		 * st.put("X", 0);
		 */

		// RL
		st.put("H", 0);
		st.put("C", 0);
		st.put("M", 0);
		st.put("R", 0);
		st.put("A", 0);
		st.put("E", 0);
		st.put("L", 0);
		st.put("P", 0);
		st.put("S", 0);
		st.put("N", 0);
		st.delete("A");
		st.delete("E");
		st.printTree();
		if (!st.isBalanced())
		{
			System.err.println("Tree is not balanced");
		}
	}
}