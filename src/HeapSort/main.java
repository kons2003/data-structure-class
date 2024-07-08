package HeapSort;

public class main {
    public static void main(String[] args) {
        Comparable[] a = new Comparable[] {null, 90, 60, 80, 50, 30, 40, 70, 10, 20};
        System.out.println("원본 배열: ");
        for (int i = 1; i < a.length; i++) {
            System.out.printf(a[i] + "  ");
        }

        Heap2.sort(a);
        
        System.out.println("\n\n정렬 중간 결과: ");
        for (int i = 1; i < a.length; i++) {
            System.out.printf(a[i] + "  ");
            if (i == 1 || i == 3 || i == 7) {
                System.out.println(); // 중간 결과 출력 후 줄 바꿈
            }
        }

        System.out.println("\n\n정렬 결과: ");
        for (int i = 1; i < a.length; i++) {
            System.out.printf(a[i] + "  ");
        }
    }
}
