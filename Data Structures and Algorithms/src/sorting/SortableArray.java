package sorting;

/**
 * Using strategy pattern
 * @author kempa
 *
 * @param <T>
 */
public abstract class SortableArray<T extends Comparable<T>> {
	
	private T a[];
	
	// The sort algorithm is what changes
	private Sorter<T> sorter;
	
	public SortableArray(T a[], Sorter<T> sorter) {
		this.a = a;
		this.sorter = sorter;
	}
	
	public void sort() {
		sorter.sort(a);
	}
	
	public void sort(int lo, int hi) {
		sorter.sort(a, lo, hi);
	}
	
	public void setSorter(Sorter<T> sorter) {
		this.sorter = sorter;
	}
	
	public void show() {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
}
