package ds.st;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SequentialSearchST<Key, Value> implements Iterable<Key>, ST<Key, Value>
{
	private int N; // number of key-value pairs
	private Node first; // first node to the linked list
	public long cmp = 0;

	// a helper linked list data type
	private class Node
	{ // linked list node
		Key key;
		Value val;
		Node next;

		public Node(Key key, Value val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	// return number of key-value pairs
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

	// does this symbol table contain the given key?
	@Override
	public boolean contains(Key key)
	{
		return get(key) != null;
	}

	// return the value associated with the key, or null if the key is not
	// present
	@Override
	public Value get(Key key)
	{
		for (Node x = first; x != null; x = x.next)
		{
			// cmp++;
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	// add a key-value pair, replacing old key-value pair if key is already
	// present
	@Override
	public void put(Key key, Value val)
	{
		if (val == null)
		{
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next)
		{
			// cmp++;
			if (key.equals(x.key)) // Search hit: update val
			{
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first); // Search miss: add new node
		N++;
	}

	// delete key in linked list beginning at Node x
	@Override
	public void delete(Key key)
	{
		if (isEmpty())
			return;

		for (Node x = first, p = null; x != null; p = x, x = x.next)
			if (key.equals(x.key))
			{
				N--;
				if (x == first)
				{
					x = x.next;
					first = x;
				}
				else
					p.next = x.next;
				return;
			}
	}

	// return all keys as an Iterable
	@Override
	public Iterable<Key> keys()
	{
		LinkedList<Key> i = new LinkedList<Key>();
		for (Node x = first; x != null; x = x.next)
			i.addFirst(x.key);
		return i;
	}

	@Override
	public Iterator<Key> iterator()
	{
		return new SequentialSearchSTIterator();
	}

	private class SequentialSearchSTIterator implements Iterator<Key>
	{
		private Node current = first;

		@Override
		public boolean hasNext()
		{
			return (current != null);
		}

		@Override
		public Key next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			Key key = current.key;
			current = current.next;
			return key;
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	/***********************************************************************
	 * Unit test client.
	 ***********************************************************************/
	public static void main(String[] args)
	{
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
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