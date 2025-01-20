package programmers;



public class ConsecutiveSequence {

    public static void main(String[] args) {
        ConsecutiveSequence test = new ConsecutiveSequence();
        int[] sequence = {1,2,3,4,5};
        int k = 7;
        int[] answer = test.solution(sequence, k);
        for(int value : answer) {
            System.out.print(value + " ");
        }
    }
    public int[] solution(int[] sequence, int k) {
        int min = 1000000000;
        int start = 0;
        int end = 0;
        int sum = 0;
        int answer_start = -1;
        int answer_end = -1;

        for(int i = 0; i < sequence.length; i++) {
            end++;
            sum += sequence[i];
            if(sum == k) {
                if(min > end - start + 1) {
                    min = end - start + 1;
                    answer_start = start;
                    answer_end = end - 1;
                }
            }
            else if(sum > k) {
                while(sum > k) {
                    sum -= sequence[start];
                    start++;
                }
                if(sum == k) {
                    if(min > end - start + 1) {
                        min = end - start + 1;
                        answer_start = start;
                        answer_end = end - 1;
                    }
                }
            }
        }
        int[] answer = {answer_start, answer_end};
        return answer;
    }
}
