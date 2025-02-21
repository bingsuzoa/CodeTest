
import java.util.*;

public class Solution {
    static boolean answer = false;
    static int m, n;
    static List<int[]> list;
    static Queue<int[]> queue;

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
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
        if(list.size() == n * n) return false;
        if(list.isEmpty()) return true;

        list.sort((o1, o2) -> {
           if(o1[0] == o2[0]) return o1[1] - o2[1];
           return o1[0] - o2[0];
        });

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
            if (move(keyList.get(i), lock)) {
                answer = true;
                break;
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
                if(key_x > 0) {
                    key_x --;
                } else {
                    lock_x ++;
                }
                key_y = m-1;
                lock_y = 0;
            }

            if(lock_x >= n) {
                continue;
            }

            if(key_x > 0) {
                answer = move_key_x_Positive(key, lock, key_x, key_y, lock_x, lock_y);
                if(answer) {
                    break;
                }
            } else {
                answer = move_key_x_negative(key, lock, key_x, key_y, lock_x, lock_y);
                if(answer) {
                    break;
                }
            }

        }
        return answer;
    }

    public boolean move_key_x_negative(int[][] key, int[][] lock, int key_x, int key_y, int lock_x, int lock_y) {
        List<int[]> matchCount = new ArrayList<>();
        if(key_y > 0) {
            int count = 0;
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock[lock_x][count] == 1 && key[i][j] == 1
                            || lock[lock_x][count] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
                        return false;
                    }
                    if (lock[lock_x][count] == 0) {
                        matchCount.add(new int[]{i, count});
                    }
                }
            }
            if(judge(matchCount)) return true;
            queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
        } else {
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock_y + j >= n) {
                        continue;
                    }
                    if (lock[lock_x][lock_y + j] == 1 && key[i][j] == 1
                            || lock[lock_x][lock_y + j] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
                        return false;
                    }
                    if (lock[lock_x][lock_y + j] == 0) {
                        matchCount.add(new int[]{i, lock_y + j});
                    }
                }
            }
            if(judge(matchCount)) return true;
            queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
        }
        return false;
    }

    public boolean move_key_x_Positive(int[][] key, int[][] lock, int key_x, int key_y, int lock_x, int lock_y) {
        List<int[]> matchCount = new ArrayList<>();

        if(key_y > 0) {
            int count = 0;
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock[i - key_x][count] == 1 && key[i][j] == 1
                            || lock[i - key_x][count] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
                        return false;
                    }
                    if (lock[i - key_x][count] == 0) {
                        matchCount.add(new int[]{i - key_x, count});
                    }
                }
            }
            if(judge(matchCount)) return true;
            queue.add(new int[]{key_x, key_y - 1, lock_x, lock_y});
        } else {
            for (int i = key_x; i < m; i++) {
                for (int j = key_y; j < m; j++) {
                    if (lock_y + j >= n) {
                        continue;
                    }
                    if (lock[i - key_x][lock_y + j] == 1 && key[i][j] == 1
                            || lock[i - key_x][lock_y + j] == 0 && key[i][j] == 0) {
                        queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
                        return false;
                    }
                    if (lock[i - key_x][lock_y + j] == 0) {
                        matchCount.add(new int[]{i - key_x, lock_y + j});
                    }
                }
            }
            if(judge(matchCount)) return true;
            queue.add(new int[]{key_x, key_y, lock_x, lock_y + 1});
        }
        return false;
    }

    public boolean judge(List<int[]> result) {
        result.sort((o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        if(result.isEmpty()) {
            return false;
        }
        if(list.size() != result.size()) {
            return false;
        }
        int count = 0;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)[0] == result.get(i)[0] && list.get(i)[1] == result.get(i)[1]) {
                count++;
                break;
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