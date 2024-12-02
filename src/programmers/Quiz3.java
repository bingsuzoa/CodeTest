package programmers;

public class Quiz3 {
    public int solution(int[][] points, int[][] routes){
        int answer = 0;
        return answer;
    }

    public void move(){
        // 같은 루트로 이동하는 경우에는 충돌하지 않음
        // 충돌가능성이 있는 경우
        // <아래-> 위> <위 -> 아래>
        // <왼 -> 오> <오 -> 왼>
        // <위 -> 아래> <왼 -> 오>, < 오-> 왼>
        // <아래 -> 위> <왼 -> 오>, < 오 -> 왼>
    }

    public static void main(String[] args){
        int[][] points = {{3,6,4,1}, {2,4,7,4}};
        int[][] routes = {{4,1,4,4}, {2,3,2,3}};
    }


}
