import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    static int m,n;
    static char[][] graph;
    static int min = Integer.MAX_VALUE;
    static Queue<Node> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        String[] board = {".D.R", "....", ".G..", "...D"};
        Solution test = new Solution();
        System.out.println(test.solution(board));
    }

    public int solution(String[] board) {
        m = board.length;
        n = board[0].length();
        graph = getBoard(board);

        int[][] visited = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                visited[i][j] = -1;
            }
        }

        que.add(new Node(0, n-1, 0, visited));
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            int sum = node.sum;
            if(x >= m || y >= n || x < 0 || y < 0) {
                continue;
            }
            if(graph[x][y] == 'G') {
                min = Math.min(min, sum);
            } else {
                for(int i = 0; i < 4; i++) {
                    go(x, y, i, node.visited, sum+1);
                }
            }
        }
        if(min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public void go(int x, int y, int index, int[][] visited, int sum) {
        int[][] newVisited = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                newVisited[i][j] = visited[i][j];
            }
        }
        if(index == 0) {
            for(int i = x+1; i < m; i++) {
                if(newVisited[i][y] == index || newVisited[i][y] == 1 || graph[i][y] == 'R') {
                    break;
                }
                newVisited[i][y] = index;
                if(graph[i][y] == 'D') {
                    que.add(new Node(i-1, y, sum, newVisited));
                    break;
                }
                if(i == m-1) {
                    que.add(new Node(i, y, sum, newVisited));
                    break;
                }
            }
        }
        if(index == 1) {
            for(int i = x-1; i >= 0; i--) {
                if(newVisited[i][y] == index || newVisited[i][y] == 0 || graph[i][y] == 'R') {
                    break;
                }
                newVisited[i][y] = index;
                if(graph[i][y] == 'D') {
                    que.add(new Node(i+1, y, sum, newVisited));
                    break;
                }
                if(i == 0) {
                    que.add(new Node(i, y, sum, newVisited));
                    break;
                }
            }
        }
        if(index == 2) {
            for(int i = y+1; i < n; i++) {
                if(newVisited[x][i] == index || newVisited[x][i] == 3 || graph[x][i] == 'R') {
                    break;
                }
                newVisited[x][i] = index;
                if(graph[x][i] == 'D') {
                    que.add(new Node(x, i-1, sum, newVisited));
                    break;
                }
                if(i == n-1) {
                    que.add(new Node(x, i, sum, newVisited));
                    break;
                }
            }
        }
        if(index == 3) {
            for(int i = y-1; i >= 0; i--) {
                if(newVisited[x][i] == index || newVisited[x][i] == 2  || graph[x][i] == 'R') {
                    break;
                }
                newVisited[x][i] = index;
                if(graph[x][i] == 'D') {
                    que.add(new Node(x, i+1, sum, newVisited));
                    break;
                }
                if(i == 0) {
                    que.add(new Node(x, i, sum, newVisited));
                    break;
                }
            }
        }
    }

    public char[][] getBoard(String[] board) {
        char[][] graph = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                graph[i][j] = board[i].charAt(j);
            }
        }
        return graph;
    }
}

class Node {
    int x;
    int y;
    int sum;
    int[][] visited;

    Node(int x, int y, int sum, int[][] visited) {
        this.x = x;
        this.y = y;
        this.sum = sum;
        this.visited = visited;
    }
}
