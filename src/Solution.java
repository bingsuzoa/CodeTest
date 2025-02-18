

public class Solution {
    static int max_m;
    static int max_n;
    static int min = Integer.MAX_VALUE;

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        max_m = m;
        max_n = n;
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
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
            if (y == sy && x < sx) {
                return Integer.MAX_VALUE;
            }
            sx = -sx;
            int result = (x - sx) * (x - sx) + (y - sy) * (y - sy);
            return result;
        }
        if (direction.equals("RIGHT")) {
            if (y == sy && sx < x) {
                return Integer.MAX_VALUE;
            }
            sx = 2 * max_m - sx;
            int result = (x - sx) * (x - sx) + (y - sy) * (y - sy);
            return result;
        }
        if (direction.equals("UP")) {
            if (x == sx && sy < y) {
                return Integer.MAX_VALUE;
            }
            sy = 2 * max_n - sy;
            int result = (x - sx) * (x - sx) + (y - sy) * (y - sy);
            return result;
        }
        if (direction.equals("BOTTOM")) {
            if (x == sx && sy > y) {
                return Integer.MAX_VALUE;
            }
            sy = -sy;
            int result = (x - sx) * (x - sx) + (y - sy) * (y - sy);
            return result;
        }
        return 0;
    }
}