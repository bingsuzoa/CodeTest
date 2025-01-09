package programmers;

import java.util.*;

public class InterceptionSystem {
    private static int max;
    private static int min;
    private static List<Integer> list = new ArrayList<>();
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int maxValue;

    public static void main(String[] args) {
        int[][] targets = {{1,5},{3,20},{4,15},{5,8},{6,18}};
        InterceptionSystem test = new InterceptionSystem();
        System.out.println(test.solution(targets));
    }

    public int solution(int[][] targets) {
        int answer = 0;
        max = getMax(targets);
        min = getMin(targets);

        for(int i = 0; i < targets.length; i++) {
            if(!map.containsKey(targets[i][0])) {
                map.put(targets[i][0], new ArrayList());
                map.get(targets[i][0]).add(targets[i][1]);
            } else {
                map.get(targets[i][0]).add(targets[i][1]);
            }
        }

        for(Integer key : map.keySet()) {
            list.add(key);
            Collections.sort(map.get(key));
        }
        Collections.sort(list);

        int index = 0;
        int x = list.get(index);
        int y = map.get(x).get(0);

        while(true) {
            int[] result = getRange(x,y);
            x = result[0];
            maxValue = result[1];
            index = result[2];
            y = map.get(x).get(0);

            if(maxValue == max && index == list.size() - 1) {
                answer++;
                break;
            } else {
                answer++;
            }
        }

        return answer;
    }
    public int[] getRange(int x, int y) {
        int max_x = x;
        int min_y = y;
        int start = list.indexOf(x);
        for(int i = start; i < list.size(); i++) {
            if(list.get(i) > y) {
                break;
            }
            else if(list.get(i) >= x && list.get(i) < y && list.get(i) < min_y) {
                max_x = Math.max(max_x, list.get(i));
                List<Integer> tempList = map.get(list.get(i));
                min_y = Math.min(min_y, tempList.get(0));
                maxValue = Math.max(maxValue, tempList.get(tempList.size()-1));
            }
        }

        int index = list.indexOf(max_x);
        for(int i = index + 1; i < list.size(); i++) {
            if(list.get(i) >= min_y) {
                max_x = list.get(i);
                index = i;
                break;
            }
        }
        int[] range = {max_x, maxValue, index};
        return range;
    }
    public int getMax(int[][] targets) {
        int max = 0;
        for(int i = 0; i < targets.length; i++) {
            max = Math.max(max, targets[i][0]);
            max = Math.max(max, targets[i][1]);
        }
        return max;
    }

    public int getMin(int[][] targets) {
        int min = 100000001;
        for(int i = 0; i < targets.length; i++) {
            min = Math.min(min, targets[i][0]);
            min = Math.min(min, targets[i][1]);
        }
        return min;
    }
}
