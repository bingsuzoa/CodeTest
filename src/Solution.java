import java.util.*;

public class Solution {
    static List<Integer> list = new ArrayList<>();
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Solution test = new Solution();
        int n = 5;
        String input = "R R R U D D";
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            String direction = st.nextToken();
            if(direction.equals("D")) {
                list.add(0);
            }
            if(direction.equals("U")) {
                list.add(1);
            }
            if(direction.equals("R")) {
                list.add(2);
            }
            if(direction.equals("L")) {
                list.add(3);
            }
        }
        queue.add(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            int plan = node.plan;

            if (x >= n || y >= n || x < 0 || y < 0) {
                int before_plan = list.get(plan-1);
                x = x -dx[before_plan];
                y = y - dy[before_plan];
            }
            if (plan == list.size()) {
                x++;
                y++;
                System.out.println(x + " " + y);
            } else {
                test.move(x, y, plan);
            }
        }


    }

    public void move(int x, int y, int plan) {
        int direction = list.get(plan);
        if (direction == 2) {
            queue.add(new Node(x + dx[2], y + dy[2], plan + 1));
        }
        if (direction == 3) {
            queue.add(new Node(x + dx[3], y + dy[3], plan + 1));
        }
        if (direction == 1) {
            queue.add(new Node(x + dx[1], y + dy[1], plan + 1));
        }
        if (direction == 0) {
            queue.add(new Node(x + dx[0], y + dy[0], plan + 1));
        }
    }
}

class Node {
    int x;
    int y;
    int plan;

    Node(int x, int y, int plan) {
        this.x = x;
        this.y = y;
        this.plan = plan;
    }
}