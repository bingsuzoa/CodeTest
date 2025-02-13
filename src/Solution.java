import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Solution {

    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] input = {{1,2,2},{1,3,3},{1,4,1},{1,5,10},{2,4,2}, {3,4,1},{3,5,1},{4,5,3},{3,5,10}, {3,1,8},{1,4,2},{5,1,7}
                        ,{3,4,2},{5,2,4}};
        int[][] graph = test.getGraph(input);
        Stack<Node> stack = test.getStack(graph);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            int x = node.x;
            int y = node.y;

            for(int i = 1; i < graph[0].length; i++) {
                if(x == i)  {
                    graph[x][i] = 0;
                }
                if(graph[y][i] == 100001) {
                    continue;
                }
                graph[x][i] = Math.min(graph[x][i], graph[x][y] + graph[y][i]);
            }
        }

        for(int i = 1; i < graph.length; i++) {
            for(int j = 1; j < graph[0].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

    }

    public int[][] getGraph(int[][] input) {
        int[][] graph = new int[6][6];

        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[0].length; j++) {
                graph[i][j] = 100001;
            }
        }
        for(int i = 0; i < input.length; i++) {
            int x = input[i][0];
            int y = input[i][1];
            int value = input[i][2];
            if(graph[x][y] == 0) {
                graph[x][y] = value;
            } else {
                graph[x][y] = Math.min(graph[x][y], value);
            }
        }
        return graph;
    }

    public Stack<Node> getStack(int[][] graph) {
        Stack<Node> stack = new Stack<>();
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[0].length; j++) {
                if(graph[i][j] != 100001) {
                    stack.add(new Node(i,j));
                }
            }
        }
        return stack;
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