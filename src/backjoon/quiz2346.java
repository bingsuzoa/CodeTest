package backjoon;

import java.io.*;
import java.util.*;

public class quiz2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> counter = new ArrayDeque<>();
        int index = 0;
        int value = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int M = Integer.parseInt(st.nextToken());
            deque.add(M);
            counter.add(i+1);
        }

        //1번째 출력 후 삭제
        bw.write(counter.pollFirst() + " ");
        //value값
        value = deque.pollFirst();

        while(!deque.isEmpty()) {
            //이동
            if (value > 0) {
                for(int i = 0; i < value; i++){
                    deque.addLast(deque.poll());
                    counter.addLast(counter.poll());
                }
                value = deque.pollLast();
                index = counter.pollLast();
            } else {
                for(int i = 0; i < Math.abs(value); i++){
                    deque.addFirst(deque.pollLast());
                    counter.addFirst(counter.pollLast());
                }
                value = deque.pollFirst();
                index = counter.pollFirst();
            }
            bw.write(index + " ");
        }
        bw.flush();
        bw.close();
    }

}
