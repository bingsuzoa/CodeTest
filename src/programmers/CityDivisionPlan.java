package programmers;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CityDivisionPlan {

    public static void main(String[] args) throws IOException {
        CityDivisionPlan test = new CityDivisionPlan();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input1 = br.readLine();
        st = new StringTokenizer(input1);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] city = new int[N + 1];
        int[][] roadCost = new int[M][3];

        for(int i = 0; i < M; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            roadCost[i][0] = Integer.parseInt(st.nextToken());
            roadCost[i][1] = Integer.parseInt(st.nextToken());
            roadCost[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(roadCost, (o1, o2) -> {
            if(o1[2] == o2[2]) {
                return o1[0] - o2[0];
            }
            return o1[2] - o2[2];
        });

        for(int i = 0; i < city.length; i++) {
            city[i] = i;
        }

        int result = 0;
        int max = 0;
        for(int i = 0; i < roadCost.length; i++) {
            int x = roadCost[i][0];
            int y = roadCost[i][1];
            int cost = roadCost[i][2];

            if(test.find(city, x) != test.find(city, y)) {
                city = test.union(city, x, y);
                max = Math.max(cost, max);
                result += cost;
            }
        }
        result = result - max;
        bw.write(result + " ");
        bw.close();



    }

    public int[] union(int[] city, int x, int y) {
        x = find(city, x);
        y = find(city, y);
        if(x < y) {
            city[y] = x;
        } else {
            city[x] = y;
        }
        return city;
    }

    public int find(int[] city, int x) {
        if(city[x] != x) {
            city[x] = find(city, city[x]);
        }
        return city[x];
    }
}
