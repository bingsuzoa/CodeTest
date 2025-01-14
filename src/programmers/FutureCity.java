package programmers;

import java.io.*;
import java.util.*;

public class FutureCity {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int answer = 0;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[M][2];
        for(int i = 0; i < M; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        String input3 = br.readLine();
        st = new StringTokenizer(input3);
        int second_visit = Integer.parseInt(st.nextToken());
        int first_visit = Integer.parseInt(st.nextToken());

        int[][] routeMap = new int[N+1][N+1];

        for(int i = 0; i < routeMap.length; i++) {
            for(int j = 0; j <routeMap[i].length; j++) {
                routeMap[i][j] = 101;
            }
        }

        for(int i = 0; i < routeMap.length; i++) {
            routeMap[i][i] = 0;
        }

        for(int i = 0; i < graph.length; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            routeMap[x][y] = 1;
            routeMap[y][x] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int a = 1; a <= N; a ++) {
                for(int b = 1; b <= N; b ++) {
                    routeMap[a][b] = Math.min(routeMap[a][b], routeMap[a][i] + routeMap[i][b]);
                }
            }
        }


        answer = routeMap[1][first_visit] + routeMap[first_visit][second_visit];
        bw.write(answer + "");
        bw.close();


    }
}