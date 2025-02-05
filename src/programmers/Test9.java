package programmers;


import java.util.*;


public class Test9 {

    public static void main(String[] args) {
        String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        Test9 test = new Test9();
        String[] solution = test.solution(plans);
        for(String value : solution) {
            System.out.print(value + " ");
        }
    }
    public String[] solution(String[][] plans) {
        Order[] orders = (Order[])changePlansToOrders(plans);
        List<String> answerList = new ArrayList<>();
        Stack<Order> stack = new Stack<>();
        for(int i = 0; i < orders.length -1; i++) {
            int necessaryTime = orders[i].duration;
            int realTakenTime = orders[i + 1].startTime - orders[i].startTime;
            int remainTime;
            if(realTakenTime >= necessaryTime) {
                answerList.add(orders[i].getSubject(plans));
                remainTime = realTakenTime - necessaryTime;
                while(remainTime > 0 && !stack.isEmpty()) {
                    Order subTask = stack.pop();
                    int remainTimeOfSubTask = subTask.duration;
                    if(remainTime >= remainTimeOfSubTask) {
                        answerList.add(subTask.getSubject(plans));
                        remainTime -= remainTimeOfSubTask;
                    } else {
                        remainTimeOfSubTask -= remainTime;
                        remainTime -= remainTimeOfSubTask;
                        stack.add(new Order(subTask.subjectIndex, subTask.startTime, remainTimeOfSubTask));
                    }
                }
            } else {
                necessaryTime -= realTakenTime;
                stack.add(new Order(orders[i].subjectIndex, orders[i].startTime, necessaryTime));
            }
        }
        answerList.add(orders[orders.length -1].getSubject(plans));
        while(!stack.isEmpty()) {
            answerList.add(stack.pop().getSubject(plans));
        }
        String[] answer = new String[plans.length];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public Object[] changePlansToOrders(String[][] plans) {
        Order[] orders = new Order[plans.length];
        for(int i = 0; i < plans.length; i++) {
            orders[i] = new Order(i, changeHourToMinute(plans[i][1]), changeHourToMinute(plans[i][2]));
        }
        Arrays.sort(orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.startTime - o2.startTime;
            }
        });
        return orders;
    }

    public int changeHourToMinute(String time) {
        if(time.contains(":")) {
            return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
        }
        return Integer.parseInt(time);
    }
}

class Order {
    int subjectIndex;
    int startTime;
    int duration;

    Order(int subjectIndex, int startTime, int duration) {
        this.subjectIndex = subjectIndex;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getSubject(String[][] plans) {
        return plans[subjectIndex][0];
    }

}
