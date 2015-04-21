package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.insertionsort.NaiveInsertionSorter;

public class NaiveInsertionSort<T extends Comparable<T>> extends SortableArray<T>{

	public NaiveInsertionSort(T a[]) {
		super(a, new NaiveInsertionSorter<T>());
	}
}
