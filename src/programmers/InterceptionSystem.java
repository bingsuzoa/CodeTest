package programmers;

import java.util.*;

public class InterceptionSystem {
//    private static int[][] targets = {{1,7},{3,4},{3,8},{6,20},{8,9},{12,22},{23,24}};
    private static int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};

    public static void main(String[] args) {
        InterceptionSystem test = new InterceptionSystem();
        System.out.println(test.solution(targets));
    }

    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1,o2) -> {
            if(o1[0]==o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int min = targets[0][1];
        for(int i = 0; i < targets.length -1; i++ ){
            if(min > targets[i+1][0]) {
                if(min > targets[i+1][1]) {
                    min = targets[i+1][1];
                }
            } else {
                answer++;
                min = targets[i+1][1];
            }
        }
        return answer + 1;
    }


}
