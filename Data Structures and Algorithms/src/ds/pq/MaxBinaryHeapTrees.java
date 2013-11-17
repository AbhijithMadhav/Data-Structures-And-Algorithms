package ds.pq;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxBinaryHeapTrees<Key extends Comparable<Key>>
{
	HeapNode<Key> root;
	HeapNode<Key> pNextInsertPos; // Parent of next insert position
	HeapNode<Key> nextDelHeapNode; // Next delete node

	public MaxBinaryHeapTrees()
	{
		root = null;
		pNextInsertPos = null;
		nextDelHeapNode = null;
	}

	public void insert(Key key)
	{
		// Empty heap
		if (root == null)
		{
			root = new HeapNode<Key>(key, null); // 1. Insert node
			nextDelHeapNode = root; // 2. Set next delete node
			pNextInsertPos = root; // 3. Set parent of next insert position
			return;
		}

		// 1. Insert at the end and swim up
		HeapNode<Key> nd = new HeapNode<Key>(key, pNextInsertPos);
		if (pNextInsertPos.left == null)
			pNextInsertPos.left = nd;
		else
			pNextInsertPos.right = nd;
		swim(nd); // only swaps value.

		// 2. set nextDelHeapNode
		nextDelHeapNode = nd;

		// 3. set pNextInsertPos

		if (pNextInsertPos.left == nd)
			return; // pNextInsertPos is where it should be

		// Traverse up until some ancestor is the left child of its parent
		while (pNextInsertPos.parent != null
				&& pNextInsertPos == pNextInsertPos.parent.right)
			pNextInsertPos = pNextInsertPos.parent;
		pNextInsertPos = (pNextInsertPos.parent == null) ? root
				: pNextInsertPos.parent.right;

		while (pNextInsertPos.left != null)
			pNextInsertPos = pNextInsertPos.left;
	}

	public Key delMax()
	{
		if (isEmpty())
			throw new RuntimeException("Priority Queue Underflow");

		Key key = root.key; // save key
		root.key = nextDelHeapNode.key; // quasi swap with last node
		HeapNode<Key> nd = nextDelHeapNode;

		// Special case : Delete lone node
		if (nextDelHeapNode.parent == null)
		{
			pNextInsertPos = null; // 1. Set parent of next insert position
			nextDelHeapNode = null; // 2. Set next delete position
			root = null; // 3. Delete node
			return key;
		}

		// 1. Set parent of next insert position
		pNextInsertPos = nextDelHeapNode.parent;

		// 2. Set next delete position
		// Traverse up until an ancestor node is the right child of its parent
		while (nextDelHeapNode.parent != null
				&& nextDelHeapNode == nextDelHeapNode.parent.left)
			nextDelHeapNode = nextDelHeapNode.parent;
		nextDelHeapNode = (nextDelHeapNode.parent == null) ? root : nextDelHeapNode.left;
		while (nextDelHeapNode.right != null)
			nextDelHeapNode = nextDelHeapNode.right;

		// 3. Delete node and sink
		if (nd.parent.left == nd)
			nd.parent.left = null;
		else
			nd.parent.right = null;
		sink(root);

		return key;

		/*
		 * pNextInsertPos = nextDelHeapNode.parent;
		 * // Set nextDelHeapNode
		 * HeapNode<Key> t = nextDelHeapNode; // save
		 * nextDelHeapNode = nextDelHeapNode.parent;
		 * if (nextDelHeapNode == null)
		 * {// this was the last node in the heap
		 * root = null;
		 * pNextInsertPos = null;
		 * nextDelHeapNode = null;
		 * return key;
		 * }
		 * if (nextDelHeapNode.right == t)
		 * {
		 * nextDelHeapNode.right = null; // delete the node
		 * nextDelHeapNode = nextDelHeapNode.left;
		 * }
		 * else
		 * {
		 * HeapNode<Key> t1 = nextDelHeapNode.left; // save for null setting of parents
		 * // pointing to t
		 * while (nextDelHeapNode.left == t)
		 * {
		 * t = nextDelHeapNode;
		 * nextDelHeapNode = nextDelHeapNode.parent;
		 * if (nextDelHeapNode == null)
		 * break;
		 * }
		 * if (nextDelHeapNode == null)
		 * nextDelHeapNode = root;
		 * else
		 * {
		 * assert (nextDelHeapNode.left != t);
		 * nextDelHeapNode = nextDelHeapNode.left;
		 * }
		 * while (nextDelHeapNode.right != null)
		 * nextDelHeapNode = nextDelHeapNode.right;
		 * 
		 * t1.parent.left = null; // delete the node
		 * }
		 * sink(root);
		 * return key;
		 */
	}

	// swaps nd.key appropriately with one of its ancestors maintaining the
	// binary heap property
	private HeapNode<Key> swim(HeapNode<Key> nd)
	{
		if (nd == null)
			return null;

		while (nd.parent != null && nd.key.compareTo(nd.parent.key) > 0)
		{
			swapKeys(nd, nd.parent);
			nd = nd.parent;
		}
		return nd;
	}

	private void swapKeys(HeapNode<Key> a, HeapNode<Key> b)
	{
		Key t = a.key;
		a.key = a.key;
		a.key = t;
	}

	private void display(HeapNode<Key> nd)
	{
		if (nd == null)
			return;

		Deque<HeapNode<Key>> queue = new ArrayDeque<HeapNode<Key>>();
		queue.addFirst(nd);
		System.out.print(nd.key + " ");
		while (!queue.isEmpty())
		{
			nd = queue.removeLast();
			if (nd.left != null)
			{
				System.out.print(nd.left.key + " ");
				queue.add(nd.left);
			}
			if (nd.right != null)
			{
				System.out.print(nd.right.key + " ");
				queue.add(nd.right);
			}
		}
/*
	
		if (nd == null)
			return;
		System.out.print(nd.key + " ");
		display(nd.left);
		display(nd.right);
		*/
	}

	private void display()
	{
		display(root);
		System.out.println();
	}

	public boolean isEmpty()
	{
		return root == null;
	}

	private HeapNode<Key> sink(HeapNode<Key> nd)
	{
		if (nd == null)
			return nd;

		while (nd.left != null && nd.right != null) // go until the last level
		{
			HeapNode<Key> max = nd.right;
			if (nd.left.key.compareTo(nd.right.key) > 0)
				max = nd.left;

			if (max.key.compareTo(nd.key) > 0)
			{
				swapKeys(max, nd);
				nd = max;
			}
			else
				break;
		}

		// The last level
		if (nd.left == null && nd.right != null)
			if (nd.right.key.compareTo(nd.key) > 0)
			{
				swapKeys(nd.right, nd);
				nd = nd.right;
			}
		if (nd.right == null && nd.left != null)
			if (nd.left.key.compareTo(nd.key) > 0)
			{
				swapKeys(nd.left, nd);
				nd = nd.left;
			}

		return nd;
	}

	public static void main(String s[])
	{
		MaxBinaryHeapTrees<Integer> pq = new MaxBinaryHeapTrees<Integer>();
		pq.insert(2);
		pq.display();
		pq.insert(5);
		pq.display();
		pq.insert(10);
		pq.display();
		pq.insert(11);
		pq.display();
		pq.insert(9);
		pq.display();
		pq.insert(8);
		pq.display();
		pq.insert(13);
		pq.display();
		pq.insert(3);
		pq.display();
		/*pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.insert(50);
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.delMax();
		pq.display();
		pq.insert(8);
		pq.display();*/

	}
}


class HeapNode<Key extends Comparable<Key>>
{
	Key key;
	HeapNode<Key> left, right, parent;

	public HeapNode(Key key, HeapNode<Key> parent)
	{
		this.key = key;
		this.parent = parent;
		left = null;
		right = null;
	}
}