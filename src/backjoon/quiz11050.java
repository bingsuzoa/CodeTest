package backjoon;

import java.io.*;
import java.util.StringTokenizer;

public class quiz11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(K == 0 || K == N){
            bw.write(1 + "");
        } else {
            int a = 1;
            int b = 1;
            int c = 1;
            for(int i = 1; i <= N; i++){
                a *= i;
            }
            for(int i = 1; i <= N-K; i++){
                b *= i;
            }
            for(int i = 1; i <= K; i++){
                c *= i;
            }
            int value = a/(b*c);
            bw.write(value +"");
        }
        bw.flush();
        bw.close();
    }
}
