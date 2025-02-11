package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Test250211 {
    private static int m, n;
    private static final int[] dx = {1,-1,0,0};
    private static final int[] dy = {0,0,1,-1};
    private static boolean changeLock = false;
    private static boolean[][] visited;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Test250211 test = new Test250211();
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(test.solution(board));
    }

    public int solution(String[] board) {
        m = board.length;
        n = board[0].length();
        char[][] reBoard = getReBoard(board);

        visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        return find(reBoard, 0, n-1, -1, changeLock, 0);
    }

    int answer = -1;
    public int find(char[][] reBoard, int i, int j, int index, boolean changeLock, int sum) {
        if(i >= m || j >= n || i <= -1 || j <= -1) {
            return 0;
        }

        visited[i][j] = true;
        if(allBlock(reBoard, i, j)) {
            return 0;
        }

        if(!changeLock) {
            if(index == 0 || index == 1) {
                find(reBoard, i + dx[2], j + dy[2], 2, true, sum + 1);
                find(reBoard, i + dx[3], j + dy[3], 3, true, sum + 1);
            }
            else if(index == 2 || index == 3) {
                find(reBoard, i + dx[0], j + dy[0], 0, true, sum + 1);
                find(reBoard, i + dx[1], j + dy[1], 1, true, sum + 1);
            } else {
                for(int k = 0; k < 4; k++) {
                    find(reBoard, i+dx[k], j+dy[k], k, true, sum + 1);
                }
            }
        }
        else {
            if(reBoard[i][j] == 'D') {
                if(reBoard[i-dx[index]][j-dy[index]] == 'G') {
                    min = Math.min(min, sum);
                    answer = min;
                    return min;
                }
                find(reBoard, i-dx[index], j-dy[index], index, false, sum);
            }
            else if((i== m-1 || i == 0) && (j==n-1 || j == 0)) {
                if(reBoard[i][j] == 'G') {
                    min = Math.min(min, sum);
                    answer = min;
                    return min;
                }
                find(reBoard, i, j, index, false, sum);
            }
            find(reBoard, i+dx[index], j+dy[index], index, true, sum);
        }
        return answer;
    }
    int count = 0;
    public boolean allBlock(char[][] reBoard, int i, int j) {
        boolean allBlock = false;
        Queue<Node11> queue = new LinkedList<>();

        for(int k = 0; k < 4; k++) {
            queue.add(new Node11(i+dx[k], j+dy[k]));
        }

        while(!queue.isEmpty()) {
            Node11 node = queue.poll();
            int x = node.x;
            int y = node.y;
            if(x >= m || x <= -1 || y >= n || y <= -1) {
                count ++;
            }
            else if(reBoard[x][y] == 'D' || visited[x][y]){
                count ++;
            }
        }
        if(count >= 4) {
            allBlock = true;
        }
        count = 0;
        return allBlock;
    }

    public char[][] getReBoard(String[] board) {
        char[][] reBoard = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                reBoard[i][j] = board[i].charAt(j);
            }
        }
        return reBoard;
    }


}
class Node11 {
    int x;
    int y;

    Node11(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
