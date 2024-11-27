package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class quiz1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) throws ParseException {

       LocalTime date1 = LocalTime.of(00, Integer.parseInt(video_len.split(":")[0]), Integer.parseInt(video_len.split(":")[1]));
       int vLength = date1.getMinute() * 60 + date1.getSecond();

       LocalTime date2 = LocalTime.of(00, Integer.parseInt(pos.split(":")[0]), Integer.parseInt(pos.split(":")[1]));
       int cMin = date2.getSecond();
       int cTime = date2.getMinute() * 60 + date2.getSecond();

       LocalTime date3 = LocalTime.of(00, Integer.parseInt(op_start.split(":")[0]), Integer.parseInt(op_start.split(":")[1]));
       int osTime = date3.getMinute() * 60 + date3.getSecond();

       LocalTime date4 = LocalTime.of(00, Integer.parseInt(op_end.split(":")[0]), Integer.parseInt(op_end.split(":")[1]));
       int oeTime = date4.getMinute() * 60 + date4.getSecond();

       LocalTime date5 = LocalTime.of(00,00,00);
       int sTime = date5.getMinute() * 60 + date5.getSecond();

        String answer = "";
        long tenSec = 10;

        for(int i = 0; i < commands.length; i++){
            String command = commands[i];
            if(command.equals("prev")){
                //10초 이하
                if(cTime <= 10){
                    cTime = sTime;
                } else {
                //10초 초과
                    cTime = cTime + 10;
                }
            } else if(command.equals("next")) {
                //종료시간 10초
                if(cTime + 10 >= vLength){
                    cTime = vLength;
                }
                //오프닝 끝난 후, 오프닝 시작까지 10초남음
                if(cTime >= oeTime || cTime + 10 < osTime){
                    cTime = cTime + 10;
                } else {
                    cTime = oeTime + 10;
                }

            }
        }
        int ansSec = (int)(cTime % 60);
        int ansMin = (int)(cTime / 60);
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
