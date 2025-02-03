package programmers;

public class ConsecutiveSequence {

    public static void main(String[] args) {
        int[] sequence = {2, 2, 2, 2, 2};
        int k = 6;
        ConsecutiveSequence test = new ConsecutiveSequence();
        int[] answer = test.solution(sequence, k);
        for(int value : answer) {
            System.out.print(value + " ");
        }
    }

    public int[] solution(int[] sequence, int k) {
        int start = 0;
        int end = 1;
        int sum = sequence[start] + sequence[end];
        int[] answer = new int[2];
        int length = sequence.length;
        answer[0] = sequence[sequence.length-1];
        while(start <= end) {
            if(sum < k) {
                end ++;
                if(end >= sequence.length) {
                    break;
                }
                sum += sequence[end];
            }
            else if(sum > k) {
                sum -= sequence[start];
                start++;
            } else {
                if(length > end - start) {
                    answer[0] = start;
                    answer[1] = end;
                    length = end - start;
                }
                else if(length == end - start) {
                    if(answer[0] > start) {
                        answer[0] = start;
                        answer[1] = end;
                    }
                }
                sum -= sequence[start];
                start++;
            }
        }
        return answer;
    }
}