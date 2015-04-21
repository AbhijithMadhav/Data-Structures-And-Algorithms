package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.SelectionSorter;

public class SelectionSort<T extends Comparable<T>> extends SortableArray<T> {
	
	public SelectionSort(T[] a) {
		super(a, new SelectionSorter<T>());
	}
}
