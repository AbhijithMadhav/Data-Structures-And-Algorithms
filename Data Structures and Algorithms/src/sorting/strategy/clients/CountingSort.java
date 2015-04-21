package sorting.strategy.clients;

import sorting.SortableArray;
import sorting.strategy.algorithms.CountingSorter;

public class CountingSort extends SortableArray<Integer>{

	public CountingSort(Integer a[]) {
		super(a, new CountingSorter());
	}
}
