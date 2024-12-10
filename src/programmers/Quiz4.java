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
        List<String> xList = new ArrayList<>(checkX(expressions));
        List<String> aList = getList(expressions);
        int max = getMax(expressions);
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
        }
        if(!answer.isEmpty()){
            for (int j = 1; j < JBList.size(); j++) {
                for (int i = 0; i < answer.size(); i++) {
                    if(answer.get(i).contains("?")){
                        continue;
                    } else {
                        int x = split(answer.get(i)).get(0);
                        int y = split(answer.get(i)).get(1);
                        int z = split(answer.get(i)).get(2);
                        int result = calculate(JBList.get(j), xList.get(i));
                        if (z != result) {
                            if (xList.get(i).contains("+")) {
                                answerString = x + " + " + y + " = ?";
                                answer.set(i, answerString);
                            } else {
                                answerString = x + " - " + y + " = ?";
                                answer.add(i, answerString);
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
    public List<Integer> getJB(int max, List<String> alist){
        List<Integer> JBList = new ArrayList<>();
        if(max != 9) max = max + 1;
        int count = 0;
        for(int i = max; i <= 9; i++){
            for(String exp : alist){
                int result = calculate(i, exp);
                int z = split(exp).get(2);
                if(result != z) break;
                else {
                    count ++;
                    if(count == 3 || count == alist.size()){
                        JBList.add(i);
                        count = 0;
                        break;
                    }
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
        result = calculateJB(n, result);
        return result;
    }

    public int calculateJB(int jb, int result){
        int count = 0;
        List<Integer> list = new ArrayList<>();
        int m = 1;
        while(Math.pow(jb, count) <= result){
            count++;
        }
        count--;
        for(int i = count; i >= 0; i--){
            while(m * Math.pow(jb, i) <= result){
                m++;
            }
            m--;
            list.add(m);
            result = result - m * (int)Math.pow(jb, i);
            m = 0;
        }
        for(int i = 0; i <list.size(); i++){
            result += list.get(i) * (int)Math.pow(10, list.size()-1-i);
        }
        return result;
    }

    public int getMax(String[] expressions){
        List<Integer> aList;
        int max = 0;
        for(String exp : expressions){
          aList = split(exp);
          for(int i = 0; i < aList.size(); i++){
              int first = aList.get(i) / 100;
              if(max < first) max = first;

              int second = (aList.get(i) - (first * 100)) / 10;
              if(max < second) max = second;

              int last = (aList.get(i) - (first * 100)) % 10;
              if(max < last) max = last;
          }
        }
        return max;
    }

    public List<Integer> split(String expression){
        List<Integer> list = new ArrayList<>();
        expression = expression.replaceAll("[^0-9]", " ");
            StringTokenizer st = new StringTokenizer(expression);
            while(st.hasMoreTokens()){
                list.add(Integer.parseInt(st.nextToken()));
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

    public static void main(String[] args){
        String[] expressions = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
        Quiz4 quiz4 = new Quiz4();
        String[] answer = quiz4.solution(expressions);
        for(String ans : answer){
            System.out.println(ans);
        }
    }
}

