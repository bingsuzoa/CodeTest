package backjoon;

import java.io.*;
import java.util.*;

public class quiz24511 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] B = new int[N];
        for(int i = 0; i < N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] C = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            C[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();



        //deque에 값 넣기
        for(int i = 0; i < N; i++){
            deque.add(B[i]);
        }
        for(int i = 0; i < M; i++) {
            int c = C[i];
            for (int j = 0; j < A.length; j++) {
                //0이면 큐
                if (A[j] == 0) {
                    for (int k = 0; k <= j; k++) {
                        stack.add(deque.pollFirst());
                    }
                    deque.addFirst(c);
                    c = stack.pop();
                    for (int w = 0; w < stack.size(); w++) {
                        deque.addFirst(stack.pop());
                    }
                    //1이면 stack
                } else {
                    continue;
                }
            }
            bw.write(c + " ");
        }
        bw.flush();
        bw.close();
    }
}
