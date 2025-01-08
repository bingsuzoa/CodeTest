package programmers;

import java.util.*;

public class InterceptionSystem {
    private static int max;
    private static int min;
    private static List<List<Integer>> list = new ArrayList<>(max+1);

    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        InterceptionSystem test = new InterceptionSystem();
        System.out.println(test.solution(targets));
    }

    public int solution(int[][] targets) {
        int answer = 0;
        max = getMax(targets);
        min = getMin(targets);

        for(int i = 0; i < max + 1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < targets.length; i++) {
            list.get(targets[i][0]).add(targets[i][1]);
        }

        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).isEmpty()) {
                continue;
            } else {
                Collections.sort(list.get(i));
            }
        }

        int maxValue = -1;
        int minIndex = min;
        while(maxValue != max) {
            List<Integer> tempList = list.get(minIndex);
            int maxIndex = tempList.get(0);

            for(int i = minIndex + 1; i < maxIndex; i++) {
                if(!list.get(i).isEmpty()) {
                    minIndex = i;
                    break;
                }
            }

            int minValue = 100000001;
            for(int i = minIndex; i < maxIndex; i++) {
                if(list.get(i).isEmpty()) {
                    continue;
                } else {
                    for(int j = 0; j < list.get(i).size(); j++) {
                        minValue = Math.min(minValue, list.get(i).get(j));
                        maxValue = Math.max(maxValue, list.get(i).get(j));
                    }
                }
            }
            if(minValue < maxIndex) {
                maxIndex = minValue;
            }
            answer ++;
            minIndex = maxIndex;
        }
        return answer;
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
