package t1095;

import java.util.Scanner;

/**
 * Created by xl on 15/10/8.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(scanner.nextInt()+scanner.nextInt());
            System.out.println();
        }
        scanner.close();
    }
}
