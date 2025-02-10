package programmers;

import java.util.ArrayList;
import java.util.List;

public class Test250210 {
    private static int min;
    private static int count = 1;
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Test250210 test = new Test250210();
        String s = "aaaaaaaaab";
        System.out.println(test.solution(s));
    }

    public int solution(String s) {
        min = s.length();
        while (count < s.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (i + count >= s.length()) {
                    list.add(s.substring(i));
                    break;
                }
                if (i == s.length() - 1) {
                    list.add(s.substring(i));
                } else {
                    list.add(s.substring(i, i + count));
                    i = i + count - 1;
                }
            }
            min = Math.min(min, find(list));
            count++;
            list.clear();
        }
        return min;
    }

    public int find(List<String> list) {
        StringBuilder buffer = new StringBuilder();
        boolean isSame = false;
        int sameCount = 1;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                isSame = true;
                sameCount++;
                if (i == list.size() - 2) {
                    buffer.append(sameCount);
                    buffer.append(list.get(i+1));
                }
            } else {
                if (isSame) {
                    buffer.append(sameCount);
                }
                buffer.append(list.get(i));
                isSame = false;
                sameCount = 1;
                if (i == list.size() - 2) {
                    buffer.append(list.get(i+1));
                }
            }
        }
        return buffer.toString().length();
    }
}
