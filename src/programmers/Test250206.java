package programmers;



public class Test250206 {
    int min = 50 * 25;

    public static void main(String[] args) {
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        int[] picks = {1,3,2};
        Test250206 test = new Test250206();
        System.out.println(test.solution(picks,minerals));
    }

    public int solution(int[] picks, String[] minerals) {
        self(minerals, 0, picks[0], picks[1], picks[2], 0);
        return min;
    }

    private void self(String[] minerals, int start, int diamond, int iron, int stone, int sum) {

        if(minerals.length <= start) {
            min = Math.min(min, sum);
            return;
        }

        if(diamond == 0 && iron == 0 && stone == 0) {
            min = Math.min(min, sum);
            return;
        }

        int length = Math.min(minerals.length, start + 5);

        if(diamond > 0) {
            int sub = length - start;
            self(minerals, length, diamond-1, iron, stone, sum+sub);
        }
        if(iron > 0) {
            int sub = 0;
            for(int i = start; i < length; i++) {
                if(minerals[i].equals("diamond")) {
                    sub += 5;
                } else {
                    sub++;
                }
            }
            self(minerals, length, diamond, iron-1, stone, sum+sub);
        }
        if(stone > 0) {
            int sub = 0;
            for(int i = start; i < length; i++) {
                if(minerals[i].equals("diamond")) {
                    sub += 25;
                } else if(minerals[i].equals("iron")) {
                    sub += 5;
                } else {
                    sub ++;
                }
            }
            self(minerals, length, diamond, iron, stone-1, sum + sub);
        }
    }
}
