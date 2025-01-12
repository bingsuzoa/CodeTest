package programmers;

import java.io.*;

public class FloorConstruction {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[N+1];
        count[1] = 1;
        count[2] = 3;
        for(int i = 3; i < count.length; i++) {
            count[i] = count[i-1] + (count[i-2] * 2);
        }
        bw.write(count[count.length-1] + "");
        bw.close();
    }
}
