package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.mergesort.MergeSorter;

public class MergeSort<T extends Comparable<T>> extends SortableArray<T> {
	
	public MergeSort(T[] a) {
		super(a, new MergeSorter<T>());
	}
}
