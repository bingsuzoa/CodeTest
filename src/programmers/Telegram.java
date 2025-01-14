package programmers;

import java.io.*;
import java.util.StringTokenizer;

public class Telegram {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input1 = br.readLine();
        st = new StringTokenizer(input1);
        int cityTotal = Integer.parseInt(st.nextToken());
        int routeTotal = Integer.parseInt(st.nextToken());
        int city = Integer.parseInt(st.nextToken());

        int[][] graph = new int[routeTotal][3];
        for(int i = 0; i < graph.length; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] route = new int[cityTotal + 1][cityTotal + 1];

        for(int i = 0; i < route.length; i++) {
            for(int j = 0; j < route[i].length; j++) {
                route[i][j] = 1001;
            }
        }

        for(int i = 0; i < route.length; i++) {
            route[i][i] = 0;
        }

        for(int i = 0; i < graph.length; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            int time = graph[i][2];
            route[x][y] = time;
        }

        for(int i = 1; i <= cityTotal; i++) {
            for(int a = 1; a <= cityTotal; a++) {
                for(int b = 1; b <= cityTotal; b++) {
                    route[a][b] = Math.min(route[a][b], route[a][i] + route[i][b]);
                }
            }
        }

        int max_city = 0;
        int max_time = 0;
        for(int i = 1; i <= cityTotal; i++) {
            if(route[city][i] > 0 && route[city][i] < 1001) {
                max_city++;
                max_time = Math.max(max_time, route[city][i]);
            }
        }

        bw.write(max_city + " " + max_time);
        bw.close();
    }
}
