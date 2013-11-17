package ds.queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A dynamic array implementation of the ADT Queue. This implementation keeps
 * track of the number of entries in the queue to manage the circular buffer 
 * 
 * @author kempa
 * 
 * @param <Item>
 *            The type of the Q
 */
public class QArrayCount<Item> implements Iterable<Item> {

	// The circular buffer of capacity N.
	// N is the maximum number of elements that can be stored in the queue
	// before resizing is needed.
	private Item q[];
	private int N = 2;

	// 'front' indexes the first element of the queue(when the Q is not empty)
	// and is used to implement the dequeue operation.
	private int front;

	// The number of items in the Q
	private int nItems;

	@SuppressWarnings("unchecked")
	public QArrayCount() {
		q = (Item[]) new Object[N];
		front = 0;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean isFull() {
		return size() == N;
	}

	public int size() {
		return nItems;
	}

	@SuppressWarnings("unchecked")
	private Item[] resize(int newCapacity) {
		Item[] t = (Item[]) new Object[newCapacity];
		for (int i = 0, j = front; i < size(); i++, j = (j + 1) % N)
			t[i] = q[j];

		N = newCapacity;
		front = 0;

		return t;
	}

	public void enqueue(Item item) {
		if (isFull())
			q = resize(N * 2);
		q[(front + size()) % N] = item;
		nItems++;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		if (size() < N / 2)
			q = resize(N / 2);

		// Remove from the front.
		Item t = q[front];
		front = (front + 1) % N;
		nItems--;
		return t;
	}

	public String toString() {
		String s = null;
		for (int i = front, count = 0; count != size(); i = (i + 1) % N)
			s = q[i] + " ";
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		QArrayCount<Integer> q = new QArrayCount<Integer>();

		for (int i = 1; i <= 567; i++)
			q.enqueue(i);

		for (int i = 1; i <= 89; i++)
			q.dequeue();

		for (int i = 8; i <= 9; i++)
			q.enqueue(i);

		for (int i = 1; i <= 43; i++)
			q.dequeue();

		for (Integer i : q)
			System.out.println(i);

		System.out.println("Size: " + q.size());
	}

	@Override
	public Iterator<Item> iterator() {
		return new QArrayIterator();
	}

	private class QArrayIterator implements Iterator<Item> {

		private int i = front;
		private int n = size();

		@Override
		public boolean hasNext() {
			return (n != 0);
		}

		@Override
		public Item next() {
			Item t = q[i];
			i = (i + 1)% N;
			n--;
			return t;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}