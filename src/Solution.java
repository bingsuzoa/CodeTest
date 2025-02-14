import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        List<ArrayList<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= c; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < r; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end,count));
        }

        int[] city = new int[c+1];
        for(int i = 0; i < city.length; i++) {
            city[i] = Integer.MAX_VALUE;
        }

        city[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int index = node.index;
            int distance = node.distance;

            for(int i = 0; i < graph.get(index).size(); i++) {
                Node subNode = graph.get(index).get(i);
                city[subNode.index] = Math.min(city[subNode.index], distance + subNode.distance);
                pq.add(new Node(subNode.index, subNode.distance));
            }
        }

        int maxCount = 0;
        int maxCity = 0;
        for(int i = 0; i < city.length; i++) {
            if(city[i] != Integer.MAX_VALUE) {
                maxCount = Math.max(maxCount, city[i]);
                maxCity++;
            }
        }
        System.out.println(maxCity-1 + " " + maxCount);
    }
}

class Node implements Comparable<Node> {
    int index;
    int distance;

    Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

