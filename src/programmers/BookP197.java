package programmers;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BookP197 {

    public static void main(String[] args) throws IOException {
        BookP197 test = new BookP197();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String input1 = br.readLine();
        st = new StringTokenizer(input1);
        int[] store = new int[N];
        for(int i = 0; i < N; i++) {
            store[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        String input2 = br.readLine();
        st = new StringTokenizer(input2);
        int[] customer = new int[M];
        for(int i = 0; i < M; i++) {
            customer[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(store);
        Arrays.sort(customer);

        for(int i = 0; i < customer.length; i++) {
            int start = 0;
            int end = store.length-1;
            int target = customer[i];

            while(start != end) {
                int[] index = test.haveGoods(store,start, end, target);
                start = index[0];
                end = index[1];
            }


            if(store[start] != target) {
                bw.write("No" + " ");
            } else {
                bw.write("Yes" + " ");
            }
            bw.flush();
        }
        bw.close();
        
    }

    public int[] haveGoods(int[] store, int start, int end, int target) {
        int mid = -1;
        int[] index = new int[2];
        if(start < end) {
            mid = (start + end) / 2;
            if(target < store[mid]) {
                end = mid - 1;
            }
            else if( target > store[mid]) {
                start = mid + 1;
            }
            else {
                start = mid;
                end = mid;
            }
        }
        index[0] = start;
        index[1] = end;
        return index;
    }
}
