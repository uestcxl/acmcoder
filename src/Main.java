import java.math.BigInteger;
import java.util.*;

/**
 * Created by xl on 15/10/5.
 */

/*1001

*/

/*1003 有bug
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++){
            int start = 0, end = 0, max = -1000;
            int number = scanner.nextInt();
            int[] a = new int[number];
            int[] b = new int[number];
            int[][] pos = new int[number][2];

            for (int j = 0; j < number; j++){
                a[j] = scanner.nextInt();
                b[j] = 0;
            }

            for (int j = 0; j < number; j++){
                max = a[j];
                pos[j][0] = start;
                for (int step = j + 1; step < number; step++){
                    if (max < (max + a[step])){
                        max += a[step];
                        end = step;
                        pos[j][1] = end;
                    }
                }
                b[j] = max;
            }
            max = b[0];
            for (int hh = 1; hh < b.length; hh++){
                if (b[hh] > max){
                    max = b[hh];
                    start = pos[hh][0];
                    end = pos[hh][1];
                    System.out.print(hh);
                }
            }
            System.out.println(max+" "+(start+1) +" "+ (end+1));
            if (i != n) System.out.println();
        }
    }
}*/

//1003 Max Sum
/*
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
}*/

/*
public class Main{
    public static void combination1() {
            */
/*全组合：
             * 思路是利用二进制的特性，每次加1即可遍历所有位的不同情况，很好理解
            代码同上
                *//*

        String arr[] = {"a", "b", "c"};
        int all = arr.length;
        int nbit = 1 << all;
        for (int i = 0; i < nbit; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < all; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(arr[j]);
                }
            }
            System.out.println(sb);
        }
    }
    public static void main(String args[]){
        combination1();
    }
}*/

public class Main{
    public static void permutation(String[] str , int first,int end) {
        //输出str[first..end]的所有排列方式
        if(first == end) {    //输出一个排列方式
            StringBuilder stringBuilder = new StringBuilder();
            for(int j=0; j<= end ;j++) {
                stringBuilder.append(str[j]+" ");
            }
            System.out.println(stringBuilder.toString().trim());
        }

        for(int i = first; i <= end ; i++) {
            swap(str, i, first);
            permutation(str, first+1, end);  //固定好当前一位，继续排列后面的
            swap(str, i, first);
        }
    }

    private static void swap(String[] str, int i, int first) {
        String tmp;
        tmp = str[first];
        str[first] = str[i];
        str[i] = tmp;
    }

    public static void main(String args[]) throws Exception {
        String[] str = {"a", "b", "c"};
        permutation(str, 0, str.length - 1);
    }
}
