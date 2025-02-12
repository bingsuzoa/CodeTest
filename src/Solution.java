import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int m, n;
    static Queue<Node> queue = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        Solution test = new Solution();
        System.out.println(test.solution(board));
    }

    public int solution(String[] board) {
        char[][] graph = getBoard(board);
        boolean[][] visited = new boolean[m][n];
        queue.add(new Node(0, n-1, 0, visited));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int sum = node.sum;

            if(x >= m || y >= n || x < 0 || y < 0) {
                continue;
            }
            if(graph[x][y] == 'G') {
                min = Math.min(min, sum);
                break;
            }

            go(graph, node, 0);
            go(graph, node, 1);
            go(graph, node, 2);
            go(graph, node, 3);
        }
        
        if(min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }

    public void go(char[][] graph, Node node, int index) {
        int x = node.x;
        int y = node.y;
        int sum = node.sum;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                visited[i][j] = node.visited[i][j];
            }
        }

        visited[x][y] = true;

        if(index == 2) {
            for(int i = y + 1; i <= n-1; i++) {
                int ny = i;
                if(visited[x][ny]) {
                    break;
                }
                visited[x][ny] = true;
                if(graph[x][ny] == 'D') {
                    queue.add(new Node(x, ny-1, sum+1, visited));
                    break;
                }
                if(ny == n-1) {
                    queue.add(new Node(x, ny, sum+1, visited));
                }
            }
        }
        if(index == 3) {
            for(int i = y - 1; i >= 0; i--) {
                int ny = i;
                if(visited[x][ny]) {
                    break;
                }
                visited[x][ny] = true;
                if(graph[x][ny] == 'D') {
                    queue.add(new Node(x, ny+1, sum+1, visited));
                    break;
                }
                if(ny == 0) {
                    queue.add(new Node(x, ny, sum+1, visited));
                }
            }
        }
        if(index == 0) {
            for(int i = x + 1; i <= m-1; i++) {
                int nx = i;
                if(visited[nx][y]) {
                    break;
                }
                visited[nx][y] = true;
                if(graph[nx][y] == 'D') {
                    queue.add(new Node(nx-1, y, sum+1, visited));
                    break;
                }
                if(nx == m-1) {
                    queue.add(new Node(nx, y, sum+1, visited));
                }
            }
        }
        if(index == 1) {
            for(int i = x - 1; i >= 0; i--) {
                int nx = i;
                if(visited[nx][y]) {
                    break;
                }
                visited[nx][y] = true;
                if(graph[nx][y] == 'D') {
                    queue.add(new Node(nx+1, y, sum+1, visited));
                    break;

                }
                if(nx == 0) {
                    queue.add(new Node(nx, y, sum+1, visited));
                }
            }
        }
    }

    public char[][] getBoard(String[] board) {
        m = board.length;
        n = board[0].length();
        char[][] newBoard = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                newBoard[i][j] = board[i].charAt(j);
            }
        }
        return newBoard;
    }
}

class Node {
    int x;
    int y;
    int sum;
    boolean[][] visited;

    Node(int x, int y, int sum, boolean[][] visited) {
        this.x = x;
        this.y = y;
        this.sum = sum;
        this.visited = visited;
    }
}
