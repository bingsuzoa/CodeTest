package backjoon;

import java.io.*;

public class quiz15439 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int value = (N-1) * N;
        bw.write(value + " ");
        bw.flush();
        bw.close();
    }
}
