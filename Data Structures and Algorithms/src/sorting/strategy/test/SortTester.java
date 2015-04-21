package sorting.strategy.test;

import sorting.SortableArray;
import sorting.strategy.clients.BubbleSort;
import sorting.strategy.clients.InsertionSort;
import sorting.strategy.clients.MergeSort;
import sorting.strategy.clients.MergeSortBU;
import sorting.strategy.clients.MergeSortOptimized;
import sorting.strategy.clients.QuickSortOptimized;
import edu.princeton.cs.introcs.StdRandom;

public class SortTester {
	
	public static void main(String s[])
	{
		Integer a[] = { 1, 2, 5, 90, 43, -9, 78 };
		
		SortableArray<Integer> testSorter = new MergeSort<>(a);
		testSorter.sort();
		testSorter.show();
		
		StdRandom.shuffle(a);
		testSorter = new MergeSortBU<>(a);
		testSorter.sort();
		testSorter.show();
		
		StdRandom.shuffle(a);
		testSorter = new MergeSortOptimized<>(a);
		testSorter.sort();
		testSorter.show();
		
		StdRandom.shuffle(a);
		testSorter = new BubbleSort<>(a);
		testSorter.sort();
		testSorter.show();
		
		StdRandom.shuffle(a);
		testSorter = new InsertionSort<>(a);
		testSorter.sort();
		testSorter.show();
		
		StdRandom.shuffle(a);
		testSorter = new QuickSortOptimized<>(a);
		testSorter.sort();
		testSorter.show();
		
		/*StdRandom.shuffle(a);
		testSorter = new CountingSort(a);
		testSorter.sort();
		testSorter.show();*/
	}
}
