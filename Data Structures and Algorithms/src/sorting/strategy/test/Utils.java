package sorting.strategy.test;

import java.util.Random;

public class Utils {

	public static Integer[] getRandomArray(int size) {
		Random r = new Random();
		Integer a[] = new Integer[size];
		for(int i = 0; i < size; i++)
			a[i] = Math.abs(r.nextInt());
		return a;
	}
}
