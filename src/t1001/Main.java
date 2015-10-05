package t1001;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 */
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLong()){
            long sum = 0;
            long n = scanner.nextInt();
            sum = (1 + n) * n / 2;
            System.out.println(sum);
            System.out.print("\n");
        }
        scanner.close();
    }
}