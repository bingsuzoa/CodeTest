import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int m, n;
    static char[][] graph;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        Solution test = new Solution();
        System.out.println(test.solution(board));
    }

    public int solution(String[] board) {
        graph = getBoard(board);
        visited = new boolean[m][n];

        queue.add(new Node(0, n - 1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int sum = node.sum;

            if (x >= m || y >= n || x < 0 || y < 0) {
                continue;
            }
            if (graph[x][y] == 'G') {
                min = Math.min(min, sum);
                break;
            }
            if(visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            for (int i = 0; i < 4; i++) {
                go(x, y, sum + 1, i);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public void go(int x, int y, int sum, int index) {
        if (index == 0) {
            for (int i = x + 1; i < m; i++) {
                if (graph[i][y] == 'D') {
                    queue.add(new Node(i - 1, y, sum));
                    break;
                }
                if(i == m-1) {
                    queue.add(new Node(i, y, sum));
                }
            }
        }
        if (index == 1) {
            for (int i = x - 1; i >= 0; i--) {
                if (graph[i][y] == 'D') {
                    queue.add(new Node(i + 1, y, sum));
                    break;
                }
                if(i == 0) {
                    queue.add(new Node(0, y, sum));
                }
            }
        }
        if (index == 2) {
            for (int i = y + 1; i < n; i++) {
                if (graph[x][i] == 'D') {
                    queue.add(new Node(x, i - 1, sum));
                    break;
                }
                if(i == n-1) {
                    queue.add(new Node(x, n - 1, sum));
                }
            }
        }
        if (index == 3) {
            for (int i = y - 1; i >= 0; i--) {
                if (graph[x][i] == 'D') {
                    queue.add(new Node(x, i + 1, sum));
                    break;
                }
                if(i == 0) {
                    queue.add(new Node(x, 0, sum));
                }
            }
        }
    }

    public char[][] getBoard(String[] board) {
        m = board.length;
        n = board[0].length();
        char[][] graph = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = board[i].charAt(j);
            }
        }
        return graph;
    }


    class Node {
        int x;
        int y;
        int sum;

        Node(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

    }
}