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

    public static void main(String[] args) {
        new DK().go();
    }
}

class Point implements Comparable<Point>{
    double x;
    double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public int compareTo(Point obj) {
        Point other = obj;
        if(this.y!=other.y) {//由小到大排序
            return (int)Math.ceil(this.y - other.y);
        }
        return (int)Math.ceil(this.x - other.x);
    }
}

class DK {

    double x;
    double y;
    Point point[];
    int a[];

    public DK(){
        a = new int[100001];
    }
    public void go() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            int n = sc.nextInt();
            if(0==n) {
                break;
            }
            point = new Point[n];
            for(int i=0; i<n; i++) {
                point[i] = new Point();
            }
            for(int i=0; i<n; i++) {
                x = sc.nextDouble();
                y = sc.nextDouble();
                point[i].x = x;
                point[i].y = y;
            }
            Arrays.sort(point);
//            for(int i=0; i<n; i++) {
//                System.out.println(point[i].x+" "+point[i].y);
//            }
            a = new int[n];
            double ans = solve(0,n-1)/2;
            System.out.println(String.format("%.2f", ans));
        }
    }
    private double solve(int left, int right) {
        double ans = 1e-7;
        if(left==right) {
            return ans;
        }
        if(left==right-1) {
            return distance(point[left], point[right]);
        }
        int mid = (left+right)>>1;
        double ans1 = solve(left,mid);
        //注意：不是mid+1
        double ans2 = solve(mid,right);
        ans = Math.min(ans1,ans2);
        int j = 0;
        for(int i=left; i<=right; i++) {
            if(Math.abs(point[i].y-point[mid].y)<=ans) {
                a[j++] = i;
            }
        }
        /*
         * 加上下面的排序就AC，否则WA，我认为至多TLE，
         * 因为扫描的是和point[i]最相近的两个矩形2*ans区间
         */
        //不知道如何用comparator接口实现间接排序，所以就写了个选择排序
        mySort(a,j);
        for(int i=0; i<j; i++) {
            for(int k=i+1; k<j&&Math.abs(point[a[i]].x - point[a[k]].x)<ans; k++) {
                double dis = distance(a[i], a[k]);
                if(ans>dis) {
                    ans = dis;
                }
            }
        }
        return ans;
    }
    private void mySort(int[] a, int j) {
        for(int i=0; i<j; i++) {
            for(int k=i+1; k<j; k++) {
                if(point[a[i]].x<point[a[k]].x) {
                    int temp = a[i];
                    a[i] = a[k];
                    a[k] = temp;
                }
            }
        }
    }
    private double distance(Point p1, Point p2) {
        double dis = Math.hypot(p1.x-p2.x, p1.y-p2.y);
        return dis;
    }
    private double distance(int i, int j) {//point搞为成员变量
        double dis = Math.hypot(point[i].x-point[j].x, point[i].y-point[j].y);
        return dis;
    }
}
