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
        Queue<Integer> C = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            C.add(Integer.parseInt(st.nextToken()));
        }

        Deque<Integer> deque = new ArrayDeque<>();

        //deque에 que값만 넣기
        for(int i = 0; i < A.length; i++){
            if(A[i] == 0){
                deque.add(B[i]);
            }
        }
        if(deque.isEmpty()){
            for(int i = 0; i < M; i++){
                bw.write(C.poll() + " ");
            }
        } else {
            for(int i = 0; i < M; i++){
                if(deque.isEmpty()){
                    bw.write(C.poll() + " ");
                } else {
                    bw.write(deque.pollLast() + " ");
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
