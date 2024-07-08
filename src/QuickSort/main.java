package QuickSort;

public class main {
	public static void main(String[] args) {
		Integer[] ascendingArray = {1, 2, 3, 4, 5, 6};
		Integer[] descendingArray = {6, 5, 4, 3, 2, 1};
		Integer[] descendingArray2 = {4, 6, 5, 3, 2, 1};

		int ascendingCount = testQuickSort(ascendingArray);
		int descendingCount = testQuickSort(descendingArray);
		int descendingCount2 = testQuickSort(descendingArray2);

		System.out.println("정렬  : 1  2  3  4  5  6 , count = " + ascendingCount);
		System.out.println("정렬  : 6  5  4  3  2  1 , count = " + descendingCount);
		System.out.println("정렬  : 4  6  5  3  2  1 , count = " + descendingCount2);
	}

	public static int testQuickSort(Integer[] a) {
		// 배열을 복제하여 원본 배열이 변경되지 않도록 함
		Integer[] copy = a.clone();
		return Quick.sortWithCount(copy);
	}
}
