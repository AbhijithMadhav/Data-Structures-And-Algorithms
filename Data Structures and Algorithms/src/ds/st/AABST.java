package ds.st;

// left leaning red black trees
public class AABST<Key extends Comparable<Key>, Value> extends
		BinarySearchTreeST<Key, Value> implements ST<Key, Value>
{

	protected STNode<Key, Value> root; // root of BST
	private boolean RED = true;
	private boolean BLACK = false;

	public AABST()
	{
		root = null;
	}

	private boolean isRed(STNode<Key, Value> x)
	{
		if (x == null)
			return false;
		return x.color == RED;
	}

	private void flipColor(STNode<Key, Value> h)
	{
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	private STNode<Key, Value> rotateLeft(STNode<Key, Value> h)
	{
		STNode<Key, Value> x = h.right;
		h.right = x.left;
		x.left = h;

		x.color = h.color;
		h.color = RED;

		h.N = size(h.left) + size(h.right) + 1;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private STNode<Key, Value> rotateRight(STNode<Key, Value> h)
	{
		STNode<Key, Value> x = h.left;
		h.left = x.right;
		x.right = h;

		x.color = h.color;
		h.color = RED;

		h.N = size(h.left) + size(h.right) + 1;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	@Override
	public void put(Key key, Value val)
	{
		root = put(root, key, val);
		root.color = BLACK;
	}

	private STNode<Key, Value> put(STNode<Key, Value> h, Key key, Value val)
	{
		if (h == null)
			return new STNode<Key, Value>(key, val, 1, 0, RED);
		else
		{
			int cmp = key.compareTo(h.key);

			if (cmp < 0)
				h.left = put(h.left, key, val);
			else if (cmp > 0)
				h.right = put(h.right, key, val);
			else
				h.val = val;

			// on the way up
			if (isRed(h.right) && !isRed(h.left))
				h = rotateLeft(h);
			if (isRed(h.left) && isRed(h.left.left))
				h = rotateRight(h);
			if (isRed(h.left) && isRed(h.right))
				flipColor(h);

			h.N = size(h.left) + size(h.right) + 1;
			h.h = Math.max(height(h.left), height(h.right)) + 1;
			return h;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ST#delete(java.lang.Object)
	 */
	@Override
	public void delete(Key key)
	{
		System.out.println("Not Implemented");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#deleteMin()
	 */
	@Override
	public void deleteMin()
	{
		System.out.println("Not Implemented");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#deleteMax()
	 */
	@Override
	public void deleteMax()
	{
		System.out.println("Not Implemented");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		AABST<String, Integer> st = new AABST<String, Integer>();
		// for (int i = 0; i < 109; i++)
		// st.put(i, i);
		//

		st.put("H", 0);
		st.put("C", 0);
		st.put("M", 0);
		st.put("R", 0);
		st.put("A", 0);
		st.put("E", 0);
		st.put("L", 0);
		st.put("P", 0);
		st.put("S", 0);
		st.put("X", 0);
		st.printTree();

	}

	public void printTree()
	{
		printTree(root);
	}

	private void printTree(STNode<Key, Value> x)
	{
		if (x == null)
			System.out.println("*");
		else
		{
			printTree(x.left);
			printSTNode(x);
			printTree(x.right);
		}

	}

	private void printSTNode(STNode<Key, Value> x)
	{
		for (int i = 0; i <= x.h; i++)
			System.out.print("\t");
		if (x.color)
			System.out.print("\t");
		System.out.println(x.key);

	}
}