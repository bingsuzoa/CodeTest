
import java.io.*;
import java.util.*;

public class Solution {
    static PriorityQueue<int[]> queue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int s;

    public static void main(String[] args) throws IOException {
        Solution test = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];

        queue = new PriorityQueue<>((a, b) -> {
            if(a[3] == b[3]) return a[2] - b[2];
            return a[3] - b[3];
        });

        for (int i = 0; i < n; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            while (st.hasMoreTokens()) {
                for (int j = 0; j < n; j++) {
                    int number = Integer.parseInt(st.nextToken());
                    graph[i][j] = number;
                    if (number > 0) {
                        queue.add(new int[]{i, j, number, 0});
                    }
                }
            }
        }

        String input3 = br.readLine();
        st = new StringTokenizer(input3);
        s = Integer.parseInt(st.nextToken());
        int a_x = Integer.parseInt(st.nextToken());
        int a_y = Integer.parseInt(st.nextToken());

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            int virus = arr[2];
            int time = arr[3];

            if (x >= n || y >= n || x < 0 || y < 0) {
                continue;
            }

            if (time != 0 && graph[x][y] > 0) {
                continue;
            }

            graph[x][y] = virus;
            if(time < s) {
                test.move(x, y, virus, time);
            }
        }

        bw.write(graph[a_x - 1][a_y - 1] + "");
        bw.close();
    }

    public void move(int x, int y, int virus, int time) {
        for(int i = 0; i < 4; i++) {
            queue.add(new int[]{x + dx[i], y + dy[i], virus, time + 1});
        }

    }
}