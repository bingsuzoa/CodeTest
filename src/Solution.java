import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int count = 0;
        for(int hour = 0; hour <= n; hour ++) {
            for (int minute = 0; minute <= 59; minute++) {
                for(int second = 0; second <= 59; second++) {
                   String time = String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
                   for(int i = 0; i < time.length(); i++) {
                       if(time.charAt(i) == '3') {
                           count++;
                           break;
                       }
                   }
                }
            }
        }
        System.out.println(count);
    }
}