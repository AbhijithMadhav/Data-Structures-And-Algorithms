package ds.st;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class BinarySearchST<Key extends Comparable<Key>, Value> implements
		OrderedST<Key, Value>
{
	private Key[] keys;
	private Value[] vals;
	private int N;

	// create an empty symbol table with given initial capacity
	public BinarySearchST(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	// number of key-value pairs in the table
	@Override
	public int size()
	{
		return N;
	}

	@Override
	public int size(Key lo, Key hi)
	{
		return rank(lo) - rank(hi);
	}

	// is the symbol table empty?
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public int rank(Key key)
	{
		return rank(key, 0, N - 1);
	}

	@Override
	public Key min()
	{
		return keys[0];
	}

	@Override
	public Key max()
	{
		return keys[N - 1];
	}

	// is the key in the table?
	@Override
	public boolean contains(Key key)
	{
		return get(key) != null;
	}

	@Override
	public Key select(int rank)
	{
		if (rank < 0 || rank >= N)
			return null;
		return keys[rank];
	}

	// return the number of keys in the table that are smaller than given key
	private int rank(Key key, int lo, int hi)
	{
		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid; // key found
		}
		return lo; // key not found
	}

	@Override
	public Key ceiling(Key key)
	{
		return keys[rank(key)];
	}

	@Override
	public Key floor(Key key)
	{
		return keys[rank(key) - 1];
	}

	@Override
	public Iterable<Key> keys(Key firstKey, Key lastKey)
	{
		LinkedList<Key> l = new LinkedList<Key>();
		int lastKeyIndex = rank(lastKey);
		for (int i = rank(firstKey); i < lastKeyIndex; i++)
			l.add(keys[i]);
		if (contains(lastKey))
			l.add(keys[lastKeyIndex]);
		return l;
	}

	@Override
	public Iterable<Key> keys()
	{
		return keys(keys[0], keys[N - 1]);
	}

	// resize the underlying arrays
	private void resize(int capacity)
	{
		assert capacity >= N;
		Key k[] = (Key[]) new Comparable[capacity];
		Value v[] = (Value[]) new Object[capacity];

		System.arraycopy(keys, 0, k, 0, (keys.length < k.length) ? keys.length
				: k.length);
		keys = k;

		System.arraycopy(vals, 0, v, 0, vals.length);
		vals = v;
	}

	// return the value associated with the given key, or null if no such key
	@Override
	public Value get(Key key)
	{
		if (isEmpty())
			return null;

		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0)
			return vals[i];
		return null;
	}

	// Search for key. Update value if found; grow table if new.
	@Override
	public void put(Key key, Value val)
	{
		if (val == null)
		{
			delete(key);
			return;
		}

		int i = rank(key);

		// key is already in table. Update
		if (i < N && key.compareTo(keys[i]) == 0)
		{
			vals[i] = val;
			return;
		}

		// insert new key-value pair
		if (N == keys.length)
			resize(keys.length * 2);

		for (int j = N; j > i; j--)
		{
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	// Remove the key-value pair if present
	@Override
	public void delete(Key key)
	{
		if (isEmpty())
			return;

		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0)
		{
			for (int j = i; j < N - 1; j++)
			{
				keys[j] = keys[j + 1];
				vals[j] = vals[j + 1];
			}

			N--;
			keys[N] = null; // free memory
			keys[N] = null; // free memory

			// resize if 1/4 full
			if (N == keys.length / 4)
				resize(keys.length / 4);
		}
	}

	// delete the minimum key and its associated value
	public void deleteMin()
	{
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow error");
		delete(min());
	}

	// delete the maximum key and its associated value
	public void deleteMax()
	{
		if (isEmpty())
			throw new RuntimeException("Symbol table underflow error");
		delete(max());
	}
}
