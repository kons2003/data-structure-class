package Btrees;

public class ArrayUtils {
    public static <T> void insertAt(T[] a, int index, T value) {
        for (int i = a.length - 1; i > index; --i)
            a[i] = a[i - 1];
        a[index] = value;
    }

    public static <T> void removeAt(T[] a, int index) {
        for (int i = index; i < a.length - 1; ++i)
            a[i] = a[i + 1];
    }

    public static void insertAt(int[] a, int index, int value) {
        for (int i = a.length - 1; i > index; --i)
            a[i] = a[i - 1];
        a[index] = value;
    }

    public static void removeAt(int[] a, int index) {
        for (int i = index; i < a.length - 1; ++i)
            a[i] = a[i + 1];
    }
}