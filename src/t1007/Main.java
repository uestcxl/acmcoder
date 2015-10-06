package t1007;

import java.util.*;

/**
 * Created by xl on 15/10/6.
 * 问题描述：http://acm.acmcoder.com/showproblem.php?pid=1007
 * 问题简化：求N个点中相距最近的两个点，就是求最近点对，编程之美里面有一样的文章。2.11
 * 思想：用分治。先将数据按照x坐标排序，取中点，分别求各组的最小距离。
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (scanner.hasNext()){
            number = scanner.nextInt();
            if (number == 0)
                System.exit(0);

            double minDis = 0;
            Point[] point = new Point[number];
            for (int i = 0; i < number; i++){
                point[i] = new Point();
                point[i].x = scanner.nextDouble();
                point[i].y = scanner.nextDouble();
            }

            minDis = new Quoit().getMinDis(point);
            System.out.println(String.format("%.2f", minDis / 2));
        }
    }
}

class Quoit{

    public double getMinDis(Point[] points){
        //先对序列按x轴大小进行排序
        Arrays.sort(points, new AscByX());
        return cal(points, 0, points.length - 1);
    }

    public double cal(Point[] points, int left, int right){
        double result = 0;
        if (left == right)
            return result;
        if (left == right - 1)
            return distance(points[left], points[right]);
        int mid = (left + right) >> 1;
        double minL = cal(points, left, mid);
        double minR = cal(points, mid, right);

        result = Math.min(minL, minR);
        //选出中线附近，x坐标值相距小于result的坐标点，并记录，后续从这些点中检查出跨线的点距离。
/*        int j = 0;
        for (int i = left; i <= right; i++){
            if (Math.abs(points[i].x - points[mid].x) <= result)
                close[j++] = i;
        }

        Arrays.sort(points, new AscByY());
        //对y坐标进行排序，并计算
        for (int i = 0; i < j; i++){
            for (int k = i + 1; k < j && Math.abs(points[close[k]].y - points[close[i]].y) < result; k++){
                double distance = distance(points[close[k]], points[close[i]]);
                if (distance < result)
                    result = distance;
            }
        }*/
        ArrayList<Point> nearPoints = new ArrayList<>();
        for (Point point : points){
            if (Math.abs(points[mid].x - point.x) < result){
                nearPoints.add(point);
            }
        }

        Collections.sort(nearPoints, new AscByY());
        for (int i = 0; i < nearPoints.size(); i++){
            for (int j = i + 1; j < nearPoints.size(); j++){
                if (nearPoints.get(j).y - nearPoints.get(i).y > result){
                    break;
                }
                double nearDis = distance(nearPoints.get(j), nearPoints.get(i));
                if (nearDis < result){
                    result = nearDis;
                }
            }
        }

        return result;
    }

    public double distance(Point pointA, Point pointB){
        return Math.hypot(pointA.x - pointB.x, pointA.y - pointB.y);
    }

    //以x为基准排序
    private static class AscByX implements Comparator<Point>{
        @Override
        public int compare(Point pointA, Point pointB) {
            if (pointA.x != pointB.x)
                return (int)Math.ceil(pointA.x - pointB.x);
            return (int)Math.ceil(pointA.y - pointB.y);
        }
    }

    //以y为基准排序
    private static class AscByY implements Comparator<Point>{
        @Override
        public int compare(Point pointA, Point pointB) {
            if (pointA.y != pointB.y)
                return (int)Math.ceil(pointA.y - pointB.y);
            return (int)Math.ceil(pointA.x - pointB.x);
        }
    }
}

class Point {
    double x;
    double y;

    public Point(){
        this.x = 0;
        this.y = 0;
    }
}