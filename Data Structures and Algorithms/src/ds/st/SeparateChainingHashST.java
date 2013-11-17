package ds.st;
import java.util.Collection;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author kempa
 * 
 */
public class SeparateChainingHashST<Key, Value> implements ST<Key, Value>
{
	private static final int INIT_CAPACITY = 4;
	private int N;
	private int M;
	private SequentialSearchST<Key, Value>[] st;

	// create separate chaining hash table
	public SeparateChainingHashST()
	{
		this(INIT_CAPACITY);
	}

	// create separate chaining hash table with M lists
	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int M)
	{
		this.M = M;
		N = 0;
		
		// create array of lists
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		
		// intialize each list
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<Key, Value>();

	}

	// is the key in the symbol table?
	@Override
	public boolean contains(Key key)
	{
		return st[hash(key)].get(key) != null;
	}

	// is the symbol table empty?
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	// return number of key-value pairs in symbol table
	@Override
	public int size()
	{
		return N;
	}

	// hash value between 0 and M-1
	private int hash(Key key)
	{
		// assuming that the type 'Key' will have implemented a hashcode which
		// takes into account all bits of the object
		return (key.hashCode() & 0x7fffffff) % M;
	}

	// insert key-value pair into the table
	@Override
	public void put(Key key, Value val)
	{
		if (N > M * 10)
			resize(2 * M);

		if (val == null)
		{
			delete(key);
			return;
		}

		int i = hash(key);
		if (!st[i].contains(key)) // Note: More efficient than contains(key) as
									// duplicate calculation of hash(key) not
									// involved
			N++;
		st[i].put(key, val);
	}

	// return value associated with key, null if no such key
	@Override
	public Value get(Key key)
	{
		return st[hash(key)].get(key);
	}

	// delete key (and associated value) if key is in the table
	@Override
	public void delete(Key key)
	{
		int i = hash(key);
		if (st[i].contains(key)) // Note: More efficient using than
									// contains(key) as
									// duplicate calculation of hash(key) not
									// involved
			N--;
		st[i].delete(key);

		if (M > INIT_CAPACITY && N <= 2 * M)
			resize(M / 2);
	}

	// return keys in symbol table as an Iterable
	@Override
	public Iterable<Key> keys()
	{
		LinkedList<Key> keys = new LinkedList<Key>();
		for (int i = 0; i < M; i++)
			keys.addAll((Collection<? extends Key>) st[i].keys());
		return keys;
	}

	private void resize(int numChains)
	{
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(
				numChains);
		for (int i = 0; i < M; i++)
		{
			for (Key key : st[i].keys())
			{
				temp.put(key, st[i].get(key));
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.st = temp.st;
	}

	/***********************************************************************
	 * Unit test client.
	 ***********************************************************************/
	public static void main(String[] args)
	{
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
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
