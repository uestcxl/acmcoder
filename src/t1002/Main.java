package t1002;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n;
        BigInteger a, b;
        BigInteger result;
        n = scanner.nextInt();
        for (int i = 1; i <= n; i++){
            a = scanner.nextBigInteger();
            b = scanner.nextBigInteger();
            result = a.add(b);
            System.out.println("Case " + i + ":");
            System.out.println(a + " + " + b + " = " + result);
            if(i < n) System.out.println();
        }
        scanner.close();
    }
}
