import java.math.BigDecimal;
import java.math.RoundingMode;

public class Solution {
    static int max_m;
    static int max_n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] balls = {{7,7},{2,7},{7,3}};
        int[] answer = test.solution(10,10,3,7,balls);
        for(int value : answer) {
            System.out.println(value);
        }
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        max_m = m;
        max_n = n;
        int[] answer = new int[balls.length];

        for(int i = 0; i < balls.length; i++) {
            min = Integer.MAX_VALUE;
            min = Math.min(min, measure(startX, startY, balls[i][0], balls[i][1], "LEFT"));
            min = Math.min(min, measure(startX, startY, balls[i][0], balls[i][1], "RIGHT"));
            min = Math.min(min, measure(startX, startY, balls[i][0], balls[i][1], "UP"));
            min = Math.min(min, measure(startX, startY, balls[i][0], balls[i][1], "BOTTOM"));
            answer[i] = min;
        }
        return answer;
    }

    public int measure(int sx, int sy, int x, int y, String direction) {
        if (direction.equals("LEFT")) {
            if(sy == y) {
                return Integer.MAX_VALUE;
            }
            double sum_1 = (x * x) + Math.pow(Math.round((y-sy) / (double)2), 2);
            double sum_2 = (sx * sx) + Math.pow(Math.round((y-sy) / (double)2), 2);
            sum_1 = Math.round(Math.sqrt(sum_1));
            sum_2 = Math.round(Math.sqrt(sum_2));
            double result = Math.pow(sum_1 + sum_2, 2);
            return (int)Math.round(result);
        }
        if (direction.equals("RIGHT")) {
            if(sy == y) {
                return Integer.MAX_VALUE;
            }
            double sum_1 = Math.pow(max_m-sx, 2) + Math.pow(((y - sy) / (double)2), 2);
            double sum_2 = Math.pow(max_m-sx, 2) + Math.pow(((y - sy) / (double)2), 2);
            sum_1 = Math.sqrt(sum_1);
            sum_2 = Math.sqrt(sum_2);
            double result = Math.pow(sum_2 + sum_1, 2);
            return (int)Math.round(result);
        }
        if (direction.equals("UP")) {
            if(sx == x) {
                return Integer.MAX_VALUE;
            }
            double sum_1 = Math.pow((sx-x) / (double)2, 2) + Math.pow(max_n-y,2);
            double sum_2 = Math.pow((sx-x) / (double)2, 2) + Math.pow(max_n-sy, 2);
            sum_1 = Math.sqrt(sum_1);
            sum_2 = Math.sqrt(sum_2);
            double result = Math.pow(sum_2 + sum_1, 2);
            return (int)Math.round(result);
        }
        if (direction.equals("BOTTOM")) {
            if(sx == x) {
                return Integer.MAX_VALUE;
            }
            double sum_1 = Math.pow((sx-x) / (double)2, 2) + Math.pow(y,2);
            double sum_2 = Math.pow((sx-x) / (double)2, 2) + Math.pow(sy,2);
            sum_1 = Math.sqrt(sum_1);
            sum_2 = Math.sqrt(sum_2);
            double result = Math.pow(sum_2 + sum_1, 2);
            return (int)Math.round(result);
        }
        return 0;
    }

}