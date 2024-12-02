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
        int max = quiz4.getMax(expressions) + 1;
        List<Integer> formation = quiz4.getFormation(aList, max, 9);

        List<String> answerList = quiz4.getAnswer(xList, formation);
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public List<String> getAnswer(List<String> xList, List<Integer> formation){
        List<String> answer = new ArrayList<>();
        for(int i = 0; i < formation.size(); i++){
            for(String exp : xList){
                exp = exp.replaceAll("[^0-9]", "");
                StringTokenizer st = new StringTokenizer(exp);
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                String string = st.nextToken();

                if(answer.size() == 0) {
                    if (exp.contains(Operatior.PLUS.getSign())) {
                        int x = ((num1 / i) * 10) + (num1 % i);
                        int y = ((num2 / i) * 10) + (num2 % i);
                        int temp = Operatior.PLUS.apply(x, y);
                        temp = (temp / i) * 10 + (temp % i);
                        string = x + " " + Operatior.PLUS.getSign() + " " + y + " = " + temp;
                        answer.add(string);
                    } else {
                        int x = ((num1 / i) * 10) + (num1 % i);
                        int y = ((num2 / i) * 10) + (num2 % i);
                        int temp = Operatior.MINUS.apply(x, y);
                        temp = (temp / i) * 10 + (temp % i);
                        string = x + " " + Operatior.MINUS.getSign() + " " + y + " = " + temp;
                        answer.add(string);
                    }
                } else {
                    if (exp.contains(Operatior.PLUS.getSign())) {
                        int x = ((num1 / i) * 10) + (num1 % i);
                        int y = ((num2 / i) * 10) + (num2 % i);
                        int temp = Operatior.PLUS.apply(x, y);
                        temp = (temp / i) * 10 + (temp % i);
                        if(answer.get(answer.size()-1).equals(temp)){
                            continue;
                        } else {
                            string = x + " " + Operatior.PLUS.getSign() + " " + y + " = ?";
                            answer.set(answer.size()-1, string);
                        }
                    } else {
                        int x = ((num1 / i) * 10) + (num1 % i);
                        int y = ((num2 / i) * 10) + (num2 % i);
                        int temp = Operatior.MINUS.apply(x, y);
                        temp = (temp / i) * 10 + (temp % i);
                        if(answer.get(answer.size()-1).equals(temp)){
                            continue;
                        } else {
                            string = x + " " + Operatior.PLUS.getSign() + " " + y + " = ?";
                            answer.set(answer.size()-1, string);
                        }
                    }
                }
            }
        }
        return answer;
    }



    public List<Integer> getFormation(List<String> list, int max, int number){
        List<Boolean> comparision = new ArrayList<>();
        List<Integer> formation = new ArrayList<>();
        for(int i = max; i <= number; i++) {
            for (String exp : list) {
                exp = exp.replaceAll("[^0-9]", "");
                StringTokenizer st = new StringTokenizer(exp);
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                int num3 = Integer.parseInt(st.nextToken());

                if (exp.contains(Operatior.PLUS.getSign())) {
                    int x = ((num1 / i) * 10) + (num1 % i);
                    int y = ((num2 / i) * 10) + (num2 % i);
                    int z = ((num3 / i) * 10) + (num3 % i);
                    int temp = Operatior.PLUS.apply(x, y);
                    int result = (temp / i) * 10 + (temp % i);
                    if (z == result) {
                        formation.add(i);
                        comparision.add(true);
                    }
                } else {
                    int x = ((num1 / i) * 10) + (num1 % i);
                    int y = ((num2 / i) * 10) + (num2 % i);
                    int z = ((num3 / i) * 10) + (num3 % i);
                    int temp = Operatior.MINUS.apply(x, y);
                    int result = (temp / i) * 10 + (temp % i);
                    if (z == result) {
                        formation.add(i);
                        comparision.add(true);
                    }
                }
                if(comparision.size() >= 3 && !comparision.contains(false)){
                    break;
                }
            }
        }
        return formation;
    }

    public int getMax(String[] expressions){
        List<Integer> aList = new ArrayList<>();
        int max = 0;

        for(String exp : expressions){
            exp = exp.replaceAll("[^0-9]", "");
            StringTokenizer st = new StringTokenizer(exp);
            int num1 = Integer.parseInt(st.nextToken());
            aList.add(num1);
            int num2 = Integer.parseInt(st.nextToken());
            aList.add(num2);
            int num3 = 0;
            String temp = st.nextToken();
            if(!temp.equals("X")){
                num3 = Integer.parseInt(temp);
                aList.add(num3);
            }
            for(int num : aList){
                max = num / 10;
                int remainder = num % 10;
                if(max < remainder){
                    max = remainder;
                }
            }
        }
        return max;
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
