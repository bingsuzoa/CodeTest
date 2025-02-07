package programmers;

import java.io.*;
import java.util.StringTokenizer;

public class Test250207 {
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Test250207 test = new Test250207();

        String input = br.readLine();
        st = new StringTokenizer(input);
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] graph = new int[M][N];
        visited = new boolean[M][N];

        for(int i = 0; i < M; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            while(st.hasMoreTokens()) {
                for(int j = 0; j < graph[0].length; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        int count = 0;
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[0].length; j++) {
                if(visited[i][j] || graph[i][j] == 1) {
                    continue;
                }
                count += test.findZero(graph, i, j);
            }
        }
        bw.write(count + "");
        bw.close();
    }

    public int findZero(int[][] graph, int i, int j) {
        if(i >= graph.length || i < 0 || j >= graph[0].length || j < 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        if(graph[i][j] == 0) {
            findZero(graph, i+1, j);
            findZero(graph, i, j+1);
            findZero(graph, i-1, j);
            findZero(graph, i, j-1);
        } else {
            return 0;
        }
        return 1;
    }
}
