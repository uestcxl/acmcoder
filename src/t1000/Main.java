package t1000;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 * 题目描述：http://acm.acmcoder.com/showproblem.php?pid=1000
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
            System.out.println(scanner.nextInt() + scanner.nextInt());
        scanner.close();
    }
}
