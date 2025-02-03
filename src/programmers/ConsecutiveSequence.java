package programmers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ConsecutiveSequence {
    PriorityQueue<Index> pq = new PriorityQueue<>(1, new IndexComparator());

    public static void main(String[] args) {
        ConsecutiveSequence test = new ConsecutiveSequence();
        int[] sequence = {2,2,2,5,5};
        int k = 2;
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
        //이미 조건에 맞게 정렬해놓은 우선순위큐에서 하나를 꺼내어 답으로 도출한다.
        Index index = pq.poll();
        answer[0] = index.startIndex;
        answer[1] = index.endIndex;
        return answer;
    }
    //1. k와 같은 값이 있는지 확인(이진탐색 활용)
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
                end = mid - 1;
            }
            else {
                end = mid - 1;
            }
        }
        if(end == -1) {
            return 0;
        }
       return answer;
    }
    // 2-2. halfIndex를 구했다면 그 뒤 index는 볼 필요 없다(절반보다 큰 수들의 연속된 합은 k보다 무조건 크니깐)
    // halfIndex부터 차례로 하나씩 내려오며 연속된 합이 k와 같은지 구한다.
    // 같은 경우 우선순위 큐에 담는다.(우선순위 큐는 index출력 조건에 맞추어 overriding한 상태)
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
    // 2-1. k와 같은 값이 없을 때 k의 절반 값(halfK)을 구한 후,
    // sequence[index] >= halfK, sequence[index-1] < halfK 인 index 구한다.
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
        return start;
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
            return o1.startIndex - o2.endIndex;
        }
        else if(size_o1 > size_o2) {
            return o2.startIndex - o1.startIndex;
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