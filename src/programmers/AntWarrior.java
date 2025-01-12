package programmers;

import java.io.*;
import java.util.StringTokenizer;

public class AntWarrior {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        String input = br.readLine();
        st = new StringTokenizer(input);
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N + 1];
        count[1] = arr[0];
        count[2] = Math.max(count[1], arr[1]);

        for(int i = 2; i <= arr.length-1; i++) {
            count[i + 1] = Math.max(count[i], arr[i] + count[i-1]);
        }
        bw.write(count[count.length-1] + "");
        bw.close();
    }
}
