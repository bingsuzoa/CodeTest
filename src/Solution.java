
import java.util.*;

public class Solution {
    static boolean answer = false;
    static int m, n;
    static List<int[]> list;

    public static void main(String[] args) {
        int[][] key = {{1, 1}, {1, 1}};
        int[][] lock = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Solution test = new Solution();
        System.out.println(test.solution(key, lock));
    }

    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lock[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }

        int[][] key_90 = rotate(key);
        int[][] key_180 = rotate(key_90);
        int[][] key_270 = rotate(key_180);
        List<int[][]> keyList = new ArrayList<>(4);
        keyList.add(key);
        keyList.add(key_90);
        keyList.add(key_180);
        keyList.add(key_270);

        for(int i = 0; i < keyList.size(); i++) {
            for(int j = m-1; j >= 0; j--) {
                if(move(keyList.get(i), lock, j, m-1, 0,0)) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }

    public boolean move(int[][] key, int[][] lock, int key_x, int key_y, int lock_x, int lock_y) {
        int matchCount = 0;

        if(key_y < 0 || lock_y >= n) {
            return false;
        }

        if (key_y > 0) {
            int count = 0;
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock[m - 1 - i][count] == 1 && key[i][j] == 1
                            || lock[m - 1 - i][count] == 0 && key[i][j] == 0) {
                        move(key, lock, key_x, key_y - 1, lock_x, lock_y);
                    }
                    if (lock[m - 1 - i][count] == 0) {
                        for(int k = 0; k < list.size(); k++) {
                            int[] arr = list.get(k);
                            if(m-1-i == arr[0] && count == arr[1]) {
                                count++;
                                matchCount++;
                            }
                        }
                    }
                }
            }
        }
        else {
            int twoCount = 0;
            for(int i = key_x; i < m; i++) {
                for(int j = key_y; j < m; j++) {
                    if (lock[m - 1 - i][lock_y + twoCount] == 1 && key[i][j] == 1
                            || lock[m - 1 - i][lock_y + twoCount] == 0 && key[i][j] == 0) {
                        move(key, lock, key_x, key_y, lock_x, lock_y + 1);
                    }
                }
            }
        }

        if(matchCount == list.size()) return true;
        return false;
    }

    public int[][] rotate(int[][] key) {
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = m - 1; j >= 0; j--) {
                newKey[i][m - 1 - j] = key[j][i];
            }
        }
        return newKey;
    }
}