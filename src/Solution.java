import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] graph = {{},{0, 1, 0, 1, 1}, {1, 0, 1, 1, 0}, {0, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}};
    static int[] plan = {2, 3, 4, 3};

    public static void main(String[] args) {
        Solution test = new Solution();
        int[] parent = new int[6];
        for(int i = 0; i < 6; i++) {
            parent[i] = i;
        }

        for(int i = 1; i < parent.length; i++) {
            int index = parent[i];

            for(int j = 1; j < graph[index].length; j++) {
                if(graph[index][j] != 0) {
                    parent = test.union(parent, index, j);
                }
            }
        }

        boolean possible = true;
        for(int i = 0; i < plan.length - 1; i++) {
            if(parent[plan[i]] != parent[plan[i+1]]) {
                possible = false;
            }
        }
        if(possible) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public int[] union(int[] parent, int x, int y) {
        x = findParent(parent, x);
        y = findParent(parent, y);

        if(x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
        return parent;
    }

    public int findParent(int[] parent, int x) {
        if(parent[x] != x) {
            findParent(parent, parent[x]);
        }
        return parent[x];
    }
}