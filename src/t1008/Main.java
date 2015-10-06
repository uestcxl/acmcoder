package t1008;

import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int number = scanner.nextInt();
            if (number == 0)
                System.exit(0);

            int[] floors = new int[number+1];
            floors[0] = 0;
            for (int i = 1; i < floors.length; i++){
                floors[i] = scanner.nextInt();
            }
            int totalTime = 0;
            int up = 6, down = -4, wait = 5;
            int step = 0;
            int gap = 0;

            for (int i = 1; i <= number; i++){
                gap = floors[i] - floors[i-1];
                step = floors[i] - floors[i-1] > 0 ? up : down;
                totalTime += gap * step + wait;
            }

            System.out.println(totalTime);
        }
    }
}
