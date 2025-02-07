package programmers;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test250207 {
    static int M;
    static int N;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[M][N];
        for (int i = 0; i < M; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            while (st.hasMoreTokens()) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0));

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= M || nx <= -1 || ny >= N || ny <= -1 || graph[nx][ny] != 1) {
                    continue;
                }

                graph[nx][ny] = graph[x][y] + 1;
                queue.add(new Node(nx, ny));
            }
        }


        bw.write(graph[M-1][N-1] + "");
        bw.close();
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
