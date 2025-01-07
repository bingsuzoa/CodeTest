package programmers;

import java.io.*;
import java.util.*;

public class MazeEscape {
    /*private static int[][] graph = {{1,0,1,0,1,0},{1,1,1,1,1,1},{0,0,0,0,0,1},{1,1,1,1,1,1},{1,1,1,1,1,1}};*/
    private static int[][] graph;
    private static int m;
    private static int n;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        String frame = br.readLine();
        st = new StringTokenizer(frame);
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[m][n];
        for(int i = 0; i < m; i++) {
            String columns = br.readLine();
            st = new StringTokenizer(columns);
            while(st.hasMoreTokens()) {
                for(int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        MazeEscape test = new MazeEscape();
        bw.write(test.solution(graph) + " ");
        bw.close();
    }

    public int solution(int[][] graph) {
        m = graph.length;
        n = graph[0].length;
        int[][] newGraph = move(graph,0,0);
        return newGraph[m-1][n-1];
    }


    public int[][] move(int[][] graph, int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {x,y});


        while(!stack.isEmpty()) {
            int[] temp = stack.pop();
            x = temp[0];
            y = temp[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || graph[nx][ny] == 0) {
                    continue;
                }
                if (graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[x][y] + 1;
                    stack.add(new int[]{nx, ny});
                }
            }
        }
        return graph;
    }
}
