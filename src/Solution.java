import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] graph = {{0,1,0,1,1}, {1,0,1,1,0},{0,1,0,0,0},{1,1,0,0,0},{1,0,0,0,0}};
    static int[] plan = {2,3,4,3};

    public static void main(String[] args) {
        Queue<Node> queue = new LinkedList<>();

        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] == 1) {
                    queue.add(new Node(i,j));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;

            for(int i = 0; i < graph[y].length; i++) {
                if(x == i) continue;
                if(graph[x][i] != 0) continue;

                graph[x][i] = graph[x][y] + graph[y][i];
            }
        }

        boolean possible = true;
        for(int i = 0; i < plan.length-1; i++) {
            int start = plan[i];
            int end = plan[i+1];
            if(graph[start][end] == 0) {
                possible = false;
            }
        }
        if(possible) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}