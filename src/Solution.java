
import java.util.*;

public class Solution {
    static boolean answer = false;
    static int m, n;
    static List<int[]> list;
    static Queue<int[]> queue;

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

        queue = new LinkedList<>();
        for (int i = 0; i < keyList.size(); i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (move(keyList.get(i), lock)) {
                    answer = true;
                    break;
                }
            }
        }
        return answer;

    }
    public boolean move(int[][] key, int[][] lock) {
        queue.add(new int[]{m - 1, m - 1, 0, 0});
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            int key_x = arr[0];
            int key_y = arr[1];
            int lock_x = arr[2];
            int lock_y = arr[3];

            if(lock_y >= n) {
                key_x --;
                key_y = m-1;
                lock_x = 0;
                lock_y = 0;
            }

            if(key_x <= -n) {
                continue;
            }

            if(key_x >= 0) {
                List<int[]> result = move_key_x_Positive(key, lock, key_x, key_y, lock_x, lock_y);
                if(judge(result)) {
                    answer = true;
                    break;
                }
            }
            List<int[]> result = move_key_x_negative(key, lock, key_x, key_y, lock_x, lock_y);
            if(judge(result)) {
                answer = true;
                break;
            }

        }
        return answer;
    }

    public List<int[]> move_key_x_negative(int[][] key, int[][] lock, int key_x, int key_y, int lock_x, int lock_y) {
        List<int[]> matchCount = new ArrayList<>();
        if(key_y > 0) {
            int count = 0;
            for (int i = -key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock[i][count] == 1 && key[i][j] == 1
                            || lock[i][count] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
                        break;
                    }
                    if (lock[i][count] == 0) {
                        matchCount.add(new int[]{i, count});
                    }
                }
            }
        } else {
            for (int i = -key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock_y + j >= n) {
                        continue;
                    }
                    if (lock[i][lock_y + j] == 1 && key[i][j] == 1
                            || lock[i][lock_y + j] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
                    }
                    if (lock[i][lock_y + j] == 0) {
                        matchCount.add(new int[]{i, lock_y + j});
                    }
                }
            }
        }
        return matchCount;
    }

    public List<int[]> move_key_x_Positive(int[][] key, int[][] lock, int key_x, int key_y, int lock_x, int lock_y) {
        List<int[]> matchCount = new ArrayList<>();

        if(key_y > 0) {
            int count = 0;
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock[i - key_x][count] == 1 && key[i][j] == 1
                            || lock[i - key_x][count] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
                        return matchCount;
                    }
                    if (lock[i - key_x][count] == 0) {
                        matchCount.add(new int[]{i - key_x, count});
                    }
                }
            }
        } else {
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock_y + j >= n) {
                        continue;
                    }
                    if (lock[i - key_x][lock_y + j] == 1 && key[i][j] == 1
                            || lock[i - key_x][lock_y + j] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
                        return matchCount;
                    }
                    if (lock[i - key_x][lock_y + j] == 0) {
                        matchCount.add(new int[]{i - key_x, lock_y + j});
                    }
                }
            }
        }
        return matchCount;
    }

    public boolean judge(List<int[]> result) {
        if(result.isEmpty()) {
            return false;
        }
        if(list.size() != result.size()) {
            return false;
        }
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < result.size(); j++) {
                if(list.get(i)[0] == result.get(j)[0] && list.get(i)[1] == result.get(j)[1]) {
                    count++;
                    break;
                }
            }
        }
        if(count == list.size()) {
            return true;
        }
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