package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.quicksort.QuickSorterOptimized;

public class QuickSortOptimized<T extends Comparable<T>> extends SortableArray<T> {
	
	public QuickSortOptimized(T[] a) {
		super(a, new QuickSorterOptimized<T>());
	}
}
