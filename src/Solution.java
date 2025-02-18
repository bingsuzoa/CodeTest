import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] coin = new int[n];
        for(int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }

        Arrays.sort(coin);

        int target = 1;
        for(int i = 0; i < coin.length; i++) {
            if(coin[i] <= target) {
                target += coin[i];
            } else {
                System.out.println(target);
                break;
            }
        }
    }
}