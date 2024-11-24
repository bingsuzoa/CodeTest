package backjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class quiz2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int index = 0;
        int value = 0;
        int count = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int M = Integer.parseInt(st.nextToken());
            arr[i] = M;
        }

        //1번째 출력
        bw.write(index + 1 + " ");
        //value값
        value = arr[index];
        //0으로 초기화
        arr[index] = 0;

        while(count < arr.length) {
            //이동
            if (value > 0) {
                for (int i = 1; i <= value; i++) {
                    if (index + 1 <= arr.length - 1) {
                        if (arr[index + 1] != 0) {
                            index++;
                        } else {
                            index++;
                            i--;
                        }
                    } else {
                        if(arr[(index + 1) % arr.length] != 0){
                            index = (index + 1) % arr.length;
                        } else {
                            index = (index + 1) % arr.length;
                            i--;
                        }
                    }
                }
            } else if (value < 0){
                for (int i = 1; i <= Math.abs(value); i++) {
                    if (index - 1 >= 0) {
                        if (arr[index - 1] != 0) {
                            index--;
                        } else {
                            index--;
                            i--;
                        }
                    } else {
                        if (arr[arr.length + index - 1] != 0) {
                            index = arr.length + index - 1;
                        } else {
                            index = arr.length + index - 1;
                            i--;
                        }
                    }
                }
            }
            //value값 구하기
            value = arr[index];
            //출력
            bw.write(index + 1 + " ");
            //초기화
            arr[index] = 0;
            count ++;
        }
        bw.flush();
        bw.close();
    }

}
