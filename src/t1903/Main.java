package t1903;

import java.util.Scanner;

/**
 * Created by xl on 15/10/10.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int i, j, sum , m = 0;
        int n = scanner.nextInt();
        for (i = 0; i < n; i++){
            m = scanner.nextInt();
            sum = 0;
            for (j = 0; j < m; j++){
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
        scanner.close();
    }
}
