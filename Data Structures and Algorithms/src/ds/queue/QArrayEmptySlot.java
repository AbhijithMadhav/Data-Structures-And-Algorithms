package ds.queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A dynamic array implementation of the ADT Queue
 * This implementation uses an empty slot to help distinguish an empty Q from
 * a full one.
 * 
 * @author kempa
 * 
 * @param <Item>
 *            The type of the Q
 */
public class QArrayEmptySlot<Item> implements Iterable<Item> {

	// The circular buffer.
	// N is the maximum number of elements that can be stored in the queue
	// before resizing is needed.
	private Item q[];
	private int N;

	// 'front' indexes the first element of the queue(when the Q is not empty)
	// and is used to implement the dequeue operation.
	// 'rear' indexes the next empty slot where an element can be inserted(when
	// the Q is not full)
	private int front;
	private int rear;

	@SuppressWarnings("unchecked")
	public QArrayEmptySlot() {
		N = 2;
		q = (Item[]) new Object[N + 1]; // extra empty slot
		front = 0;
	}
	
	@SuppressWarnings("unchecked")
	public QArrayEmptySlot(int N) {
		q = (Item[]) new Object[N + 1]; // extra empty slot
		this.N = N;
		front = 0;
	}

	public boolean isEmpty() {
		return front == rear;
	}

	public boolean isFull() {
		return (rear + 1) % (N + 1) == front;
	}

	public int size() {
		if (front <= rear)
			return rear - front;
		else
			return (N - front + 1) + rear;
	}
	
	@SuppressWarnings("unchecked")
	private Item[] resize(int newCapacity) {
		if (newCapacity > N)
			System.out.println("Increasing capacity from " + N + " to " + newCapacity);
		else
			System.out.println("Decreasing capacity from " + N + " to " + newCapacity);
		Item[] t = (Item[]) new Object[newCapacity + 1];
		for (int i = 0, j = front; j != rear; i++, j = (j + 1) % (N + 1))
			t[i] = q[j];

		N = newCapacity;
		
		// size() makes use of front and rear. So set rear = size() before resetting front
		rear = size();
		front = 0;

		System.out.println("Resize " + " -> front: " + front + " rear: " + rear);
		return t;
	}

	public void enqueue(Item item) {
		if (isFull())
			q = resize(N * 2);
		q[rear] = item;
		rear = (rear + 1) % (N + 1);
		
		System.out.println("enqueue " + item + " -> front: " + front + " rear: " + rear);
		System.out.println(toString());
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		// Remove from the front.
		Item t = q[front];
		front = (front + 1) % (N + 1);

		if (size() < N / 2)
			q = resize(N / 2);

		System.out.println("dequeue -> front: " + front + " rear: " + rear);
		System.out.println(toString());
		return t;
	}

	public String toString() {
		String s = "size = " + size() + " : ";		
		for (Item i : this)
			s += i + " ";
		return s;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		QArrayEmptySlot<Integer> q = new QArrayEmptySlot<Integer>();

		for (int i = 1; i <= 444; i++)
			q.enqueue(i);
		
		for (int i = 1; i <= 4; i++)
			q.enqueue(i);

		for (int i = 1; i <= 13; i++)
			q.enqueue(i);
		
		for (int i = 1; i <= 100; i++)
			q.enqueue(i);
	}

	@Override
	public Iterator<Item> iterator() {
		return new QArrayIterator();
	}

	private class QArrayIterator implements Iterator<Item> {

		private int i = front;

		@Override
		public boolean hasNext() {
			return ( i != rear);
		}

		@Override
		public Item next() {
			Item t = q[i];
			i = (i + 1)% (N + 1);
			return t;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}