package programmers;

import java.io.*;
import java.util.*;

public class MazeEscape {
    /*private static int[][] graph = {{1,0,1,0,1,0},{1,1,1,1,1,1},{0,0,0,0,0,1},{1,1,1,1,1,1},{1,1,1,1,1,1}};*/
    private static int[][] graph;
    private static int m;
    private static int n;
    private static int count = 0;

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
        int answer = move(0,0);
        return answer;
    }


    public int move(int x, int y) {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[] {x,y});

        while(true) {
            int[] temp = stack.pop();
            x = temp[0];
            y = temp[1];
            if(x <= -1 || y <= -1 || x >= m || y >= n || graph[x][y] == 0) {
                continue;
            }
            else if(x == m-1 && y == n-1) {
                count++;
                break;
            }
            else {
                count++;
                stack.add(new int[] {x-1 , y});
                stack.add(new int[] {x, y-1});
                stack.add(new int[] {x, y+1});
                stack.add(new int[] {x+1 , y});
            }
        }
        return count;
    }
}
