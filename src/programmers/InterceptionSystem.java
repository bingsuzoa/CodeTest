package programmers;

import java.util.*;

public class InterceptionSystem {
//    private static int[][] targets = {{1,7},{3,4},{3,8},{6,20},{8,9},{12,22},{23,24}};
    private static int[][] targets = {{1,4},{2,3},{5,12},{10,11},{11,13}};

    public static void main(String[] args) {
        InterceptionSystem test = new InterceptionSystem();
        System.out.println(test.solution(targets));
    }

    public int solution(int[][] targets) {
        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;

        for(int i = 0; i < targets.length; i++) {
            if(!map.containsKey(targets[i][0])) {
                map.put(targets[i][0], targets[i][1]);
            } else {
                int min = map.get(targets[i][0]);
                min = Math.min(min, targets[i][1]);
                map.replace(targets[i][0], min);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int key : map.keySet()) {
            list.add(key);
        }
        Collections.sort(list);

        int index_bef = 0;
        while(index_bef < list.get(list.size()-1)) {
            int start = list.get(index_bef);
            int end = map.get(start);
            int index_next = getIndex(list, index_bef, end, end);
            if(index_next - index_bef >= 2) {
                for(int i = index_bef + 1; i < index_next; i++) {
                    if(map.get(i) <= list.get(i+1)) {
                        answer++;
                        index_bef = index_next;
                    }
                }
            }
            else if(index_next == list.size() -1) {
                answer++;
            }
        }
        return answer+1;
    }

    public int getIndex(List<Integer> list, int index_bef, int end, int target) {
        int result = 0;
        while(index_bef <= end) {
            int mid = (index_bef + end) / 2;
            if(target > list.get(mid)) {
                index_bef = mid + 1;
            }
            else if(target == list.get(mid)) {
                result = mid;
                return result;

            } else {
                if(target > list.get(mid -1)) {
                    result = mid;
                    return result;
                } else {
                    end = mid - 1;
                }
            }
        }
        return list.size() - 1;
    }

}
