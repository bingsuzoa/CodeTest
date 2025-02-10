package programmers;


public class Test250210 {
    private static int m, n;
    private static final int[] dx = {1,-1,0,0};
    private static final int[] dy = {0,0,1,-1};
    private static boolean changeLock = false;

    public static void main(String[] args) {
        Test250210 test = new Test250210();
        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(test.solution(board));

    }

    public int solution(String[] board) {
        m = board.length;
        n = board[0].length();
        char[][] reBoard = getReBoard(board);

        int sum = find(reBoard, 0, n-1, -1, changeLock, 0);
        if(sum == 0) return -1;
        return sum;
    }

    public int find(char[][] reBoard, int i, int j, int index, boolean changeLock, int sum) {
        if(i >= m || j >= n || i <= -1 || j <= -1) {
            return 0;
        }


        if(!changeLock) {
            if(index == 0 || index == 1) {
                find(reBoard, i + dx[2], j + dy[2], 2, true, sum);
                find(reBoard, i + dx[3], j + dy[3], 3, true, sum);
            }
            else if(index == 2 || index == 3) {
                find(reBoard, i + dx[0], j + dy[0], 0, true, sum);
                find(reBoard, i + dx[1], j + dy[1], 1, true, sum);
            } else {
                for(int k = 0; k < 4; k++) {
                    find(reBoard, i + dx[k], j + dy[k], k, true, sum);
                }
            }
        }
        else {
            if(reBoard[i][j] == 'D') {
                if(reBoard[i-dx[index]][j-dy[index]] == 'G') {
                    return sum+1;
                }
                find(reBoard, i-dx[index], j-dy[index], index, false, sum+1);
            }
            else if((i== m-1 || i == 0) && (j==n-1 || j == 0)) {
                if(reBoard[i][j] == 'G') {
                    return sum +1;
                }
                find(reBoard, i, j, index, false, sum+1);
            } else {
                reBoard[i][j] = 'V';
                find(reBoard, i+dx[index], j+dy[index], index, true, sum);
            }
        }
        return 0;
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
