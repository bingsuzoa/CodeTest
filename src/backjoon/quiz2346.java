package backjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class quiz2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        int aIndex = 0;
        int index2 = 0;
        int value = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int M = Integer.parseInt(st.nextToken());
            aList.add(M);
            bList.add(M);
        }
        //1번째
        //출력
        bw.write(aIndex+1 + " ");
        //index2의 value값 가져오기
        value = bList.get(index2);
        //다 저장했으면 삭제
        bList.remove(index2);

        while(!bList.isEmpty()){
            //새로운 index2 가져오기
            index2 = Math.abs(index2-+value) % bList.size();
            //index2의 value값 가져오기
            value = bList.get(index2);
            //value의 aList index가져오기
            aIndex = aList.indexOf(value);
            //출력
            bw.write(aIndex+1 + " ");
            //다 저장했으면 삭제
            bList.remove(index2);
        }

        bw.flush();
        bw.close();
    }

}
