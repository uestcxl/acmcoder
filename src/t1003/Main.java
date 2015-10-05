package t1003;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 */
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int length = n;
        while (n-- > 0){
            int number = scanner.nextInt();
            int[] a = new int[number];
            int[] d = new int[number];
            for (int i = 0; i < number; i++){
                a[i] = scanner.nextInt();
                d[i] = a[i];
            }
            int start = 1;
            int end = 1;
            for (int i = 1; i < number; i++){
                d[i] = Math.max(d[i], d[i-1] + a[i]);
            }
            int max = d[0], sum = 0, j= 0;
            for (int i = 0; i < number; i++){
                if (max < d[i]){
                    max = d[i];
                    start = j + 1;
                    end = i + 1;
                }
                if (d[i] < 0){
                    j = i + 1;
                }
            }
            System.out.println("Case "+(length - n)+":");
            System.out.println(max+" "+start+" "+end);
            if (n != 0)
                System.out.println();
        }
    }
}