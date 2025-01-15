package programmers;

public class ConsecutiveSequence {

    public static void main(String[] args) {
        ConsecutiveSequence test = new ConsecutiveSequence();
        int[] sequence = {1, 1, 1, 2, 3, 4, 5};
        int k = 5;
        int[] answer = test.solution(sequence, k);
        for(int value : answer) {
            System.out.print(value + " ");
        }
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        for(int i = 0; i < sequence.length; i++) {
            if(k == sequence[i]) {
                answer[0] = i;
                answer[1] = i;
                return answer;
            }
        }

        int[] temp = twoCount(sequence, k);
        if(sequence[temp[0]] + sequence[temp[1]] == k) {
            return temp;
        } else {
            int mid = temp[1];
            for(int i = mid; i >= 1; i--) {
                int sum = 0;
                sum += sequence[mid];
                int index = i;
                while(sum < k) {
                    index--;
                    if(index < 0) {
                        break;
                    }
                    sum += sequence[index];
                }
                if(sum == k) {
                    answer[0] = index;
                    answer[1] = i;
                }
            }

        }

        return answer;
    }

    public int[] twoCount(int[] sequence, int k) {
        int[] index = new int[2];
        double half = k / (double) 2;
        int start = 0;
        int end = sequence.length -1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(mid == sequence.length - 1){
                index[1] = end;
                return index;
            }
            if(sequence[mid] < half && sequence[mid + 1] > half) {
                if(sequence[mid] + sequence[mid + 1] == k) {
                    index[0] = mid;
                    index[1] = mid+1;
                    return index;
                } else {
                    index[0] = 0;
                    index[1] = mid;
                    return index;
                }
            }
            else if(sequence[mid] < half && sequence[mid + 1] < half) {
                start = mid + 1;
            }
        }
        return index;
    }
}
