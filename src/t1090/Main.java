package t1090;

import java.util.Scanner;

/**
 * Created by xl on 15/10/10.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            System.out.println(scanner.nextInt() + scanner.nextInt());
        }
    }
}
