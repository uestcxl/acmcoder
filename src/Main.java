import java.math.BigInteger;
import java.util.Scanner;

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

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        int caseCount = 0;
        while(times-- > 0){
            int num = sc.nextInt();
            int tmpMax = 0;
            int max = -10000, left, right;
            left = right = 1;
            int i = 0;
            int flag = 0;
            while(++i <= num){
                tmpMax += sc.nextInt();
                if(tmpMax > max){
                    max = tmpMax;
                    right = i;
                    if(flag != 0){
                        left = flag;
                        flag = 0;
                    }
                } else if (tmpMax < 0) {
                    tmpMax = 0;
                    flag = i + 1;
                }
            }
            System.out.println("Case " + ++caseCount + ":");
            System.out.println(max + " " + left + " " + right);
            if(times != 0)
                System.out.println();
        }
        sc.close();
    }
}
