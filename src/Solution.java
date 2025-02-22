
class Solution {

    public static void main(String[] args) {
        Solution test = new Solution();
        String[] board = {"O.X", ".O.", "..X"};
        test.solution(board);
    }

    public int solution(String[] board) {
        String[][] graph = split(board);

        int o_count = 0;
        int x_count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (graph[i][j].equals("O")) {
                    o_count++;
                } else if (graph[i][j].equals("X")) {
                    x_count++;
                }
            }
        }

        if (o_count < 3 && x_count < 3) {
            if (o_count == x_count + 1 || o_count == x_count) {
                return 1;
            }
            return 0;
        }

        if (x_count > o_count || o_count - x_count >= 2) {
            return 0;
        }

        if(answer(graph).equals("O")) {
            if(o_count == 3 && x_count == 2) {
                return 1;
            }
        }
        else if(answer(graph).equals("X")) {
            if(o_count == 3 && x_count == 3) {
                return 1;
            }
        }
        return 0;
    }

    public String answer(String[][] board) {

        for (int i = 0; i < board.length; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }
        return "";
    }

    public String[][] split(String[] board) {
        String[][] arr = new String[board.length][board[0].length()];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (j == arr[0].length - 1) {
                    arr[i][j] = board[i].substring(j);
                } else {
                    arr[i][j] = board[i].substring(j, j + 1);
                }
            }
        }
        return arr;
    }
}