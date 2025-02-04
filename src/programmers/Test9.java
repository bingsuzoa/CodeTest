package programmers;


import java.util.*;

public class Test9 {
    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        Test9 test = new Test9();
        String[] solution = test.solution(plans);
        for(String value : solution) {
            System.out.print(value + " ");
        }
    }
    public String[] solution(String[][] plans) {
        int[][] parsePlans = parsePlans(plans);
        Queue<Integer> subject = new LinkedList<>();
        List<String> endSubject = new ArrayList<>();
        String[] answer = new String[plans.length];

        for(int i = 0; i < parsePlans.length; i++) {
            subject.add(i);
        }

        Stack<Integer> ongoingTasks = new Stack<>();
        while (!subject.isEmpty()) {
            int index =  subject.poll();
            int subjectIndex = parsePlans[index][0];
            int end = parsePlans[index][2];
            if(index == parsePlans.length -1) {
                endSubject.add(plans[subjectIndex][0]);
            }
            for (int i = index + 1; i < parsePlans.length; i++) {
                if (parsePlans[i][1] < end) {
                    ongoingTasks.add(subjectIndex);
                    break;
                } else {
                    endSubject.add(plans[subjectIndex][0]);
                    break;
                }
            }
        }
        while (!ongoingTasks.isEmpty()) {
            endSubject.add(plans[ongoingTasks.pop()][0]);
        }

        for (int i = 0; i < plans.length; i++) {
            answer[i] = endSubject.get(i);
        }

        return answer;
    }

    public int[][] parsePlans(String[][] plans) {
        int[][] hourToMinute = new int[plans.length][3];
        for (int i = 0; i < plans.length; i++) {
            hourToMinute[i][0] = i;
            hourToMinute[i][1] = hourToMinute(plans[i][1], plans[i][2])[0];
            hourToMinute[i][2] = hourToMinute(plans[i][1], plans[i][2])[1];
        }
        Arrays.sort(hourToMinute, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        return hourToMinute;
    }

    public int[] hourToMinute(String startTime, String duration) {
        String[] splitTime = startTime.split(":");
        int[] startAndEndTime = new int[2];
        startAndEndTime[0] = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        startAndEndTime[1] = startAndEndTime[0] + Integer.parseInt(duration);
        return startAndEndTime;
    }
}
