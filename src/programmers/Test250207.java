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
        queue.add(new Node(0,0, 1));

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int value = node.value;
            if (x >= M || x <= -1 || y >= N || y <= -1 || graph[x][y] != 1) {
                continue;
            }

            graph[x][y] = value;

            for(int i = 0; i < dx.length; i++) {
                queue.add(new Node(x + dx[i], y+ dy[i], graph[x][y]+1));
            }
        }


        bw.write(graph[M-1][N-1] + "");
        bw.close();
    }
}

class Node {
    int x;
    int y;
    int value;

    Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

}
