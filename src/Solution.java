import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        String input = sc.nextLine();
        st = new StringTokenizer(input);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[m][n];
        for(int i = 0; i < m; i++) {
            String input2 = sc.nextLine();
            st = new StringTokenizer(input2);
            while(st.hasMoreTokens()) {
                for(int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int[] minArr = new int[m];
        for(int i = 0; i < minArr.length; i++) {
            minArr[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j  <graph[i].length; j++) {
                minArr[i] = Math.min(minArr[i], graph[i][j]);
            }
        }

        int result = 0;
        for(int i = 0; i < minArr.length; i++) {
            result = Math.max(result, minArr[i]);
        }
        System.out.println(result);
    }

}