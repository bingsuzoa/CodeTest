package backjoon;

import java.io.*;
import java.util.StringTokenizer;

public class quiz24511 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[M];

        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < M; i++){
            C[i] = Integer.parseInt(st.nextToken());
        }



    }
}
