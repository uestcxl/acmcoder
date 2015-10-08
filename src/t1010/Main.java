package t1010;

import java.util.Scanner;

/**
 * Created by xl on 15/10/7.
 * 问题描述：http://acm.acmcoder.com/showproblem.php?pid=1010
 * 思路：dfs
 */
public class Main {
    static int N, M, T;
    static char[][] maze;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            N = scanner.nextInt();
            M = scanner.nextInt();
            T = scanner.nextInt();
            if (N == 0 || M == 0 || T ==0)
                System.exit(0);
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
//                    maze[i][j] = ;
                }
            }
        }
        scanner.close();
    }
}
