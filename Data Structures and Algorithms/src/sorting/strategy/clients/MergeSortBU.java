package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.mergesort.MergeSorterBU;

public class MergeSortBU<T extends Comparable<T>> extends SortableArray<T> {
	
	public MergeSortBU(T[] a) {
		super(a, new MergeSorterBU<T>());
	}
}
