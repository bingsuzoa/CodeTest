package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class quiz1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM:SS");

        Date date1 = sdf.parse(video_len);
        long vLength = date1.getTime();

        Date date2 = sdf.parse(pos);
        long cTime = date2.getTime();

        Date date3 = sdf.parse(op_start);
        long osTime = date3.getTime();

        Date date4 = sdf.parse(op_end);
        long oeTime = date4.getTime();

        Date date5 = sdf.parse("00:10");
        long tenSec = date5.getTime();

        Date date6 = sdf.parse("00:00");
        long sTime = date6.getTime();

        String answer = "";

        for(int i = 0; i < commands.length; i++){
            String command = commands[i];
            if(command.equals("prev")){
                //10초 이하
                if(cTime <= tenSec){
                    cTime = sTime;
                } else {
                //10초 초과
                    cTime = cTime - tenSec;
                }
            } else if(command.equals("next")) {
                //종료시간 10초
                if(cTime + tenSec >= vLength){
                    cTime = vLength;
                }
                //오프닝 끝난 후, 오프닝 시작까지 10초남음
                if(cTime >= oeTime || cTime + tenSec < osTime){
                    cTime = cTime + tenSec;
                } else {
                    cTime = oeTime + tenSec;
                }

            }
        }
        int ansSec = (int)(cTime / 1000) % 60;
        int ansMin = (int)(cTime / (1000 * 60)) % 60;
        answer = ansMin + ":" + ansSec;

        return answer;
    }

    public static void main(String[] args){
        String video_len = "34:33";
        String pos = "13:00";
        String op_start = "00:55";
        String op_end = "02:55";
        String[] commands = {"next", "prev"};

        try {
            quiz1 solution1 = new quiz1();
            String answer = solution1.solution(video_len, pos, op_start, op_end, commands);
            System.out.println(answer);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
