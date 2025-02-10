package programmers;

import java.io.*;
import java.util.StringTokenizer;

public class Test250210 {
    private static int count_1;
    private static int count_2;

    public static void main(String[] args) throws IOException {
        Test250210 test = new Test250210();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String standard = input.substring(0,1);
        for(int i = 1; i < input.length(); i++) {
            if(!standard.equals(input.substring(i, i+1))) {
                count_1++;
                i = test.findIndex(i, input) -1;
                if(i == input.length()) break;
                if(input.substring(input.length() -1).equals(standard)) count_1++;
            }
        }
        System.out.println(count_1 + "," + count_2);
        System.out.println(Math.min(count_1, count_2));
    }

    public int findIndex(int i, String input) {
        String standard = input.substring(i, i+1);
        for(int j = i+1; j < input.length(); j++) {
            if(!input.substring(j, j+1).equals(standard)) {
                count_2++;
                return j;
            }
        }
        count_2++;
        return input.length();
    }
}
