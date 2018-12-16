import java.util.Arrays;

/**
 * This class provides provides the generic implementations of:
 * merge sort, quick sort, insertion sort and selection sort.
 * 
 * merge sort -> stable sort, guaranteed to be O(nlogn)
 * quick sort -> unstable sort, best and average cases O(nlogn), worst case O(n^2)
 * insertion sort -> stable sort, best case O(n), worst case O(n^2)
 * selection sort -> unstable sort, guaranteed to be O(n^2)
 * 
 * @author atara
 */
public class Sorting {

	public static <E extends Comparable<E>> void swap(E[] array, int a, int b) {
		E temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static <E extends Comparable<E>> void mergeSort(E[] array) {
		int n = array.length;
		if (n < 2) {
			return;
		}
		//divide
		int middleIndex = n / 2;
		E[] a = Arrays.copyOfRange(array, 0, middleIndex);
		E[] b = Arrays.copyOfRange(array, middleIndex, n);
		//conquer
		mergeSort(a);
		mergeSort(b);
		//merge
		merge(array, a, b);
	}

	private static <E extends Comparable<E>> void merge(E[] array, E[] a, E[] b) {
		int indexA = 0;
		int indexB = 0;
		
		while (indexA < a.length && indexB < b.length) {
			if (a[indexA].compareTo(b[indexB]) < 0) {
				array[indexA + indexB] = a[indexA++];
			}
			else {
				array[indexA + indexB] = b[indexB++];
			}
		}
		while (indexA < a.length) {
			array[indexA + indexB] = a[indexA++];
		}
		while (indexB < b.length) {
			array[indexA + indexB] = b[indexB++];
		}
	}

	public static <E extends Comparable<E>> void quickSort(E[] array) {
		quickSort(array, 0, array.length - 1);
	}

	private static <E extends Comparable<E>> void quickSort(E[] array, int left, int right) {
		//pick pivot
		int pivotIndex = (left + right) / 2;
		//swap pivot 2 end
		swap(array, pivotIndex, right);
		//partition 
		int x = partition(array, left, right - 1, array[right]);
		//swap partition value with pivot
		swap(array, x, right);
		//repeat with recursion
		if (x - left > 1) {
			quickSort(array, left, x - 1);
		}
		if (right - x > 1) {
			quickSort(array, x + 1, right);
		}
	}

	private static <E extends Comparable<E>> int partition(E[] array, int left, int right, E pivot) {
		while (left <= right) {
			while (array[left].compareTo(pivot) < 0) {
				left++;
			}
			while (right >= left && array[right].compareTo(pivot) >= 0) {
				right--;
			}
			if (right > left) {
				swap(array, left, right);
			}
		}
		return left;
	}

	public static <E extends Comparable<E>> void iterativeInsertionSort(E[] array) {
		//if you find a bigger value swap
		for (int i = 0; i < array.length; i++) {
			for (int j = i; (j > 0) && (array[j].compareTo(array[j-1]) < 0); j--) {
				swap(array, j, j-1);
			}
		}
	}

	public static <E extends Comparable<E>> void recursiveInsertionSort(E[] array) {
		recIS(array, 0);
	}

	private static <E extends Comparable<E>> void recIS(E[] array, int index) {
		if (index == array.length) {
			return;
		}
		for (int i = index; i > 0; i--) {
			if (array[i].compareTo(array[i - 1]) < 0) {
				swap(array, i, i-1);
				recIS(array, i-1);
			}
		}
		recIS(array, index + 1);
	}

	public static <E extends Comparable<E>> void iterativeSelectionSort(E[] array) {
		for (int i = 0; i < array.length; i++) {
			int big = 0;
			for (int j = 1; j < array.length - i; j++) {
				if (array[j].compareTo(array[big]) > 0) {
					big = j;
				}
			}
			swap (array, big, array.length - i - 1);
		}
	}

	public static <E extends Comparable<E>> void recursiveSelectionSort(E[] array) {
		recSS(array, array.length - 1);
	}
	
	private static <E extends Comparable<E>> void recSS(E[] array, int lastAvailablePosition) {
		if (lastAvailablePosition == 0) {
			return; 
		}
		int big = 0;
		for (int i = 1; i <= lastAvailablePosition; i++) {
			if (array[big].compareTo(array[i]) < 0) {
				big = i;
			}
		}
		swap(array, big, lastAvailablePosition);
		recSS(array, lastAvailablePosition - 1);
	}
}
