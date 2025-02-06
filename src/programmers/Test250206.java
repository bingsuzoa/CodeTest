package programmers;

import java.util.*;

public class Test250206 {

    public static void main(String[] args) {
        Test250206 test = new Test250206();
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(test.solution(picks, minerals));
    }

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] tired = new int[3][3];

        for(int i = 0; i < tired.length; i++) {
            for(int j = 0; j < tired[i].length; j++) {
                tired[i][j] = 1;
            }
        }
        tired[1][0] = 5;
        tired[2][0] = 25;
        tired[2][1] = 5;

        Queue<Integer> changedMinerals = stringToInt(minerals);
        for(int i = 0; i < picks.length; i++) {
            int count = picks[i] * 5;
            while(count > 0 && !changedMinerals.isEmpty()) {
                answer += tired[i][changedMinerals.poll()];
                count--;
            }
        }
        return answer;
    }

    public Queue stringToInt(String[] minerals) {
        Queue<Integer> changeMinerals = new LinkedList<>();

        for(String mineral : minerals) {
            if(mineral.equals("diamond")) {
                changeMinerals.add(0);
            }
            else if(mineral.equals("iron")) {
                changeMinerals.add(1);
            }
            else {
                changeMinerals.add(2);
            }
        }
        return changeMinerals;
    }
}
