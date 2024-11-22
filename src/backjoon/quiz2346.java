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
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        int index = 0;
        int value = 0;
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int M = Integer.parseInt(st.nextToken());
            aList.add(M);
            bList.add(M);
        }

        //1번째 출력
        bw.write(answer + 1 + " ");
        //value 저장
        value = bList.get(index);
        //삭제
        bList.remove(index);

        while(!bList.isEmpty()){
            //이동
            if(bList.size() > 1){
                if(value > 0){
                    if(index + value <= bList.size()-1){
                        index = ((index-1) + value) % bList.size();
                    } else {
                        index = (index + value -1) % bList.size();
                    }
                } else {
                    if(0<= index + value){
                        index = index + value;
                    } else {
                        if(index == bList.size()){
                            index = bList.size() + index + value;
                        } else if (index == 0){
                            if(Math.abs(index+value) % bList.size() == 0){
                                index = 0;
                            } else {
                                index = bList.size() - (Math.abs(index+value) % bList.size());
                            }
                        } else {
                            if(Math.abs(index+value) % bList.size() == 0){
                                index = 0;
                            } else {
                                index = bList.size() - (Math.abs(index+value) % bList.size());
                            }
                        }
                    }
                }
            } else {
                index = 0;
            }
            //value값
            value = bList.get(index);
            //value값에 대한 aList index
            answer = aList.indexOf(value);
            bw.write(answer+1 + " ");
            //삭제
            bList.remove(index);
        }
        bw.flush();
        bw.close();
    }

}
