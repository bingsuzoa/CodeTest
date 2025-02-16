import java.io.*;
import java.util.*;

public class Solution {
    static int[] graph;

    public static void main(String[] args) throws IOException {
        Solution test = new Solution();
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        int p = sc.nextInt();
        int[] order = new int[p];
        for(int i = 0; i < order.length; i++) {
            order[i] = sc.nextInt();
        }

        graph = new int[g+1];
        for(int i = 0; i < g+1; i++) {
            graph[i] = i;
        }

        int count = 0;
        for(int i = 0; i < order.length; i++) {
            int plane = order[i];
            if(test.find(graph, plane) == 0) {
                break;
            }
            plane = test.find(graph, plane);
            graph = test.union(graph, plane-1, plane);
            count++;

        }
        System.out.println(count);
    }

    public int find(int[] graph, int plane) {
        if(graph[plane] != plane) {
            return find(graph, graph[plane]);
        }
        return graph[plane];
    }

    public int[] union(int[] graph, int left, int right) {
        if(left < right) {
            graph[right] = left;
        } else {
            graph[left] = right;
        }
        return graph;
    }
}