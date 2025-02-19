import java.util.*;

public class Solution {
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) {
        Solution test = new Solution();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        char before = input.charAt(0);
        int n = before - 'a';
        int m = input.charAt(1) - '1';
        
        int[][] graph = new int[8][8];

        test.go(graph, m, n);

        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            if (x >= 8 || y >= 8 || x < 0 || y < 0) {
                continue;
            }
            count++;
        }
        System.out.println(count);

    }

    public void go(int[][] graph, int m, int n) {
        queue.add(new Node(m - 1, n + 2));
        queue.add(new Node(m - 1, n - 2));
        queue.add(new Node(m + 1, n + 2));
        queue.add(new Node(m + 1, n - 2));
        queue.add(new Node(m + 2, n + 1));
        queue.add(new Node(m + 2, n - 1));
        queue.add(new Node(m - 2, n + 1));
        queue.add(new Node(m - 2, n - 1));
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}