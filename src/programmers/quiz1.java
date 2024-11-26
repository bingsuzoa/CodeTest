package programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class quiz1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:SS");
        String answer = "";

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

        long ansTime = 0;

        for(int i = 0; i < commands.length; i++){
            String command = commands[i];
            if(command.equals("prev")){
                //10초 이하
                if(cTime <= tenSec){
                    answer = "00:00";
                } else {
                //10초 초과
                    ansTime = cTime - tenSec;
                }
            } else if(command.equals("next")) {
                //종료시간 10초
                if(cTime + tenSec >= vLength){
                    answer = video_len;
                }
                //오프닝 끝난 후, 오프닝 시작까지 10초남음
                if(cTime >= oeTime || cTime + tenSec < osTime){
                    ansTime = cTime + tenSec;
                } else {
                    ansTime = oeTime + tenSec;
                }

            }
        }
        long ansSec = (ansTime / 1000) % 60;
        long ansMin = ansTime / (1000 * 60) % 60;
        answer = ansMin + ":" + ansSec;

        return answer;
    }
}
