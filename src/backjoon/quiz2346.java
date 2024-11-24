package backjoon;

import java.io.*;
import java.util.*;

public class quiz2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int value = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int M = Integer.parseInt(st.nextToken());
            deque.add(M);
            list.add(M);
        }

        //1번째 출력
        bw.write(index + 1 + " ");
        //value값
        value = list.get(index);
        //삭제
        deque.remove();

        while(!deque.isEmpty()) {
            //이동
            if (value > 0) {
                for(int i = 0; i < value; i++){
                    deque.addLast(deque.poll());
                }
                value = deque.pollLast();
                index = list.indexOf(value);
            } else {
                for(int i = 0; i < Math.abs(value); i++){
                    deque.addFirst(deque.pollLast());
                }
                value = deque.pollFirst();
                index = list.indexOf(value);
            }
            bw.write(index + 1 + " ");
        }
        bw.flush();
        bw.close();
    }

}
