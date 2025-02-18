import java.util.*;

public class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        int[][] key_90 = rotate(key);
        int[][] key_180 = rotate(key_90);
        int[][] key_270 = rotate(key_180);

        return answer;
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