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
        int answer = Integer.MAX_VALUE;
        int[][] tired = new int[3][3];

        for(int i = 0; i < tired.length; i++) {
            for(int j = 0; j < tired[i].length; j++) {
                tired[i][j] = 1;
            }
        }
        tired[1][0] = 5;
        tired[2][0] = 25;
        tired[2][1] = 5;

        Queue<int[][]> changedPicks = makeRandomPicks(picks);
        while(!changedPicks.isEmpty()) {
            Queue<Integer> changedMinerals = stringToInt(minerals);
            int[][] newPicks = changedPicks.poll();
            int sum = 0;
            for(int i = 0; i < newPicks.length; i++) {
                int count = newPicks[i][1] * 5;
                while(count > 0 && !changedMinerals.isEmpty()) {
                    sum += tired[newPicks[i][0]][changedMinerals.poll()];
                    count--;
                }
            }
            if(answer > sum) {
                answer = sum;
            }
        }
        return answer;
    }

    public Queue<Integer> stringToInt(String[] minerals) {
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

    public Queue<int[][]> makeRandomPicks(int[] picks) {
        Queue<int[][]> queue = new LinkedList<>();

        int[] diamond = {0, picks[0]};
        int[] iron = {1, picks[1]};
        int[] stone = {2, picks[2]};

        queue.add(new int[][] {diamond, iron, stone});
        queue.add(new int[][] {diamond, stone, iron});
        queue.add(new int[][] {iron, stone, diamond});
        queue.add(new int[][] {iron, diamond, stone});
        queue.add(new int[][] {stone, diamond, iron});
        queue.add(new int[][] {stone, iron, diamond});

        return queue;
    }
}
