package programmers;

public class Quiz2 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        long result = 0;
        int level = 0;

        //한계 result찾기
        //배열의 최댓값
       int max = 1;
       int maxLevel = 1;

       if(diffs.length == 1){
           answer = times[0];
       } else {
           for(int i = 1; i < diffs.length; i++){
               if(max < diffs[i]){
                   max = diffs[i];
               }
           }
           if(max == 1){
               answer = max;
           } else {
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
               if(result <= limit){
                   result = 0;
                   level = level - 1;
                   for (int i = 0; i < diffs.length; i++) {
                       int diff = diffs[i];
                       if (level >= diff) {
                           result += times[i];
                       } else {
                           result += (times[i] + ((long) (times[i] + times[i - 1]) * (diff - level)));
                       }
                   }
                   if (result > limit) {
                       level = level + 1;
                       answer = level;
                   }
               }
           }
       }


        //이진트리로 줄여가야한다.
        int minLevel = 1;
        while (answer == 0) {
            result = 0;

            if(level == 1){
                answer = level;
                break;
            } else {
                level = (minLevel + maxLevel) / 2;
                if(level == 1){
                    answer = level;
                    break;
                }
            }

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
                    maxLevel = level;
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
        int[] diffs = {1};
        int[] times = {1};
        long limit = 1;

        Quiz2 test = new Quiz2();
        System.out.println(test.solution(diffs, times, limit));
    }
}
