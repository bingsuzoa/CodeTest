package programmers;


import java.util.*;


public class Test9 {

    public static void main(String[] args) {
        String[][] plans = {{"korean", "12:20", "40"}, {"english", "12:40", "50"}, {"math", "14:00", "60"}};
        Test9 test = new Test9();
        String[] solution = test.solution(plans);
        for(String value : solution) {
            System.out.print(value + " ");
        }
    }
    public String[] solution(String[][] plans) {
        List<String> answerList = new ArrayList<>();
        int[][] startEndSubject = parsePlans(plans);
        List<Order> startTimeList = new ArrayList<>(1440);
        for(int i = 0; i < startEndSubject.length; i++) {
            Order startOrder = new Order(i, true);
            Order endOrder = new Order(i, false);
            startTimeList.add(startEndSubject[i][0], startOrder);
            startTimeList.add(startEndSubject[i][1], endOrder);
        }

        Stack<Integer> startTimeStack = new Stack<>();
        boolean stackOpenKey = false;
        int possibleMove = 0;
        int move = 0;
        int order = 0;
        for(int i = 0; i < startTimeList.size(); i++) {
            if(stackOpenKey) {
                while(startTimeList.get(possibleMove) == null || !startTimeList.get(possibleMove).isStartOrArrive()) {
                    move = startTimeStack.pop();
                    order = startTimeList.get(move).getOrder();
                    move++;
                    if(startTimeList.get(move).getOrder() == order) {
                        answerList.add(findSubject(plans, startEndSubject, move));
                    }
                    possibleMove++;
                }
                move--;
                Order newOrder = new Order(order,true);
                startTimeList.add(move, newOrder);
                startTimeStack.add(move);
            }
            else if(startTimeList.get(i).isStartOrArrive()) {
                startTimeStack.add(i);
            }
            else {
                if(startTimeList.get(i).getOrder() == startTimeList.get(startTimeStack.peek()).getOrder()) {
                    startTimeStack.pop();
                    answerList.add(plans[i][0]);
                    stackOpenKey = true;
                    possibleMove = i + 1;
                }
            }
        }
        String[] answer = answerList.stream().toArray(String[]::new);
        return answer;
    }

    public int[][] parsePlans(String[][] plans) {
        int[][] hourToMinute = new int[plans.length][2];
        for (int i = 0; i < plans.length; i++) {
            hourToMinute[i][0] = hourToMinute(plans[i][1]); //시작시간
            hourToMinute[i][1] = hourToMinute[i][0] + hourToMinute(plans[i][2]); //종료시간
        }
        return hourToMinute;
    }

    public String findSubject(String[][] plans, int[][] startEndSubject, int move) {
        int index = Arrays.stream(startEndSubject)
                .filter(row -> row[1] == move)
                .mapToInt(row -> Arrays.asList(startEndSubject).indexOf(row))
                .findFirst()
                .orElse(-1);
        return plans[index][0];
    }

    public int hourToMinute(String time) {
        int stringToInt;
        if(time.length() > 2) {
            String[] splitTime = time.split(":");
            stringToInt = Integer.parseInt(splitTime[0]) * 60 + Integer.parseInt(splitTime[1]);
        } else {
            stringToInt = Integer.parseInt(time);
        }
        return stringToInt;
    }
}

class Order {
    int order;
    boolean startOrArrive;

    Order(int order, boolean startOrArrive) {
        this.order = order;
        this.startOrArrive = startOrArrive;
    }
    public int getOrder() {
        return this.order;
    }
    public boolean isStartOrArrive() {
        return this.startOrArrive;
    }
}
