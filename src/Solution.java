
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] house = new int[n];

        for (int i = 0; i < house.length; i++) {
            int m = sc.nextInt();
            house[i] = m;
        }
        Arrays.sort(house);

        int mid = (house.length - 1) / 2;
        int min = 0;
        for (int i = 0; i < house.length; i++) {
            min += Math.abs(house[mid] - house[i]);
        }

        int number = house[mid];

        int before = mid - 1;
        while (before >= 0) {
            int before_sum = 0;
            for (int i = 0; i < house.length; i++) {
                before_sum += Math.abs(house[before] - house[i]);
            }
            if (min >= before_sum) {
                min = before_sum;
                number = house[before];
                before--;
            } else {
                break;
            }
        }
        int after = mid + 1;
        while (after < house.length) {
            int after_sum = 0;
            for (int i = 0; i < house.length; i++) {
                after_sum += Math.abs(house[after] - house[i]);
            }
            if (min > after_sum) {
                min = after_sum;
                number = house[after];
                after++;
            }
            else if(min == after_sum) {
                after++;
            }
            else {
                break;
            }
        }
        System.out.println(number);
    }
}