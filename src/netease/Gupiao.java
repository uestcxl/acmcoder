package netease;

import java.util.Scanner;

/**
 * Created by xl on 15/10/9.
 */

/*
* Time Limit: 1000/1000 MS (Java/Others) Memory Limit: 32768/32768 K (Java/Others)
Problem Description:
有股神吗？
有，小红就是！

经过严密的计算，小红买了一支股票，他知道从他买股票的那天开始，股票会有以下变化：第一天不变，以后涨一天，跌一天，涨两天，跌一天，涨三天，跌一天...依此类推。
* 为方便计算，假设每次涨和跌皆为1，股票初始单价也为1，请计算买股票的第n天每股股票值多少钱？
* 输入包括多组数据；
每行输入一个n，1<=n<=10^9 。
* */

/*思路：求出day天中股票跌的天数即可。递推式为f(n)=f(n-1)+n+1;f(1)=3;其中n表示跌的天数，f表示总天数
* 求解方程((n+4)*(n-1)/2+3)=day中n的解，就是包含几个down。
*/
public class Gupiao {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Gupiao main = new Gupiao();

        int day = 0;
        while (scanner.hasNext()){
            day =  scanner.nextInt();
            System.out.println(main.cal(day));
        }

        scanner.close();
    }

    public long cal(int day){
        if (day == 1){
            return 1;
        }
        long money = 0;
        double n = 0;
        n = (Math.sqrt(8 * day + 1) - 3) / 2;
        money = day - 2 * (int)n;
        return money;
    }
}