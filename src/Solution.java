import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        String input = sc.nextLine();
        st = new StringTokenizer(input);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int count = 0;
        while(true) {
            int target = (n / k) * k;
            count += (n - target);
            n = target;
            if(n < k) {
                break;
            }
            n /= k;
            count++;
        }
        System.out.println(count - 1);
    }
}