package t1089;

import java.util.Scanner;

/**
 * Created by xl on 15/10/10.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(scanner.nextInt() + scanner.nextInt());
        }
        scanner.close();
    }
}
