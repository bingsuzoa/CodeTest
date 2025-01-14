package programmers;

import java.io.*;
import java.util.*;

public class FutureCity {

    public static void main(String[] args) throws IOException {
        FutureCity test = new FutureCity();
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

        int[] distance_fv = new int[N+1];
        int[] distance_sv = new int[N+1];
        for(int i = 0; i < N+1; i++) {
            distance_fv[i] = 101;
            distance_sv[i] = 101;
        }
        List<List<Integer>> list = new ArrayList<>(N+1);

        for(int i = 0; i < N+1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < graph.length; i++) {
            list.get(graph[i][0]).add(graph[i][1]);
        }
        for(int i = 0; i < graph.length; i++) {
            list.get(graph[i][1]).add(graph[i][0]);

        }



        distance_fv = test.distanceCount(distance_fv, list, 1);
        answer += distance_fv[first_visit];
        distance_sv = test.distanceCount(distance_sv, list, first_visit);
        answer += distance_sv[second_visit];

        if(distance_fv[first_visit] == 101 || distance_sv[second_visit] == 101) {
            bw.write("-1");
        } else {
            bw.write(answer + " ");
        }
        bw.close();
    }

    public int[] distanceCount(int[] distance, List<List<Integer>> list, int first) {
        boolean[] visited = new boolean[distance.length];
        PriorityQueue<Road> pq = new PriorityQueue<>(1, new RoadComparator());
        pq.add(new Road(0,first));
        distance[first] = 0;
        visited[first] = true;

        while(!pq.isEmpty()) {
            Road roadRoute = pq.poll();
            int now_minRoute = roadRoute.now_minRoute;
            int node = roadRoute.node;

            for(int i = 0; i < list.get(node).size(); i++) {
                int new_node = list.get(node).get(i);
                if(!visited[new_node]) {
                    int cost = now_minRoute + 1;
                    if(cost < distance[new_node]) {
                        distance[new_node] = cost;
                        visited[new_node] =true;
                        pq.add(new Road(distance[new_node], new_node));
                    }
                }
            }
        }
        return distance;
    }

}

class Road {
    int now_minRoute;
    int node;

    public Road(int now_minRoute, int node) {
        this.now_minRoute = now_minRoute;
        this.node = node;
    }
}

class RoadComparator implements Comparator<Road> {
    @Override
    public int compare(Road o1, Road o2) {
        if(o1.now_minRoute == o2.now_minRoute) {
            return o1.node - o2.node;
        } else {
            return o1.now_minRoute - o2.now_minRoute;
        }
    }
}