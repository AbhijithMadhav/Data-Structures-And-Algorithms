package ds.st;
import java.util.LinkedList;
import java.util.Random;

public class BinarySearchTreeST<Key extends Comparable<Key>, Value> implements
		OrderedST<Key, Value>
{

	protected STNode<Key, Value> root; // root of BST
	private STNode<Key, Value> cache; // For software caching

	/*
	 * Construct a new binary search tree
	 */
	public BinarySearchTreeST()
	{
		root = null;
		cache = new STNode<Key, Value>();
	}

	// is the symbol table empty?
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	// return the size of the BST after calculating it recursively
	public int size()
	{
		return size(root);
	}

	// return the size of the BST rooted at x after calculating it recursively
	protected int size(STNode<Key, Value> x)
	{
		if (x == null)
			return 0;
		else
			return (size(x.left) + 1 + size(x.right));
	}

	// return the height of the BST by calculating it recursively
	public int height()
	{
		return height(root);
	}

	// return the height of the BST rooted at x by calculating it recursively
	protected int height(STNode<Key, Value> x)
	{
		if (x == null)
			return -1;

		return Math.max(height(x.left), height(x.right)) + 1;
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

	// return value associated with the given key, or null if no such key exists
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
		if (x == null)
			return null; // Leaves reached. Key not found.

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key); // search left subtree recursively
		else if (cmp > 0)
			return get(x.right, key); // search right subtree recursively
		else
			return x.val;// cmp == 0. Key found
	}

	/***********************************************************************
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value
	 ***********************************************************************/
	@Override
	public void put(Key key, Value val)
	{
		root = put(root, key, val);
	}

	private STNode<Key, Value> put(STNode<Key, Value> x, Key key, Value val)
	{
		if (x == null) // key does not exist. Insert as a leaf
			return new STNode<Key, Value>(key, val, 1, 0);

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;

		// On the way up the tree set the size and height of the trees
		x.N = size(x);
		x.h = height(x);

		return x;
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
	protected STNode<Key, Value> deleteMin(STNode<Key, Value> x)
	{
		// No left child. This is the minimum node. Orphan it by returning its
		// right subtree
		if (x.left == null)
			return x.right;

		// the minimum lies in the left subtree
		x.left = deleteMin(x.left);

		// Update size and height on the way up(from the recursive calls)
		x.N = size(x);
		x.h = height(x);

		return x;
	}

	@Override
	public void deleteMax()
	{
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow");
		root = deleteMax(root);
		assert check();
	}

	private STNode<Key, Value> deleteMax(STNode<Key, Value> x)
	{
		// No right child? This is the max. Orphan it by returning its right
		// subtree
		if (x.right == null)
			return x.left;

		// Max is in the right subtree
		x.right = deleteMax(x.right);

		// Update size and height on the way up
		x.N = size(x);
		x.h = height(x);

		return x;
	}

	@Override
	public void delete(Key key)
	{
		root = delete(root, key);
		assert check();
	}

	// return a BST-subtree rooted at x minus the node containing key
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

		return x;
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

	// return the node with the minimum key
	// The leftmost node is the node with the minimum key
	protected STNode<Key, Value> min(STNode<Key, Value> x)
	{
		if (x.left == null)
			return x;
		return min(x.left);
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
		return max(x.right);
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
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp < 0) // floor is in the left subtree
			return floor(x.left, key);

		STNode<Key, Value> t = floor(x.right, key); // floor might be in the
													// right
													// subtree
		if (t != null)
			return t;
		else
			return x; // floor turned out to be x itself
	}

	// return the smallest key lesser than 'key'
	// return null if 'key' is greater than max()
	@Override
	public Key ceiling(Key key)
	{
		STNode<Key, Value> x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	// return the node containing the ceiling of key in the subtree rooted at x
	private STNode<Key, Value> ceiling(STNode<Key, Value> x, Key key)
	{
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceiling(x.right, key);

		STNode<Key, Value> t = ceiling(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}

	/***********************************************************************
	 * Rank and selection
	 ***********************************************************************/
	/*
	 * return no. of elements between lo and hi
	 */
	@Override
	public int size(Key lo, Key hi)
	{
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	// return key with rank, 'rank'
	// return null if no key exists with rank, 'rank'
	@Override
	public Key select(int rank)
	{
		STNode<Key, Value> x = select(root, rank);
		if (x != null)
			return x.key;
		else
			return null;
	}

	// return node containing key with rank k in the subtree rooted at x
	private STNode<Key, Value> select(STNode<Key, Value> x, int rank)
	{
		if (x == null)
			return null;

		int t = size(x.left);
		if (t > rank)
			return select(x.left, rank);
		else if (t < rank)
			return select(x.right, rank - (t + 1));
		else
			return x;
	}

	@Override
	public int rank(Key key)
	{
		return rank(root, key);
	}

	private int rank(STNode<Key, Value> x, Key key)
	{
		if (x == null)
			return 0;

		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key);
		else if (cmp > 0)
			return rank(x.right, key) + size(x.left) + 1;
		else
			return size(x.left);
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
		System.out.println(x.key + ":" + x.h);

	}

	@Override
	public Iterable<Key> keys()
	{
		return keys(min(), max());
	}

	@Override
	public Iterable<Key> keys(Key lo, Key hi)
	{
		LinkedList<Key> list = new LinkedList<Key>();
		keys(root, list, lo, hi);
		return list;
	}

	// enumerate the keys between [lo, hi] w.r.t to x
	private void keys(STNode<Key, Value> x, LinkedList<Key> list, Key lo, Key hi)
	{
		if (x == null)
			return;

		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);

		if (cmplo < 0) // if lo is lesser than the key
			keys(x.left, list, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) // if key is inbetween lo and hi
			list.add(x.key);
		if (cmphi > 0) // if hi is greater than key
			keys(x.right, list, lo, hi);
	}

	/*************************************************************************
	 * Check integrity of BST data structure
	 *************************************************************************/
	private boolean check()
	{
		if (!isSizeConsistent(root))
		{
			System.out.println("Size is not consistent");
			return false;
		}
		if (!isRankConsistent(root))
		{
			System.out.println("Rank is not consistent");
			return false;
		}
		return true;
	}

	// are the size fields correct?
	private boolean isSizeConsistent(STNode<Key, Value> x)
	{
		if (x != null)
			if (x.N != size(x.left) + 1 + size(x.right))
				return false;
		return true;
	}

	// check that ranks are consistent
	private boolean isRankConsistent(STNode<Key, Value> x)
	{
		for (int i = 0; i < size(x); i++)
			if (i != rank(select(i)))
			{
				return false;
			}
		for (Key key : keys())
			if (key.compareTo(select(rank(key))) != 0)
				return false;
		return true;
	}

	/*
	 * Test client
	 */
	public static void main(String args[])
	{
		BinarySearchTreeST<String, Integer> st;
		st = new BinarySearchTreeST<String, Integer>();

		String pool[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };

		Random r = new Random();
		for (int i = 1; i <= pool.length; i++)
		{
			String key = pool[r.nextInt(pool.length)];
			st.put(key, i);
		}

		System.out.println("Testing min(), max(), size(), height()");
		System.out.println("Min = " + st.min());
		System.out.println("Max = " + st.max());
		assert (st.size() == st.size());
		System.out.println("Size = " + st.size());
		assert (st.height() == st.height());
		System.out.println("Height = " + st.height());

		System.out
				.println("\nTesting keys(), put(), get(), rank(), select(), ceiling(), floor()");
		System.out.println("Key\tValue\tRank\tSelect\tFloor\tCeiling");
		System.out.println("----------------------------------------");
		for (int i = 0; i < pool.length; i++)
		{
			System.out.println(pool[i] + "\t" + st.get(pool[i]) + "\t"
					+ st.rank(pool[i]) + "\t" + st.select(st.rank(pool[i]))
					+ "\t" + st.floor(pool[i]) + "\t" + st.ceiling(pool[i]));
		}

		System.out.print("\nTesting deleteMax() on ");
		for (String s : st.keys())
			System.out.print(s);
		System.out.println();
		while (st.size() != 0)
		{
			st.deleteMax();
			System.out.print("keys : ");
			for (String s : st.keys())
				System.out.print(s);
			System.out.println();
		}

		for (int i = 1; i <= pool.length; i++)
		{
			String key = pool[r.nextInt(pool.length)];
			st.put(key, i);
		}
		System.out.print("\nTesting deleteMin() on ");
		for (String s : st.keys())
			System.out.print(s);
		System.out.println();
		while (st.size() != 0)
		{
			st.deleteMin();
			System.out.print("keys : ");
			for (String s : st.keys())
				System.out.print(s);
			System.out.println();
		}

		for (int i = 1; i <= pool.length; i++)
		{
			String key = pool[r.nextInt(pool.length)];
			st.put(key, i);
		}
		System.out.print("\nTesting delete() on ");
		for (String s : st.keys())
			System.out.print(s);
		System.out.println();
		while (st.size() != 0)
		{
			String key = st.select(r.nextInt(st.size()));
			st.delete(key);
			System.out.print("After deleting " + key + ": ");
			for (String s : st.keys())
				System.out.print(s);
			System.out.println();
		}
		// st.printTree();
	}
}