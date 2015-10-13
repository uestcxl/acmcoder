package t1091;

import java.util.Scanner;

/**
 * Created by xl on 15/10/13.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int m, n;
        while (scanner.hasNext()){
            m = scanner.nextInt();
            n = scanner.nextInt();
            if (m == 0 && n == 0)
                System.exit(0);
            System.out.println(m + n);
        }
    }
}
