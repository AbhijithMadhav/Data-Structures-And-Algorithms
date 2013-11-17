package ds.st;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author kempa
 * 
 */
public class LinearProbingHashST<Key, Value> implements ST<Key, Value>
{
	private int M;
	private int N;
	private Key keys[];
	private Value vals[];
	private static final int INIT_CAPACITY = 4;

	public LinearProbingHashST()
	{
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int M)
	{
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
		this.M = M;
		N = 0;
	}

	// return number of key-value pairs in symbol table
	@Override
	public int size()
	{
		return N;
	}

	// is the symbol table empty?
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public boolean contains(Key key)
	{
		return get(key) != null;
	}

	// hash value between 0 and M-1
	int hash(Key key)
	{
		// assuming that the type 'Key' will have implemented a hash code which
		// takes into account all bits of the object
		return (key.hashCode() & 0x7fffffff) % M;
	}

	// return value associated with key, null if no such key
	@Override
	public Value get(Key key)
	{
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (key.equals(keys[i]))
				return vals[i];
		return null;
	}

	// insert key-value pair into the table
	@Override
	public void put(Key key, Value val)
	{
		if (val == null)
			delete(key);

		if (N >= M / 2)
			resize(2 * M);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (key.equals(keys[i]))
			{
				vals[i] = val;
				return;
			}

		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	@Override
	public void delete(Key key)
	{
		if (key == null)
			return;

		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (key.equals(keys[i]))
			{ 
				// delete
				keys[i] = null;
				vals[i] = null;
				N--;
				
				// Re-insert all keys and vals which are to the right of keys[i],
				// thereby ensuring that the hash table is consistent for future
				// searches of keys with the same has as i
				for (int j = (i + 1) % M; keys[j] != null; j = (j + 1) % M)
				{
					Key keyToReInsert = keys[j];
					Value valToReInsert = vals[j];

					keys[j] = null;
					vals[j] = null;
					N--;
					put(keyToReInsert, valToReInsert);
				}
				break;
			}

		if (N > INIT_CAPACITY && N < M / 8)
			resize(M / 2);
	}

	private void resize(int capacity)
	{
		LinearProbingHashST<Key, Value> t = new LinearProbingHashST<Key, Value>(
				capacity);
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);

		M = t.M;
		N = t.N;
		keys = t.keys;
		vals = t.vals;
	}

	// return keys in symbol table as an Iterable
	@Override
	public Iterable<Key> keys()
	{
		LinkedList<Key> list = new LinkedList<Key>();
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				list.add(keys[i]);
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++)
		{
			String key = StdIn.readString();
			st.put(key, i);
		}

		// print keys
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));

		for (String s : st.keys())
			st.delete(s);

		if (st.isEmpty())
			System.out.println("Successfully deleted all items");
		else
			System.out.println("Unsuccessful in deleting all items");

	}
}
