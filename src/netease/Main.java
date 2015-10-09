package netease;

import java.util.Scanner;

/**
 * Created by xl on 15/10/9.
 */

/*

兰博教训了提莫之后，然后和提莫讨论起约德尔人，谈起约德尔人，自然少不了一个人，那就是黑默丁格——约德尔人历史上最伟大的科学家。
提莫说，黑默丁格最近在思考一个问题：黑默丁格有三个炮台，炮台能攻击到距离它R的敌人,(两点之间的距离为两点连线的距离,例如(3,0)和(0,4)之间的距离是5),如果一个炮台能攻击到敌人，那么会对敌人造成1X的伤害。黑默丁格将三个炮台放在N*M方格中的点上,并且给出敌人的坐标。

问：那么敌人受到伤害会是多大？

第一行9个整数，R，x1,y1,x2,y2,x3,y3,x0,y0。(0 <= R，x1,y1,x2,y2,x3,y3,x0,y0 <= 100) R 代表炮台攻击的最大距离，(x1,y1),(x2,y2),(x3,y3)代表三个炮台的坐标。(x0,y0)代表敌人的坐标。

输出一行,这一行代表敌人承受的最大伤害,(如果每个炮台都不能攻击到敌人，输出0X)。
输出格式见样例。
* */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int i, j, R;
        int hit = 0;
        int[] a = new int[7];
        int x0, y0;

        R = scanner.nextInt();
        for (i = 1; i <= 6; i++){
            a[i] = scanner.nextInt();
        }

        x0 = scanner.nextInt();
        y0 = scanner.nextInt();

        i = 1;
        while (i <= 6){
            if ( R >= Math.sqrt((a[i] - x0)*(a[i] - x0) + (a[i+1] - y0)*(a[i+1] - y0))){
                ++hit;
            }
            i = i + 2;
        }
        System.out.println(hit+"X");

        scanner.close();
    }
}
