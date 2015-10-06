package t1007;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * Created by xl on 15/10/6.
 * 问题描述：http://acm.acmcoder.com/showproblem.php?pid=1007
 * 问题简化：求N个点中相距最近的两个点，就是求最近点对，编程之美里面有一样的文章。2.11
 * 思想：用分治。先将数据按照x坐标排序，取中点，分别求各组的最小距离。
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int number = 0;
        while (scanner.hasNext()){
            number = scanner.nextInt();
            if (number == 0)
                System.exit(0);

            double minDis = 0;
            Point[] point = new Point[number];
            for (int i = 0; i < number; i++){
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                point[i] = new Point(x, y);
            }

            minDis = new Main().getMinDis(point);
            System.out.println(String.format("%.2f", minDis / 2));
        }
        scanner.close();
    }

    public double getMinDis(Point[] points){
        //先对序列按x轴大小进行排序
        Arrays.sort(points, new AscByX());
        return cal(points, 0, points.length - 1);
    }

    public double cal(Point[] points, int left, int right){
        double result = 0;
        if (left == right)
            return result;
        else if (left == right - 1)
            return distance(points[left], points[right]);
        else if (left == right -2 ){
            double temp1 = distance(points[left], points[left+1]);
            double temp2 = distance(points[left], points[right]);
            double temp3 = distance(points[left+1], points[right]);
            return min(min(temp1, temp2), temp3);
        }

        int mid = (left + right) >> 1;
        result = min(cal(points, left, mid), cal(points, mid, right));

        //选出中线附近，x坐标值相距小于result的坐标点，并记录，后续从这些点中检查出跨线的点距离。
        ArrayList<Point> nearPoints = new ArrayList<>();
        for (Point point : points){
            if (Math.abs(points[mid].x - point.x) < result){
                nearPoints.add(point);
            }
        }

        //对y坐标进行排序，并计算
        Collections.sort(nearPoints, new AscByY());
        for (int i = 0; i < nearPoints.size(); i++){
            for (int j = i + 1; j < nearPoints.size() && nearPoints.get(j).y - nearPoints.get(i).y < result; j++){
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

    public double min(double a, double b){
        return a < b ? a : b;
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

    public Point(double a, double b){
        x = a;
        y = b;
    }
}

/*
* Java真是给跪了。。。同样的思想，用c++：

#include<iostream>
#include<cmath>
#include<algorithm>
using namespace std;
int n;
struct node
{
  double x;
  double y;
}p[100005];

int a[100005];

double cmpx(node a,node b)
{
  return a.x<b.x;
}

double cmpy(int a,int b)
{
  return p[a].y<p[b].y;
}

double min(double a,double b)
{
  return a<b?a:b;
}

double dis(node a,node b)
{
  return sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
}

double find(int l,int r)
{
     if(r==l+1)
      return dis(p[l],p[r]);
     if(l+2==r)
      return min(dis(p[l],p[r]),min(dis(p[l],p[l+1]),dis(p[l+1],p[r])));
     int mid=(l+r)>>1;
     double ans=min(find(l,mid),find(mid+1,r));
     int i,j,cnt=0;
     for(i=l;i<=r;i++)
     {
       if(p[i].x>=p[mid].x-ans&&p[i].x<=p[mid].x+ans)
          a[cnt++]=i;
     }
     sort(a,a+cnt,cmpy);
     for(i=0;i<cnt;i++)
     {
       for(j=i+1;j<cnt;j++)
       {
         if(p[a[j]].y-p[a[i]].y>=ans) break;
         ans=min(ans,dis(p[a[i]],p[a[j]]));
       }
     }
     return ans;
}

int main()
{
    int i;

    while(scanf("%d",&n)!=EOF)
    {
      if(!n) break;
      for(i=0;i<n;i++)
       scanf("%lf %lf",&p[i].x,&p[i].y);
      sort(p,p+n,cmpx);
      printf("%.2lf%\n",find(0,n-1)/2);
    }
    return 0;
}

* */