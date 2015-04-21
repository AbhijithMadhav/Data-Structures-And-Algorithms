package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.BubbleSorter;

public class BubbleSort<T extends Comparable<T>> extends SortableArray<T> {
	
	public BubbleSort(T[] a) {
		super(a, new BubbleSorter<T>());
	}
}
