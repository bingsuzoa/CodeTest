package programmers;

import java.io.*;
import java.util.*;

public class BookP178 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Map<String, Integer> scoreMap = new HashMap<>();
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String information = br.readLine();
            st = new StringTokenizer(information);
            scoreMap.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        List<List<String>> scoreList = new ArrayList<>(101);

        for(int i = 0; i < 101; i++) {
            scoreList.add(new ArrayList<>());
        }
        Iterator<String> keys = scoreMap.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            int score = scoreMap.get(key);

            scoreList.get(score).add(key);
        }
        for(int i = 0; i < scoreList.size(); i++) {
            if(scoreList.get(i).isEmpty()) {
                continue;
            } else {
                List<String> tempList = scoreList.get(i);
                for(String name : tempList) {
                    bw.write(name + " ");
                }
            }
        }
        bw.close();
    }
}
