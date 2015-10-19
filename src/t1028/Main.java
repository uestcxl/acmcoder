package t1028;

import java.util.Scanner;

/**
 * Created by xl on 15/10/19.
 * 母函数：http://www.wutianqi.com/?p=596
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] generating = new int[121];
        int[] temp = new int[121];
        int i, j, k, n;
        for (i = 0; i < generating.length; i++){
            generating[i] = 1;
            temp[i] = 0;
        }

        for (i = 2; i < generating.length; i++){
            for (j = 0; j < generating.length; j++){
                for (k = 0; k * i < generating.length; k++){
                    if (k * i + j < generating.length)
                        temp[k * i + j] += generating[j];
                }
            }
            for (j = 0; j < generating.length; j++){
                generating[j] = temp[j];
                temp[j] = 0;
            }
        }
        while (scanner.hasNext()){
            n = scanner.nextInt();
            System.out.println(generating[n]);
        }

        scanner.close();
    }
}
