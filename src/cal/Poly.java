// Poly.java
package cal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Poly {

    public static void main(String[] args) throws Exception {

        System.out.println("배열--------다항식 A를 입력하겠습니다. --------");
        Polynomial A = new Polynomial();

        // A식 입력
        while (true)

        {
            System.out.print("계수 지수 :");
            // 두가지를 한번에 입력받는 처리 부분
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int coef = i;
            int expo = j;

            A.insertArray(coef, expo);
            if (expo == 0) {
                break;
            }
        }

        System.out.print("->A결과: ");
        A.print();

        System.out.println("--------다항식 B를 입력하겠습니다. --------");
        Polynomial B = new Polynomial();

        // B식 입력
        while (true)

        {
            System.out.print("계수 지수 :");
            // 두가지를 한번에 입력받는 처리 부분
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            int coef = i;
            int expo = j;

            B.insertArray(coef, expo);
            if (expo == 0) {
                break;
            }

        }

        System.out.print("->B결과: ");
        B.print();

        Polynomial C = new Polynomial();

        int startA = 0;
        int finishA = A.getSize() - 1;
        int startB = 0;
        int finishB = B.getSize() - 1;

        while (startA <= finishA && startB <= finishB) {

            // A의 지수가 B의 지수보다 큰 경우
            if (A.getExpo(startA) > B.getExpo(startB)) {
                C.insertArray(A.getCoef(startA), A.getExpo(startA));
                startA++;
            }
            // B의 지수가 A의 지수보다 큰 경우
            else if (A.getExpo(startA) < B.getExpo(startB)) {
                C.insertArray(B.getCoef(startB), B.getExpo(startB));
                startB++;
            }
            // A의 지수와 B의 지수가 같은 경우
            else {
                C.insertArray(A.getCoef(startA) + B.getCoef(startB), A.getExpo(startA));
                startA++;
                startB++;
            }
        }

        // A에 항이 남아 있는 경우 C에 추가
        for (; startA <= finishA; startA++)
            C.insertArray(A.getCoef(startA), B.getExpo(startA));

        // B에 항이 남아 잇는 경우 B에 추가
        for (; startB <= finishB; startB++)
            C.insertArray(B.getCoef(startB), B.getExpo(startB));

        //마지막 지수 계산
        while (A.lastCoef != 0 || B.lastCoef != 0) {

            C.lastCoef = A.lastCoef + B.lastCoef;
            break;

        }
        System.out.print("->A+B결과: ");
        C.print();

    }
}
