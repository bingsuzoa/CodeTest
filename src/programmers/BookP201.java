package programmers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BookP201 {
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BookP201 test = new BookP201();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String input2 = br.readLine();
        st = new StringTokenizer(input2);
        int[] info = new int[N];
        for(int i = 0; i < N; i++) {
            info[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(info);

        bw.write(test.getMax(info, M) + " ");
        bw.close();


    }

    public int getMax(int[] info, int M) {
        int end = info[info.length - 1];
        int start = 1;
        int result = 0;
        int mid = 0;
        while(start <= end) {
            mid = (start + end) / 2;
            int sum = 0;
            for(int i = 0; i < info.length; i++) {
                if(info[i] > mid) {
                    sum += info[i] - mid;
                }
            }
            if(M > sum) {
                end = mid - 1;
            }
            else {
                result = mid;
                start = mid + 1;
            }
        }
        return result;
    }


}
