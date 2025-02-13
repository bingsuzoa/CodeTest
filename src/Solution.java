import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[m][n];
        Node[][] f = new Node[m][n];
        String input2 = br.readLine();
        st = new StringTokenizer(input2);
        while(st.hasMoreTokens()) {
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        for(int x = 0; x < m; x++) {
            f[x][0] = new Node(x, 0);
            for(int i = 1; i < n; i++) {
                Node node = f[x][i-1];
                if(node.x == 0) {
                    int max = Math.max(graph[node.x][node.y+1], graph[node.x+1][node.y+1]);
                    if(graph[node.x][node.y+1] == max) {
                        f[x][i] = new Node(node.x, node.y+1);
                    }
                    if(graph[node.x+1][node.y+1] == max) {
                        f[x][i] = new Node(node.x+1, node.y+1);
                    }
                }
                else if(node.x == m-1) {
                    int max = Math.max(graph[node.x][node.y+1], graph[node.x-1][node.y+1]);
                    if(graph[node.x][node.y+1] == max) {
                        f[x][i] = new Node(node.x, node.y+1);
                    }
                    if(graph[node.x-1][node.y+1] == max) {
                        f[x][i] = new Node(node.x-1, node.y+1);
                    }
                }
                else {
                    int max = graph[node.x][node.y+1];
                    max = Math.max(max, graph[node.x+1][node.y+1]);
                    max = Math.max(max, graph[node.x-1][node.y+1]);
                    if(graph[node.x][node.y+1] == max) {
                        f[x][i] = new Node(node.x, node.y+1);
                    }
                    if(graph[node.x+1][node.y+1] == max) {
                        f[x][i] = new Node(node.x+1, node.y+1);
                    }
                    if(graph[node.x-1][node.y+1] == max) {
                        f[x][i] = new Node(node.x-1, node.y+1);
                    }
                }
            }
        }

        int max = 0;
        for(int i = 0; i < m; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                Node node = f[i][j];
                sum += graph[node.x][node.y];
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
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
