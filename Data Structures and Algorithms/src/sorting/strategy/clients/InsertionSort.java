package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.insertionsort.InsertionSorter;

public class InsertionSort<T extends Comparable<T>> extends SortableArray<T>{

	public InsertionSort(T a[]) {
		super(a, new InsertionSorter<T>());
	}
}
