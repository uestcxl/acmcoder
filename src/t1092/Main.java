package t1092;

import java.util.Scanner;

/**
 * Created by xl on 15/10/14.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (scanner.hasNext()){
            int sum = 0;
            n = scanner.nextInt();
            if (n == 0)
                System.exit(0);
            for (int i = 0; i < n; i++)
                sum += scanner.nextInt();
            System.out.println(sum);
        }
    }
}
