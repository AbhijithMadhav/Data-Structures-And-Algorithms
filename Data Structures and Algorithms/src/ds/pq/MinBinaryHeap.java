package ds.pq;
/**
 * @author kempa
 * 
 * @param <Key>
 *            is the type of the elements in the heap. It extends the Comparable
 *            class
 */
@SuppressWarnings("unchecked")
public class MinBinaryHeap<Key extends Comparable<Key>>
{

	private Key[] heap; // heap-ordered complete binary tree in heap[1..N] with
	private int N; // heap[0] unused

	/**
	 * Constructs an empty MIn-heap with the specified intial capacity
	 * 
	 * @param capacity
	 *            Initial capacity of the Max-heap
	 */
	public MinBinaryHeap(int capacity)
	{
		heap = (Key[]) new Comparable[capacity + 1];
		N = 0;
	}

	/**
	 * Constructs a Min-heap initialised with the specified array
	 * 
	 * @param a
	 *            array whose elements are to be used to initialise Min-heap
	 */
	public MinBinaryHeap(Key a[])
	{
		heap = (Key[]) new Comparable[a.length + 1];
		System.arraycopy(a, 0, heap, 1, a.length);

		for (int i = heap.length / 2; i >= 1; i--)
			sink(i);

		N = heap.length - 1;
	}

	/**
	 * Constructs a Min-heap initialised with the elements of the iterable
	 * object
	 * 
	 * @param a
	 *            Iterable object whose elements are to be used to initialise
	 *            Min-heap
	 */
	public MinBinaryHeap(Iterable<Key> a)
	{
		heap = (Key[]) new Comparable[4];

		int j = 1;
		for (Key k : a)
		{
			if (j == heap.length)
				heap = resize(heap, heap.length * 2);
			heap[j++] = k;
		}
		N = j - 1;

		for (int i = j / 2; i >= 1; i--)
			sink(i);
	}

	private boolean greater(Key i, Key j)
	{
		if (i.compareTo(j) > 0)
			return true;
		return false;
	}

	/**
	 * Tests if Min-heap is empty
	 * 
	 * @return Test result
	 */
	public boolean isEmpty()
	{
		return N == 0;
	}

	/**
	 * Returns size of Min-heap
	 * 
	 * @return Size of Min-heap
	 */
	public int size()
	{
		return N;
	}

	/**
	 * Insert a key into Min-heap in logarithmic time
	 * 
	 * @param v
	 *            Key to be inserted
	 */
	public void insert(Key v)
	{
		// Insert
		heap[++N] = v;
		swim(N);

		// resizing
		if (N >= heap.length - 1)
			heap = resize(heap, 2 * heap.length);
	}

	/**
	 * Resizes the specified array with the specified size
	 * 
	 * @param a
	 *            Array to be resized
	 * @param n
	 *            New size
	 * @return Resized array
	 */
	Key[] resize(Key[] a, int n)
	{
		Key[] b = (Key[]) new Comparable[n];
		System.arraycopy(a, 0, b, 0, (a.length < b.length) ? a.length
				: b.length);
		return b;
	}

	/**
	 * heap[i] swims to its rightful place.
	 * 
	 * @param i
	 *            Index of element which has to swim
	 * @return Index of key after the swim
	 */
	private int swim(int i)
	{
		Key t = heap[i]; // Save key for later restoring
		while (i > 1 && greater(heap[i / 2], heap[i]))
		{
			// exch(heap, i / 2, i);
			heap[i] = heap[i / 2]; // half exchanges
			i = i / 2;
		}
		heap[i] = t; // restore key
		return i;
	}

	/**
	 * Delete the min key in logarithmic time
	 * 
	 * @return The min key which was deleted
	 */
	public Key delMin()
	{
		if (N == 0)
			throw new RuntimeException("Priority Queue Underflow");

		Key min = heap[1];

		heap[1] = heap[N]; // half exchange
		heap[N--] = null;
		sink(1);

		// resize
		if (N == heap.length / 4)
			heap = resize(heap, heap.length / 2);

		return min;
	}

	/**
	 * Returns the min key in constant time
	 * 
	 * @return The max key
	 */
	public Key max()
	{
		if (N == 0)
			throw new RuntimeException("Priority Queue Underflow");
		return heap[1];

	}

	/**
	 * heap[i] sinks to its rightful place
	 * 
	 * @param i
	 *            Index of the element which should sink
	 * @return New Index of the element which has sunk
	 */
	private int sink(int i)
	{
		Key t = heap[i]; // Save key for later restoring
		while (2 * i + 1 <= N) // while heap[i] has both the children
		{
			int j = 2 * i + 1; // rightmost child

			// 1st comparision: Making j point to the least of the two
			// children as heap[i] should sink to j
			if (greater(heap[j], heap[j - 1]))
				j--;

			// 2nd comparsion. Stop sinking if heap[i] is less than its
			// least child
			if (greater(heap[j], t))
				break;

			// exch(heap, i, j);
			heap[i] = heap[j]; // half exchanges
			i = j; // Prepare 'i' for the next iteration
		}

		// if heap[i] had only one child, the comparision for sink hasn't been
		// done yet
		if (2 * i == N)
			if (greater(heap[i], heap[N]))
			{
				heap[i] = heap[N];
				i = N;
			}

		heap[i] = t; // restore
		return i;
	}

	/**
	 * Deletes the item in node i from the heap
	 * 
	 * @param i
	 * @return
	 */
	public Key delete(int i)
	{
		if (i > heap.length - 1 || i < 1)
			return null;
		Key key = heap[i];
		heap[i] = heap[N--];
		if (greater(heap[i], key))
			sink(i);
		else
			swim(i);
		return key;
	}
}