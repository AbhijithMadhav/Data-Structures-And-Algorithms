package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.mergesort.MergeSorterOptimized;

public class MergeSortOptimized<T extends Comparable<T>> extends SortableArray<T> {
	
	public MergeSortOptimized(T[] a) {
		super(a, new MergeSorterOptimized<T>());
	}
}
