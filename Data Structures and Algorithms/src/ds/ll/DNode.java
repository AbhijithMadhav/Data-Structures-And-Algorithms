package ds.ll;
/**
 * @author kempa
 * 
 */
public class DNode<Item>
{
	Item item;
	DNode<Item> prev;
	DNode<Item> next;

	
	public DNode(Item item, DNode<Item> next, DNode<Item> previous)
	{
		this.item = item;
		this.next = next;
		this.prev = previous;
	}
}