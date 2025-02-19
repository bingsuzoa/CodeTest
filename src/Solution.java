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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int[][] nowKey : keyList) {
                    if (move_fromLU(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                    if (move_fromRD(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                    if (move_fromRU(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                    if (move_fromLD(nowKey, lock, i, j)) {
                        answer = true;
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public boolean move_fromLU(int[][] key, int[][] lock, int lock_x, int lock_y) {
        boolean[] isUnLock = new boolean[list.size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (j + lock_y >= n || i + lock_x >= n) continue;
                if (lock[i + lock_x][j + lock_y] == 1 && key[i][j] == 1) return false;
                if (lock[i + lock_x][j + lock_y] == 0) {
                    if (key[i][j] == 0) {
                        return false;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        int[] arr = list.get(k);
                        if (arr[0] == i + lock_x && arr[1] == j + lock_y) {
                            isUnLock[k] = true;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (boolean unLock : isUnLock) {
            if (unLock) count++;
        }
        if (count == list.size()) return true;
        return false;
    }

    public boolean move_fromRU(int[][] key, int[][] lock, int lock_x, int lock_y) {
        boolean[] isUnLock = new boolean[list.size()];
        for (int i = 0; i < m; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (j - m + 1 < 0 || j - m + 1 + lock_y >= n || i + lock_x >= n) continue;
                if (lock[i + lock_x][j - m + 1 + lock_y] == 1 && key[i][j] == 1) return false;
                if (lock[i + lock_x][j - m + 1 + lock_y] == 0) {
                    if (key[i][j] == 0) {
                        return false;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        int[] arr = list.get(k);
                        if (arr[0] == i + lock_x && arr[1] == j - m + 1 + lock_y) {
                            isUnLock[k] = true;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (boolean unLock : isUnLock) {
            if (unLock) count++;
        }
        if (count == list.size()) return true;
        return false;
    }

    public boolean move_fromRD(int[][] key, int[][] lock, int lock_x, int lock_y) {
        boolean[] isUnLock = new boolean[list.size()];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i - lock_x < 0 || j - lock_y < 0) continue;
                if (lock[i - lock_x][j - lock_y] == 1 && key[i][j] == 1) return false;
                if (lock[i - lock_x][j - lock_y] == 0) {
                    if (key[i][j] == 0) {
                        return false;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        int[] arr = list.get(k);
                        if (arr[0] == i - lock_x && arr[1] == j - lock_y) {
                            isUnLock[k] = true;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (boolean unLock : isUnLock) {
            if (unLock) count++;
        }
        if (count == list.size()) return true;
        return false;
    }

    public boolean move_fromLD(int[][] key, int[][] lock, int lock_x, int lock_y) {
        boolean[] isUnLock = new boolean[list.size()];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (j - m + 1 < 0 || j - m + 1 + lock_x >= n || j + lock_y >= n) continue;
                if (lock[j - m + 1 + lock_x][j + lock_y] == 1 && key[i][j] == 1) return false;
                if (lock[j - m + 1 + lock_x][j + lock_y] == 0) {
                    if (key[i][j] == 0) {
                        return false;
                    }
                    for (int k = 0; k < list.size(); k++) {
                        int[] arr = list.get(k);
                        if (arr[0] == j - m + 1 + lock_x && arr[1] == j + lock_y) {
                            isUnLock[k] = true;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (boolean unLock : isUnLock) {
            if (unLock) count++;
        }
        if (count == list.size()) return true;
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