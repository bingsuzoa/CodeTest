import java.util.*;

public class Solution {
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int x, y, direction;

    public static void turn_left() {
        direction -= 1;
        if(direction == -1) {
            direction = 3;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        x = sc.nextInt();
        y = sc.nextInt();
        direction = sc.nextInt();

        graph = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        graph[x][y] = 2;
        int cnt = 0;
        int block = 0;
        while(true) {
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if(graph[nx][ny] == 0) {
                block = 0;
                x  = nx;
                y = ny;
                graph[nx][ny] = 2;
                cnt++;
            } else {
                block++;
                if(block == 4) {
                    nx = x - dx[direction];
                    ny = y - dx[direction];
                    if(graph[nx][ny] == 1) {
                        System.out.println(cnt);
                        break;
                    } else {
                        cnt++;
                        x = nx;
                        y = ny;
                        block = 0;
                    }
                }
            }
        }
    }
}

