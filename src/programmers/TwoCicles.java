package programmers;

public class TwoCicles {

    public static void main(String[] args) {
        int r1 = 999999;
        int r2 = 1000000;
        TwoCicles test = new TwoCicles();
        System.out.println(test.solution(r1,r2));
    }
    public long solution(int r1, int r2) {
        long answer = 0;

        for(long i = 0; i <= r2; i++) {
            double length1;
            if(r1 > i) {
                long extent1 = ((long)r1 * r1) - (i*i);
                length1 = Math.sqrt(extent1);
            }  else {
                length1 = 0;
            }
            long extent2 = ((long)r2 * r2) - (i*i);
            double length2 = Math.sqrt(extent2);
            answer += (Math.floor(length2) - Math.ceil(length1) + 1);
        }
        answer = answer * 4;
        long overlapping = (r2 - r1 + 1) * 4;
        answer = answer - overlapping;

        return answer;
    }
}
