package programmers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class FindTheRoad {

    public static void main(String[] args) throws IOException {
        FindTheRoad test = new FindTheRoad();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] city = new int[N+1];
        for(int i = 0; i < city.length; i++) {
                city[i] = 1000000;
        }
        city[X] = 0;


        List<List<Integer>> cityInfor = new ArrayList<>(N+1);
        for(int i = 0; i < N+1; i++) {
            cityInfor.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            int index = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            cityInfor.get(index).add(value);
        }

        city = test.findCityOfMinRoadK(X, city, cityInfor);
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i < city.length; i++) {
           if(city[i] == K) {
               answer.add(i);
           }
        }

        if(answer.isEmpty()) {
            bw.write("-1");
        } else {
            for(int value : answer) {
                bw.write(value + "\n");
            }
        }
        bw.close();
    }

    public int[] findCityOfMinRoadK(int startCity, int[] city, List<List<Integer>> cityInfo) {
        Stack<Integer> stack = new Stack<>();
        stack.add(startCity);
        while(!stack.isEmpty()) {
            int cityNumber = stack.pop();
            int count = city[cityNumber];
            List<Integer> list = cityInfo.get(cityNumber);
            for(int connectedCity : list) {
                if(city[connectedCity] > count + 1) {
                    city[connectedCity] = count + 1;
                }
                stack.add(connectedCity);
            }
        }
        return city;
    }
}
