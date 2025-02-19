import java.util.*;

public class Solution {
    static boolean answer = false;
    static int m, n;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        Solution test = new Solution();
        System.out.println(test.solution(key, lock));
    }

    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;

        int[][] key_90 = rotate(key);
        int[][] key_180 = rotate(key_90);
        int[][] key_270 = rotate(key_180);
        List<int[][]> keyList = new ArrayList<>(4);
        keyList.add(key);
        keyList.add(key_90);
        keyList.add(key_180);
        keyList.add(key_270);


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int[][] nowKey : keyList) {
                    if (move_rightDown(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                    if (move_leftUp(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public boolean move_rightDown(int[][] key, int[][] lock, int lock_x, int lock_y) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (j + lock_y >= n || i + lock_x >= n) continue;
                if (lock[i + lock_x][j + lock_y] == 1 && key[i][j] == 1) return false;
                if(lock[i+lock_x][j + lock_y] == 0) {
                    if(key[i][j] == 0) {
                        return false;
                    }
                    lock[i+lock_x][j + lock_y] = key[i][j];
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean move_leftUp(int[][] key, int[][] lock, int lock_x, int lock_y) {
        for (int i = m - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i - lock_x < 0 || j - lock_y < 0) continue;
                if (lock[i - lock_x][j - lock_y] == 1 && key[i][j] == 1) return false;
                if (lock[i - lock_x][j - lock_y] == 0) {
                    if(key[i][j] == 0) {
                        return false;
                    }
                    lock[i - lock_x][j - lock_y] = key[i][j];
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lock[i][j] == 0) return false;
            }
        }
        return true;
    }

    public int[][] rotate(int[][] key) {
        int m = key.length;
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = m - 1; j >= 0; j--) {
                newKey[i][m - 1 - j] = key[j][i];
            }
        }
        return newKey;
    }

}