import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<Subject> graph = new ArrayList<>();
        int[] time = new int[n+1];
        int[] order = new int[n+1];
        int[] answer = new int[n+1];
        Queue<Subject> queue = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            String input = br.readLine();
            st = new StringTokenizer(input);
            time[i] = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int before_subject = Integer.parseInt(st.nextToken());
                if(before_subject == -1) {
                    continue;
                }
                order[i] ++;
                graph.add(new Subject(i, before_subject));
            }
        }

        for(int i = 1; i < order.length; i++) {
            if(order[i] == 0) {
                queue.add(new Subject(i, 0));
            }
        }

        while(!queue.isEmpty()) {
            Subject sub = queue.poll();
            int now_sub = sub.now;
            int before_sub = sub.before;

            answer[now_sub] += (time[now_sub] + time[before_sub]);
            
            for(int i = 0; i < graph.size(); i++) {
                Subject subject = graph.get(i);
                int now = subject.now;
                int before = subject.before;
                if(before == now_sub) {
                    order[now] --;
                    if(order[now] == 0) {
                        queue.add(new Subject(now, before));
                    }
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
class Subject implements Comparable<Subject> {
    int now;
    int before;

    Subject(int now, int before) {
        this.now = now;
        this.before = before;
    }

    @Override
    public int compareTo(Subject other) {
        return this.before - other.before;
    }
}
