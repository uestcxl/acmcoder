package t1006;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 * 问题描述：http://acm.acmcoder.com/showproblem.php?pid=1006
 *
 * 问题思路：H:m:s时，
 *      ∆s = s*(360/60) = 6s
 *      ∆m = (s/60+m)*(360/60) = 6m+s/10
 *      ∆H = (h+m/60+s/3600)*(360/12) = 30h+m/2+s/120
 *      解不等式：
 *      D≤|∆h-∆m|≤360-D
 *      D≤|∆h-∆s|≤360-D
 *      D≤|∆s-∆m|≤360-D
 *      解方程d<=|hh-mm|<=360-d ==> hh=30*h+m/2+s/120,mm=6*m+s/10;
 *      解方程d<=|hh-ss|<=360-d ==> hh=30*h+m/2+s/120,ss=6*s;
 *      解方程d<=|mm-ss|<=360-d ==> mm=6*m+s/10,ss=6*s;
 *      设s为未知，即12*60进行遍历，累加秒长度。
 */

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double degrees = 0.0;
        while (scanner.hasNext()){
            degrees = scanner.nextDouble();
            if (degrees == -1){
                System.exit(0);
            }

            double length = 0;
            for (double hour = 0; hour < 12; hour++){
                for (double min = 0; min < 60; min++){
                    length += calHappyTime(degrees, hour, min);
                }
            }

            System.out.println(String.format("%.3f", 100 * length / (60 * 60 * 12)));
        }
    }

    public static double calHappyTime(double d, double hour, double min){
        double a, b;
        Range[][] ranges = new Range[3][2];  //存放上面三个不等式的解。
        double length = 0;
        Range smallRange = new Range();

        a = 1.0 / 120 - 0.1;
        b = 30 * hour + min / 2.0 - 6 * min;
        ranges[0][0] = smallRange.solve(d, a, b);
        ranges[0][1] = smallRange.solve(d, -a, -b);

        a = 1.0 / 120 - 6.0;
        b = 30 * hour + min / 2.0;
        ranges[1][0] = smallRange.solve(d, a, b);
        ranges[1][1] = smallRange.solve(d, -a, -b);

        a = 0.1 - 6;
        b = 6 * min;
        ranges[2][0] = smallRange.solve(d, a, b);
        ranges[2][1] = smallRange.solve(d, -a, -b);

        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < 2; k++){
                    smallRange = smallRange.Intersect(smallRange.Intersect(ranges[0][i], ranges[1][j]), ranges[2][k]);
                    length += smallRange.end - smallRange.start;
                }
            }
        }

        return length;
    }

}

class Range{
    double start, end;

    public Range(){
        start = 0.0;
        end = 0.0;
    }

    //解方程 d≤a*x+b≤360-d，和[0, 60]求交集
    public Range solve(double d, double a, double b){
        Range mixed = new Range();
        if (a > 0){
            mixed.start = (d - b) / a;
            mixed.end = (360 - b - d) / a;
        }
        else {
            mixed.start = (360 - b - d) / a;
            mixed.end = (d - b) / a;
        }

        if (mixed.start < 0)
            mixed.start = 0;
        if (mixed.end > 60)
            mixed.end = 60;
        if (mixed.start >= mixed.end)
            mixed.start = mixed.end = 0;

        return mixed;
    }

    //求并集
    public Range Intersect(Range a, Range b){
        Range intersect = new Range();

        intersect.start = a.start >= b.start ? a.start : b.start;
        intersect.end = a.end <= b.end ? a.end : b.end;
        if (intersect.start >= intersect.end)
            intersect.start = intersect.end = 0;

        return intersect;
    }
}