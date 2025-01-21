package programmers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Adventurer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] adventurer = new int[N];
        String input = br.readLine();
        st = new StringTokenizer(input);
        for(int i = 0; i < N; i++) {
            adventurer[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(adventurer);

        int max = 0;
        for(int i = adventurer.length-1; i >= 0; i--) {
            int pointer = i;
            int count = 0;
            while(pointer >= 0) {
                int scare = adventurer[pointer];
                if(pointer - scare + 1 >= 0) {
                    pointer = pointer - scare;
                    count ++;
                } else {
                    break;
                }
            }
            if(max < count) {
                max = count;
            }
        }
        bw.write(max + " ");
        bw.close();
    }
}
