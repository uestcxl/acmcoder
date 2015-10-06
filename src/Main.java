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
 * x轴排序tle，y轴果断ac
 */
public class Main {
    public static int[] a = new int[10010];

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        for (int i = 0; i < n ; i++) {
            a[i] = cin.nextInt();
        }

        int maxfront = 0;
        int maxtear = 0;
        int max = -1;
        int sum = -1;
        int tear = 0;
        int front = 0;

        for (int i = 0; i != n; i ++) {
            if (sum < 0) {
                sum = a[i];
                front = i;
                tear= i;
            }else {
                sum += a[i];
                tear = i;
            }

            if (sum > max) {
                max = sum;
                maxfront = front;
                maxtear = tear;
            }
        }

        if (max < 0) {
            System.out.println("0 " + a[0] + " " + a[n-1]);
        }else {
            System.out.println(max + " " + a[maxfront] + " " +a[maxtear]);
        }
    }
}
