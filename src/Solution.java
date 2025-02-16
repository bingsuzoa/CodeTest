import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        boolean[] occupied = new boolean[g+1];
        int p = sc.nextInt();
        int[] order = new int[p];
        for(int i = 0; i < order.length; i++) {
            order[i] = sc.nextInt();
        }
        List<ArrayList<Integer>> list = new ArrayList<>(g+1);
        for(int i = 0; i < g+1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 1; i < list.size(); i++) {
            for(int j = 1; j <= i; j++) {
                list.get(i).add(j);
            }
            Collections.sort(list.get(i), Comparator.reverseOrder());
        }

        for(int i = 0; i < order.length; i++) {
            int plane = order[i];
            boolean success = false;
            for(int j = 0; j < list.get(plane).size(); j++) {
                if(!occupied[list.get(plane).get(j)]) {
                    occupied[list.get(plane).get(j)] = true;
                    success = true;
                    break;
                }
            }
            if(!success) {
                break;
            }
        }
        int count = 0;
        for(boolean value : occupied) {
            if(value) count++;
        }
        System.out.println(count);
    }
}