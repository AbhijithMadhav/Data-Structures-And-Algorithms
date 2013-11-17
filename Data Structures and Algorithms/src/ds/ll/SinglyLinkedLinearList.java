package ds.ll;
/**
 * @author kempa
 * 
 *         Efficient operations that can be performed using a singly linked
 *         linear list
 *         The following is not an ADT but just an illustration of the efficient
 *         operations. A client or an ADT implementation which uses this type of
 *         list will have to include checks for boundary conditions like empty
 *         lists.
 * 
 */
public class SinglyLinkedLinearList<Item extends Comparable<Item>>
{

	public Node<Item> first;

	public SinglyLinkedLinearList()
	{
		first = null;
	}

	public boolean isEmpty()
	{
		return first == null;
	}
	
	public boolean isUnity()
	{
		if (!isEmpty())
			return first.next == null;
		return false;
	}

	public void insertBeginning(Item item)
	{
		Node<Item> nd = new Node<Item>(item, first);
		first = nd;
	}

	// Client must check if the list is empty before performing the operation
	public Item removeBeginning()
	{
		Item t = first.item;
		first = first.next;
		return t;
	}

	public void insertAfter(Node<Item> nd, Item item)
	{
		Node<Item> newNd = new Node<Item>(item, nd.next);
		nd.next = newNd;
	}

	public Item removeAfter(Node<Item> nd)
	{
		if (nd.next == null) // No nodes after the last node
			return null;

		Item item = nd.next.item;
		nd.next = nd.next.next;
		return item;
	}
}
