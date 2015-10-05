package t1005;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 * 题目描述：http://acm.acmcoder.com/showproblem.php?pid=1005
 */
public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = 0, b = 0, n = 0;
        int[] fn = new int[50];

        while (scanner.hasNext()){
            a = scanner.nextInt();
            b = scanner.nextInt();
            n = scanner.nextInt();
            if (a == 0 && b == 0 && n == 0){
                return;
            }

            for (int i = 1; i < 50; i++){
                if (i == 1 || i == 2){
                    fn[i] = 1;
                }
                else {
                    fn[i] = (a * fn[i-1] + b * fn[i-2]) % 7;
                }
            }

            System.out.println(fn[n % 49]);
        }
    }
}
