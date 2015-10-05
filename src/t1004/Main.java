package t1004;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xl on 15/10/6.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (scanner.hasNext()){
            number = scanner.nextInt();
            if (number == 0){
                return;
            }

            Map<String, Integer> ballons = new HashMap<String, Integer>();
            String maxColor = "";
            int maxTimes = 0;
            String currentColor = "";

            for (int i = 0; i < number; i++){
                currentColor = scanner.next();
                if (ballons.containsKey(currentColor)){
                    int times = ballons.get(currentColor);
                    ballons.put(currentColor, times+1);
                }
                else {
                    ballons.put(currentColor, 1);
                }
            }

            for (Map.Entry<String, Integer> entry : ballons.entrySet()){
                if (entry.getValue() > maxTimes){
                    maxColor = entry.getKey();
                    maxTimes = entry.getValue();
                }
            }
            System.out.println(maxColor);
        }
    }
}
