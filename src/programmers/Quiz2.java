package programmers;

public class Quiz2 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        long result = 0;
        int level;

        //한계 result찾기
        //배열의 최댓값
       int max = 1;
       int maxLevel = 1;
       for(int i = 1; i < diffs.length; i++){
           if(max < diffs[i]){
               max = diffs[i];
           }
       }
       //result 구하기 : level이 배열의 최댓값일때
        level = max;
        maxLevel = max;
        for(int i = 0; i < diffs.length; i++){
            int diff = diffs[i];
            if(level >= diff){
                result += times[i];
            } else {
                result += (times[i] + ((long)(times[i] + times[i-1])*(diff-level)));
            }
        }
        //이진트리로 줄여가야한다.
        int minLevel = 1;
        while (true) {
            result = 0;
            level = (minLevel + maxLevel) / 2;
            for (int i = 0; i < diffs.length; i++) {
                int diff = diffs[i];
                if (level >= diff) {
                    result += times[i];
                } else {
                    result += (times[i] + ((long) (times[i] + times[i - 1]) * (diff - level)));
                }
            }
            if (result <= limit){
                result = 0;
                level = level -1;
                for (int i = 0; i < diffs.length; i++) {
                    int diff = diffs[i];
                    if (level >= diff) {
                        result += times[i];
                    } else {
                        result += (times[i] + ((long) (times[i] + times[i - 1]) * (diff - level)));
                    }
                }
                if(result > limit){
                    level = level + 1;
                    answer = level;
                    break;
                } else {
                    if(level == 1){
                        answer = level;
                        break;
                    } else {
                        maxLevel = level;
                    }
                }

            } else {
                result = 0;
                level = level + 1;
                for (int i = 0; i < diffs.length; i++) {
                    int diff = diffs[i];
                    if (level >= diff) {
                        result += times[i];
                    } else {
                        result += (times[i] + ((long) (times[i] + times[i - 1]) * (diff - level)));
                    }
                }
                if(result <= limit){
                    answer = level;
                    break;
                } else {
                    minLevel = level;
                }
            }
        }
        return answer;
    }
    public static void main(String[] args){
        int[] diffs = {1,1,3};
        int[] times = {1,2,3};
        long limit = 50;

        Quiz2 test = new Quiz2();
        System.out.println(test.solution(diffs, times, limit));
    }
}
