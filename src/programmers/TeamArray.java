package programmers;

import java.io.*;
import java.util.StringTokenizer;

public class TeamArray {

    public static void main(String[] args) throws IOException {
        TeamArray test = new TeamArray();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input1 = br.readLine();
        st = new StringTokenizer(input1);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] command = new int[M][3];
        for(int i = 0; i < command.length; i++) {
            String input2 = br.readLine();
            st = new StringTokenizer(input2);
            command[i][0] = Integer.parseInt(st.nextToken());
            command[i][1] = Integer.parseInt(st.nextToken());
            command[i][2] = Integer.parseInt(st.nextToken());
        }

        int[] people = new int[N + 1];
        for(int i = 0; i < people.length; i++) {
            people[i] = i;
        }

        for(int i = 0; i <command.length; i++) {
            int order = command[i][0];
            int n1 = command[i][1];
            int n2 = command[i][2];

            if(order == 0) {
                if(people[n1] != people[n2]) {
                    people = test.union(people, n1, n2);
                }
            } else {
                if(people[n1] == people[n2]) {
                    bw.write("Yes" + " ");
                } else {
                    bw.write("No" + " ");
                }
            }
        }
        bw.flush();
        bw.close();
    }

    public int[] union(int[] people, int n1, int n2) {
        int a = find(people, n1);
        int b = find(people, n2);
        if(a < b) {
            people[b] = a;
        } else {
            people[a] = b;
        }
        return people;
    }

    public int find(int[] people, int number) {
        if(people[number] != number) {
            people[number] = find(people, people[number]);
        }
        return people[number];
    }
}
