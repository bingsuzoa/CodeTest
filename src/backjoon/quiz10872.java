package backjoon;

import java.io.*;

public class quiz10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));

        int N = Integer.parseInt(br.readLine());

        if(N == 0){
            bw.write(1 +  " ");
        } else {
            for(int i = 1; i <= N; i++){
                int value = 1;
                value *= i;
                bw.write(value);
            }
        }

        bw.flush()
;
    }
}
