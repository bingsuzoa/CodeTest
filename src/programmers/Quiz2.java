package programmers;

public class Quiz2 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer;
        long result = 0;
        int level = 1;

        while(true){
            for(int i = 0; i<diffs.length; i++){
                int diff = diffs[i];
                if(level >= diff){
                    result += times[i];
                } else {
                    result += (times[i] + ((long)(times[i] + times[i-1]) * (diff-level)));
                }
            }
            if(result > limit) {
                result = 0;
                level++;
            } else {
                break;
            }
        }
        answer = level;
        return answer;
    }

    public static void main(String[] args){
        int[] diffs = {1,99999,100000,99995};
        int[] times = {9999,9001,9999,9001};
        long limit = 3456789012L;

        Quiz2 test = new Quiz2();
        System.out.println(test.solution(diffs, times, limit));
    }
}
