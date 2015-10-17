package t1096;

import java.util.Scanner;

/**
 * Created by xl on 15/10/17.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while ( n-- > 0){
            int m = scanner.nextInt();
            int sum = 0;
            while ( m-- > 0)
                sum += scanner.nextInt();
            System.out.println(sum);
            if ( n > 0)
                System.out.println();
        }
        scanner.close();
    }
}
