package QuickSort;

import java.lang.Comparable;

public class Quick {
    private static int comparisonCount = 0; // 비교 횟수를 저장할 변수

    public static int sortWithCount(Comparable[] a) {
        comparisonCount = 0; // 비교 횟수 초기화
        sort(a, 0, a.length - 1);
        return comparisonCount;
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low) return;
        int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private static int partition(Comparable[] a, int pivot, int high) {
        int i = pivot + 1;
        int j = high;
        Comparable p = a[pivot];
        while (true) {
            while (i < high && isless(a[i], p)) {
                i++;
                comparisonCount++; // 비교 횟수 업데이트
            }
            while (j > pivot && isless(p, a[j])) {
                j--;
                comparisonCount++; // 비교 횟수 업데이트
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, pivot, j);
        return j;
    }

    private static boolean isless(Comparable u, Comparable v) {
        return (u.compareTo(v) < 0);
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
