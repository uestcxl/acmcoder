package t1094;

import java.util.Scanner;

/**
 * Created by xl on 15/10/7.
 * 来道简单的压压惊hhhh
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number, sum;
        while (scanner.hasNext()){
            sum = 0;
            number = scanner.nextInt();
            for (int i = 0; i < number; i++){
                sum += scanner.nextInt();
            }
            System.out.println(sum);
        }
        scanner.close();
    }
}
