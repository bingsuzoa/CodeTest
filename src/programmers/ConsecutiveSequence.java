package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ConsecutiveSequence {
    PriorityQueue<Index> pq = new PriorityQueue<>(1, new IndexComparator());

    public static void main(String[] args) {
        ConsecutiveSequence test = new ConsecutiveSequence();
        int[] sequence = {2,2,2,2,2,2};
        int k = 6;
        int[] answer = test.solution(sequence, k);
        for(int value : answer) {
            System.out.print(value + " ");
        }
    }

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int temp = findK(sequence, k);
        if(temp != -1) {
            answer[0] = temp;
            answer[1] = temp;
            return answer;
        }
        findIndex(sequence, k);
        Index index = pq.poll();
        answer[0] = index.startIndex;
        answer[1] = index.endIndex;
        return answer;
    }

    public int findK(int[] sequence, int k) {
        int answer = -1;
        int start = 0;
        int end = sequence.length -1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(sequence[mid] < k) {
                start = mid + 1;
            }
            else if(sequence[mid] == k) {
                answer = mid;
                return answer;
            }
            else {
                end = mid - 1;
            }
        }
        return answer;
    }
    public void findIndex(int[] sequence, int k) {
        int halfKIndex = findIndexOverThanHalfK(sequence, k);
        int startIndex = halfKIndex + 1;
        int endIndex;
        int sum;
        while(startIndex >= 1) {
            startIndex--;
            sum = sequence[startIndex];
            for(int i = startIndex; i >= 1; i--) {
                int temp = sequence[i-1];
                if(sum + temp > k) {
                    break;
                } else if(sum + temp == k) {
                    endIndex = i-1;
                    pq.add(new Index(endIndex, startIndex));
                    break;
                } else {
                    sum += temp;
                }
            }
        }
    }

    public int findIndexOverThanHalfK(int[] sequence, int k) {
        double halfK = (double)k / 2;
        int start = 0;
        int end = sequence.length - 1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(sequence[mid] < halfK) {
                start = mid + 1;
            }
            else if(sequence[mid] > halfK) {
                end = mid - 1;
            }
            else {
                return mid;
            }
        }
        if(start == sequence.length) {
            return start -1;
        }
        return start+1;
    }
}
class Index {
    int startIndex;
    int endIndex;
    public Index(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
}
class IndexComparator implements Comparator<Index> {
    @Override
    public int compare(Index o1, Index o2) {
        int size_o1 = o1.endIndex - o1.startIndex;
        int size_o2 = o2.endIndex - o2.startIndex;
        if(size_o1 < size_o2) {
            return size_o1 - size_o2;
        }
        else if(size_o1 > size_o2) {
            return size_o1 - size_o2;
        }
        else {
            if(o1.startIndex < o2.startIndex) {
                return o1.startIndex - o2.startIndex;
            } else {
                return o2.startIndex - o1.startIndex;
            }
        }
    }
}
