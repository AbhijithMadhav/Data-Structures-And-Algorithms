package ds.st;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class BinarySearchTreeSTIterative<Key extends Comparable<Key>, Value>
		implements OrderedST<Key, Value>
{
	private STNode<Key, Value> root; // root of BST
	private STNode<Key, Value> cache; // For software caching

	public BinarySearchTreeSTIterative()
	{
		root = null;
		cache = new STNode<Key, Value>();
	}

	// return number of key-value pairs in BST
	@Override
	public int size()
	{
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	protected int size(STNode<Key, Value> x)
	{
		if (x == null)
			return 0;
		return x.N;
	}

	// is the symbol table empty?
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	// return the height of the BST
	public int height()
	{
		return height(root);
	}

	// return the height of the BST rooted at x
	protected int height(STNode<Key, Value> x)
	{
		if (x == null)
			return -1;
		return x.h;
	}

	/***********************************************************************
	 * Search BST for given key, and return associated value if found, return
	 * null if not found
	 ***********************************************************************/
	// does there exist a key-value pair with given key?
	@Override
	public boolean contains(Key key)
	{
		return get(key) == null;
	}

	// return value associated with the given key
	// Return null if no such key exists
	// Software caching implemented
	@Override
	public Value get(Key key)
	{
		if (cache.key != key)
		{
			cache.key = key;
			cache.val = get(root, key);
		}
		return cache.val; // return the previous value if the query is the same
							// as the previous
	}

	// Return value associated with key in the subtree rooted at x.
	// Return null if key is not present in subtree rooted at x.
	private Value get(STNode<Key, Value> x, Key key)
	{
		while (x != null)
		{
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				// cmp == 0
				return x.val;
		}
		return null;
	}

	/***********************************************************************
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value
	 ***********************************************************************/
	@Override
	public void put(Key key, Value val)
	{
		if (contains(key))
			root = put(root, key, val, true);
		else
			root = put(root, key, val, false);
	}

	// Insert specified key-value pair into the subtree rooted at x and return
	// it
	private STNode<Key, Value> put(STNode<Key, Value> x, Key key, Value val,
			boolean update)
	{
		STNode<Key, Value> savedRoot = x;

		// Save parent of x while travelling down. Needed to insert x
		STNode<Key, Value> p = null;
		while (x != null)
		{
			p = x;
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
			{
				if (!update)
					p.N++;
				x = x.left;
			}
			else if (cmp > 0)
			{
				if (!update)
					p.N++;
				x = x.right;
			}
			else
			{// cmp == 0. Key already present. Update the value and return
				assert update;
				x.val = val;
				return savedRoot;
			}
		}

		assert !update;
		STNode<Key, Value> t = new STNode<Key, Value>(key, val, 1, 0);

		// if tree is empty
		if (p == null)
		{
			root = t;
			return root;
		}

		boolean adjustHeight = false;
		if (key.compareTo(p.key) < 0)
		{
			p.left = t;
			if (p.right == null)
				adjustHeight = true;
		}
		else
		{
			p.right = t;
			if (p.left == null)
				adjustHeight = true;
		}

		if (adjustHeight)
		{
			x = savedRoot;
			while (x != null)
			{
				x.h++;
				int cmp = key.compareTo(x.key);
				if (cmp < 0)
					x = x.left;
				else if (cmp > 0)
					x = x.right;
				else
					// cmp = 0
					break;
			}
		}
		return savedRoot;
	}

	/***********************************************************************
	 * Delete
	 ***********************************************************************/
	@Override
	public void deleteMin()
	{
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow");
		root = deleteMin(root);
		// assert check();
	}

	// return BST-subtree rooted at x minus the STNode containing the min key
	private STNode<Key, Value> deleteMin(STNode<Key, Value> x)
	{
		STNode<Key, Value> savedRoot = x;

		STNode<Key, Value> p = null;
		while (x.left != null)
		{
			p = x;
			p.N--;
			x = x.left;
		}

		if (p == null)
		{
			root = null;
			return root;
		}

		p.left = null; // delete
		if (p.right == null) // adjust height
		{
			x = savedRoot;
			while (x != null)
			{
				x.h--;
				x = x.left;
			}
		}
		return savedRoot;
	}

	@Override
	public void deleteMax()
	{
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow");
		root = deleteMax(root);
		// assert check();
	}

	private STNode<Key, Value> deleteMax(STNode<Key, Value> x)
	{
		STNode<Key, Value> savedRoot = x;

		STNode<Key, Value> p = null;
		while (x.right != null)
		{
			p = x;
			p.N--;
			x = x.right;
		}

		if (p == null)
		{
			root = null;
			return root;
		}

		p.right = null;
		if (p.left == null) // adjust height
		{
			x = savedRoot;
			while (x != null)
			{
				x.h--;
				x = x.right;
			}
		}
		
		return savedRoot;
	}

	@Override
	public void delete(Key key)
	{
		if (contains(key))
			root = delete(root, key);
		// assert check();
	}

	// key must be present
	private STNode<Key, Value> delete(STNode<Key, Value> x, Key key)
	{
		STNode<Key, Value> savedRoot = x;

		STNode<Key, Value> p = null;
		while (x != null)
		{
			int cmp = x.key.compareTo(key);
			if (cmp < 0)
			{
				p = x;
				p.N--;
				x = x.left;
			}
			else if (cmp > 0)
			{
				p = x;
				p.N--;
				x = x.right;
			}
			else
			// cmp == 0
			{
				if (p.left == x && x.left == null)
					p.left = x.right;
				else if (p.right == x && x.left == null)
					p.right = x.right;
				else if (p.left == x && x.right == null)
					p.left = x.left;
				else if (p.right == x && x.right == null)
					p.right = x.left;
				else
				// non-empty left and right subtrees
				{
					// replace with in-order successor
					STNode<Key, Value> s = min(x.right);
					x.key = s.key;
					x.val = s.val;
					deleteMin(x.right);
				}
			}
		}
		return savedRoot;
	}

	/***********************************************************************
	 * Min, max, floor, and ceiling
	 ***********************************************************************/
	@Override
	public Key min()
	{
		if (isEmpty())
			return null;
		return min(root).key;
	}

	// Return the node with the minimum key
	// The leftmost node is the node with the minimum key
	protected STNode<Key, Value> min(STNode<Key, Value> x)
	{
		if (x.left == null)
			return x;

		while (x.left != null)
			x = x.left;

		return x;
	}

	@Override
	public Key max()
	{
		if (isEmpty())
			return null;
		return max(root).key;
	}

	// return the node with the maximum key
	// The leftmost node is the node with the maximum key
	private STNode<Key, Value> max(STNode<Key, Value> x)
	{
		if (x.right == null)
			return x;

		while (x.right != null)
			x = x.right;

		return x;
	}

	// return the greatest key lesser than 'key'
	// return null if 'key' is lesser than min()
	@Override
	public Key floor(Key key)
	{
		STNode<Key, Value> x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	// return the node containing the floor of key in the subtree rooted at x
	private STNode<Key, Value> floor(STNode<Key, Value> x, Key key)
	{
		while(x != null)
		{
			int cmp = key.compareTo(x.key);
			if (cmp < 0) // floor is in left subtree
				x = x.left;
			else if (cmp > 0)
				return x;
			else
				return x;
		}
		return x;
		
	}
	@Override
	public Iterable<Key> keys()
	{
		LinkedList<Key> list = new LinkedList<Key>();
		keys(root, list);
		return list;
	}

	private void keys(STNode<Key, Value> x, LinkedList<Key> list)
	{
		if (x == null)
			return;
		keys(x.left, list);
		list.add(x.key);
		keys(x.right, list);
	}

	public static void main(String[] args)
	{
		String[] a = StdIn.readAll().split("\\s+");
		int N = a.length;
		BinarySearchTreeSTIterative<String, Integer> st = new BinarySearchTreeSTIterative<String, Integer>();
		for (int i = 0; i < N; i++)
			st.put(a[i], i);
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#rank(java.lang.Object)
	 */
	@Override
	public int rank(Key key)
	{
		System.out.println("Not implemented");
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#select(int)
	 */
	@Override
	public Key select(int k)
	{
		System.out.println("Not implemented");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#size(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int size(Key lo, Key hi)
	{
		System.out.println("Not implemented");
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see OrderedST#keys(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Iterable<Key> keys(Key lo, Key hi)
	{
		System.out.println("Not implemented");
		return null;
	}

	/* (non-Javadoc)
	 * @see OrderedST#ceiling(java.lang.Object)
	 */
	@Override
	public Key ceiling(Key key)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
