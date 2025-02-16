import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] result = new int[n+1];
        int[] time = new int[n+1];
        List<ArrayList<Integer>> list = new ArrayList<>(n+1);
        int[] order = new int[n+1];

        for(int i = 0; i < n+1; i++) {
            list.add(new ArrayList<>());
        }

        Queue<Integer> queue =  new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            String input = br.readLine();
            st = new StringTokenizer(input);
            time[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int value = Integer.parseInt(st.nextToken());
                if(value == -1) {
                    continue;
                }
                list.get(value).add(i);
                order[i] ++;
            }
        }

        for(int i = 1; i < order.length; i++) {
            if(order[i] == 0) {
                result[i] = time[i];
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();
            int now_time = result[now];

            for(int i = 0; i < list.get(now).size(); i++) {
                order[list.get(now).get(i)]--;
                result[list.get(now).get(i)] = Math.max(result[list.get(now).get(i)], now_time + time[list.get(now).get(i)]);
                if(order[list.get(now).get(i)] == 0) {
                    queue.add(list.get(now).get(i));
                }
            }
        }
        for(int i = 1; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }
}