import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int[] graph;

    public static void main(String[] args) throws IOException {
         Solution test = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            queue.add(new Node(x,y,cost));
        }

        graph = new int[n+1];
        for(int i = 1; i < n+1; i++) {
            graph[i] = i;
        }

        int sum = 0;
        int max = 0;
        Set<Integer> set = new HashSet<>();
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int cost = node.cost;
            if(test.find(graph, x) == test.find(graph, y)) {
                continue;
            }
            sum += cost;
            max = Math.max(max, cost);
            graph = test.union(graph, x, y);

        }
        System.out.println(sum - max);
    }
    public int[] union(int[] graph, int x, int y) {
        int nx = find(graph, x);
        int ny = find(graph, y);
        if(nx < ny) {
            graph[ny] = nx;
        } else {
            graph[nx] = ny;
        }
        return graph;
    }

    public int find(int[] graph, int x) {
        if(graph[x] != x) {
            return graph[x] = find(graph, graph[x]);
        }
        return graph[x];
    }
}

class Node implements Comparable<Node> {
    int x;
    int y;
    int cost;

    Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return this.cost - other.cost;
    }
}