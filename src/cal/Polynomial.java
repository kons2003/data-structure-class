// Polynomial.java --------------------------------------------------------------------------------
package cal;

import java.util.ArrayList;

public class Polynomial {


    private ArrayList<Integer> coefArray = new ArrayList<Integer>();
    private ArrayList<Integer> expoAraay = new ArrayList<Integer>();
    int lastCoef = 0;

    public void insertArray(int coef, int expo) {

        if (expo == 0) {
            lastCoef = coef ;
        }else {
            coefArray.add(coef);
            expoAraay.add(expo);
        }
    }

    public int getCoef(int index) {
        return coefArray.get(index);
    }

    public int getExpo(int index) {
        return expoAraay.get(index);
    }

    public int getSize() {
        return coefArray.size();
    }

    public void print() {

        if (coefArray.size()==0) System.out.println("0");
        for (int i = 0; i < coefArray.size(); i++) {
            int coef = coefArray.get(i);
            int expo = expoAraay.get(i);

            // X^0인 경우, 자연수만 출력하기 위한 처리
            String isDigit = expo == 0 ? "0" : "X^" + expo;

            if ((coef == 0) || (expo == 0)) {
                System.out.println("0");
                break;
            }
            if ((coef == 0) && (expo != 0)) {
                continue;
            }

            /*// 마지막 덧셈을 없애기 위한 처리
            if (i == coefArray.size() - 1) {
                System.out.print(coef + isDigit);
            } else {
                System.out.print(coef + isDigit + " + ");
            }*/
            // 마지막 0 없애기

            if (i == coefArray.size() - 1) {
                if (lastCoef != 0) {
                    System.out.print(coef + isDigit + " + " + lastCoef);
                } else {
                    System.out.print(coef + isDigit);
                }
                System.out.println();
            } else {
                System.out.print(coef + isDigit + " + ");
            }

        }
        /*String isPlus = expoAraay.size() == 0? "":" + " ;

        if (lastCoef!=0) {
            System.out.print(isPlus + lastCoef);
            System.out.println();
        }*/
    }
}
