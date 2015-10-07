package t1009;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xl on 15/10/7.
 * 题目描述：http://acm.acmcoder.com/showproblem.php?pid=1009
 * 思路：贪心算法，f是耗费，j是价值，即求在总耗费为M的情况下，sum(j)最大。
 * 可先算出每个房间的单位耗费的价值，单位价值从大到小，依次取值。
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int M, N;
        while (scanner.hasNext()){
            M = scanner.nextInt();
            N = scanner.nextInt();
            if (M == -1 && N == -1)
                System.exit(0);

            WareHouse[] wareHouse = new WareHouse[N];
            double maxBeans = 0;
            for (int i = 0; i < N; i++){
                wareHouse[i] = new WareHouse(scanner.nextInt(), scanner.nextInt());
            }

            Arrays.sort(wareHouse);
//            for (int i = 0; i < N; i++)
//                System.out.println(wareHouse[i].j+ " "+wareHouse[i].f+" "+wareHouse[i].weight);

            for (int i = 0; i < N && M > 0; i++){
                if (wareHouse[i].f < M){   //若容量够，则优先取完单位价值高的
                    M -= wareHouse[i].f;
                    maxBeans += wareHouse[i].j;
                }
                else {
                    maxBeans += M * 1.0 * wareHouse[i].weight;
                    break;
                }
            }
            System.out.println(String.format("%.3f", maxBeans));
//            System.exit(0);
        }
        scanner.close();
    }
}

class WareHouse implements Comparable<WareHouse>{
    int j, f;
    double weight;

    public WareHouse(int a, int b){
        j = a;
        f = b;
        weight = j * 1.0 / f;
    }

    //按照weight从大到小排序
    @Override
    public int compareTo(WareHouse wareHouse) {
        return wareHouse.weight >= this.weight ? 1 : -1;
    }
}
