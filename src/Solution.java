
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int m, n;
    static int[][] graph;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    static int max = 0;
    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        Solution test = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = br.readLine();
        st = new StringTokenizer(input);
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[m][n];

        String input2 = br.readLine();
        st = new StringTokenizer(input2);
        while(st.hasMoreTokens()) {
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        for(int i = 0; i < m; i++) {
            stack.add(new Node(i,0,0));
        }
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            int x = node.x;
            int y = node.y;
            int sum = node.sum;

            if(x >= m || y >= n || x < 0 || y < 0) {
                continue;
            }
            
            test.find(x, y, sum);
        }

        System.out.println(max);
    }

    public void find(int i, int j, int sum) {
        sum += graph[i][j];
        for(int k = 0; k < 3; k++) {
            stack.add(new Node(i+dx[k], j+dy[k], sum));
        }
        max = Math.max(sum, max);

    }
}
class Node {
    int x;
    int y;
    int sum;

    Node(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}
