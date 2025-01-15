package programmers;

import java.io.*;
import java.util.*;

public class Telegram {
    private static int cityCount = 0;
    private static int totalTime = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input1 = br.readLine();
        st = new StringTokenizer(input1);
        int cityTotal = Integer.parseInt(st.nextToken());
        int routeTotal = Integer.parseInt(st.nextToken());
        int city = Integer.parseInt(st.nextToken());

        int[][] graph = new int[cityTotal + 1][cityTotal + 1];
        boolean[] visited = new boolean[cityTotal + 1];

        for(int i = 0; i <graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                graph[i][j] = 1001;
            }
        }

        List<List<Integer>> list = new ArrayList<>(cityTotal + 1);
        for(int i = 0; i < cityTotal + 1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < routeTotal; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[x][y] = time;
            list.get(x).add(y);
        }

        PriorityQueue<MinTimeOfRoute> pq  = new PriorityQueue<>(1, new MinTimeOfRouteComparator());
        pq.add(new MinTimeOfRoute(0, city));
        while(!pq.isEmpty()) {
            MinTimeOfRoute nowRoute = pq.poll();
            int now_time  = nowRoute.time;
            int node = nowRoute.node;
            visited[node] = true;

            for(int i = 0; i < list.get(node).size(); i++) {
                int y = list.get(node).get(i);
                if(!visited[y]) {
                    visited[y] = true;
                    int cost = now_time + graph[node][y];
                    if(cost < graph[city][y]) {
                        graph[node][y] = cost;
                    }
                    pq.add(new MinTimeOfRoute(graph[node][y], y));
                }
            }
        }
        for(int i = 0; i < graph[city].length; i++ ){
            if(graph[city][i] < 1001) {
                cityCount ++;
                totalTime = Math.max(totalTime, graph[city][i]);
            }
        }
        bw.write(cityCount + " " + totalTime);
        bw.close();

    }
}

class MinTimeOfRoute {
    int time;
    int node;

    public MinTimeOfRoute(int time, int node) {
        this.time = time;
        this.node = node;
    }
}

class MinTimeOfRouteComparator implements Comparator<MinTimeOfRoute> {

    @Override
    public int compare(MinTimeOfRoute o1, MinTimeOfRoute o2) {
        if(o1.time == o2.time) {
            return o1.node - o2.node;
        } else {
            return o1.time - o2.time;
        }
    }
}

