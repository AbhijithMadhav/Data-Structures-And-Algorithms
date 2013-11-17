package ds.ll;
/**
 * @author kempa
 * 
 *         Efficient operations of a dummy headed doubly linked circular list
 *         The following is not an ADT but just an illustration of the efficient
 *         operations. A client or an ADT implementation which uses this type of
 *         list will have to include checks for boundary conditions like empty
 *         lists.
 */
public class DummyHeadedDoublyLinkedCircularList<Item>
{
	private DNode<Item> head;

	public DummyHeadedDoublyLinkedCircularList()
	{
		head = new DNode<Item>(null, null, null);
		head.next = head;
		head.prev = head;
	}

	public boolean isEmpty()
	{
		return head.next == head;
	}

	public void insertAfter(DNode<Item> nd, Item item)
	{
		DNode<Item> newNd = new DNode<Item>(item, nd, nd.next);
		nd.next = newNd;
		newNd.next.prev = newNd;
	}

	public void insertBeginning(Item item)
	{
		insertAfter(head, item);
	}

	public void insertBefore(DNode<Item> nd, Item item)
	{
		insertAfter(nd.prev, item);
	}

	public void insertEnd(Item item)
	{
		insertBefore(head, item);
	}

	// nd must not be null
	public Item remove(DNode<Item> nd)
	{
		nd.prev.next = nd.next;
		nd.next.prev = nd.prev;
		return nd.item;
	}

	// list must not be empty
	public Item removeBeginning()
	{
		return remove(head.next);
	}

	// list must not be empty
	public Item removeEnd()
	{
		return remove(head.prev);
	}

	public Item removeAfter(DNode<Item> nd)
	{
		if (nd.next == head)
			return null;
		return remove(nd.next);
	}

	public Item removeBefore(DNode<Item> nd)
	{
		if (nd.prev == head)
			return null;
		return remove(nd.prev);
	}
}
