package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.IntBinaryOperator;

enum Operatior {
    PLUS("+", (x , y) -> x + y),
    MINUS("-", (x , y) -> x - y);

    private final String sign;
    private final IntBinaryOperator calculation;

    Operatior(String sign, IntBinaryOperator calculation){
        this.sign = sign;
        this.calculation = calculation;
    }

    public String getSign(){
        return sign;
    }

    public int apply(int x, int y){
        return calculation.applyAsInt(x , y);
    }
}

public class Quiz4 {

    public String[] solution(String[] expressions) {
        Quiz4 quiz4 = new Quiz4();
        List<String> xList = new ArrayList<>(quiz4.checkX(expressions));
        List<String> aList = getList(expressions);
        int max = quiz4.getMax(expressions);
        List<Integer> JBList = getJB(max, aList);
        List<String> answerList = getAnswer(xList, JBList);

        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public List<String> getAnswer(List<String> xList, List<Integer> JBList){
        List<String> answer = new ArrayList<>();
        String answerString;

        if(JBList.size() == 1 || answer.isEmpty()){
            for(String exp : xList){
                int x = split(exp).get(0);
                int y = split(exp).get(1);
                int result = calculate(JBList.get(0), exp);
                if(exp.contains("+")) {
                    answerString = x + " + " + y + " = " +result;
                    answer.add(answerString);
                } else {
                    answerString = x + " - " + y + " = " +result;
                    answer.add(answerString);
                }
            }
        } else {
            for (int j = 0; j < JBList.size(); j++) {
                for (int i = 0; i < xList.size(); i++) {
                    int x = split(xList.get(i)).get(0);
                    int y = split(xList.get(i)).get(1);
                    int z = split(xList.get(i)).get(2);
                    int result = calculate(JBList.get(j), xList.get(i));
                    if (z == result) {
                        if (xList.get(i).contains("+")) {
                            answerString = x + " + " + y + " = ?";
                            answer.add(answerString);
                        } else {
                            answerString = x + " - " + y + " = ?";
                            answer.add(answerString);
                        }
                    }
                }
            }
        }
        return answer;
    }
    public List<Integer> getJB(int max, List<String> alist){
        List<Integer> JBList = new ArrayList<>();
        max = max + 1;
        int count = 0;
        for(int i = max; i <= 9; i++){
            for(String exp : alist){
                int result = calculate(i, exp);
                int z = split(exp).get(2);
                if(result == z){
                    if(count == 3){
                        break;
                    } else {
                        count++;
                        JBList.add(i);
                    }
                } else {
                    break;
                }
            }
        }
        return JBList;
    }

    public int calculate(int n, String exp){
        int result = 0;
        int n1 = split(exp).get(0);
        int n2 = split(exp).get(1);
        int x = ((n1 / 10) * n) + (n1 % 10);
        int y = ((n2 / 10) * n) + (n2 % 10);
        if(exp.contains(Operatior.PLUS.getSign())) result = Operatior.PLUS.apply(x,y);
        if(exp.contains(Operatior.MINUS.getSign())) result = Operatior.MINUS.apply(x,y);
        result = ((result / n) * 10) + (result % 10);
        return result;
    }

    public int getMax(String[] expressions){
        List<Integer> aList;
        int max = 0;
        for(String exp : expressions){
          aList = split(exp);
          for(int i : aList){
              max = aList.get(i) / 100;
              int second = aList.get(i) / 10;
              if(max < second) max = second;

              int last = aList.get(i) % 10;
              if(max < last) max = last;
          }
        }
        return max;
    }

    public List<Integer> split(String expression){
        List<Integer> list = new ArrayList<>();
        expression = expression.replaceAll("[^0-9]", "");
        if(!expression.contains("X")){
            StringTokenizer st = new StringTokenizer(expression);
            int num1 = Integer.parseInt(st.nextToken());
            list.add(num1);
            int num2 = Integer.parseInt(st.nextToken());
            list.add(num2);
            int num3 = Integer.parseInt(st.nextToken());
            list.add(num3);
        } else {
            StringTokenizer st = new StringTokenizer(expression);
            int num1 = Integer.parseInt(st.nextToken());
            list.add(num1);
            int num2 = Integer.parseInt(st.nextToken());
            list.add(num2);
        }
        return list;
    }

    public List<String> checkX(String[] expressions){
        List<String> xList = new ArrayList<>();
        for(String exp : expressions){
            if(exp.contains("X")){
                xList.add(exp);
            }
        }
        return xList;
    }

    public List<String> getList(String[] expressions){
        List<String> aList = new ArrayList<>();
        for(String exp : expressions){
            if(!exp.contains("X")){
                aList.add(exp);
            }
        }
        return aList;
    }
}
