package programmers;

import java.text.ParseException;
import java.time.LocalTime;

public class quiz1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) throws ParseException {

        LocalTime date1 = LocalTime.of(0, Integer.parseInt(video_len.split(":")[0]), Integer.parseInt(video_len.split(":")[1]));
        int vLength = date1.getMinute() * 60 + date1.getSecond();

        LocalTime date2 = LocalTime.of(0, Integer.parseInt(pos.split(":")[0]), Integer.parseInt(pos.split(":")[1]));
        int cTime = date2.getMinute() * 60 + date2.getSecond();

        LocalTime date3 = LocalTime.of(0, Integer.parseInt(op_start.split(":")[0]), Integer.parseInt(op_start.split(":")[1]));
        int osTime = date3.getMinute() * 60 + date3.getSecond();

        LocalTime date4 = LocalTime.of(0, Integer.parseInt(op_end.split(":")[0]), Integer.parseInt(op_end.split(":")[1]));
        int oeTime = date4.getMinute() * 60 + date4.getSecond();

        LocalTime date5 = LocalTime.of(0, 0, 0);
        int sTime = date5.getMinute() * 60 + date5.getSecond();

        String answer;

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            //현재 위치가 오프닝구간인경우
            if (cTime >= osTime && cTime <= oeTime) {
                cTime = oeTime;
            }
                if (command.equals("prev")) {
                    if (cTime - 10 < 10) {
                        cTime = sTime;
                    } else if (cTime < osTime && cTime >= 20) {
                        cTime = cTime - 10;
                    } else if (cTime <= oeTime + 10) {
                        cTime = oeTime;
                    } else {
                        cTime = cTime - 10;
                    }
                } else if (command.equals("next")) {
                    if (cTime + 10 > vLength - 10) {
                        cTime = vLength;
                    } else if (oeTime <= cTime && cTime <= vLength - 10) {
                        cTime = cTime + 10;
                    } else if (cTime + 10 < osTime) {
                        cTime = cTime + 10;
                    } else if (cTime + 10 >= osTime && cTime + 10 <= oeTime) {
                        cTime = oeTime;
                    } else {
                        cTime = cTime + 10;
                    }
                }
            }
            int ansSec = cTime % 60;
            String ansSec2;
            String ansMin2;
            if (ansSec < 10) {
                ansSec2 = "0" + ansSec;
            } else {
                ansSec2 = String.valueOf(ansSec);
            }

            int ansMin = cTime / 60;
            if (ansMin < 10) {
                ansMin2 = "0" + ansMin;
            } else {
                ansMin2 = String.valueOf(ansMin);
            }
            answer = ansMin2 + ":" + ansSec2;
            return answer;
        }

    public static void main(String[] args){
        String video_len = "07:22";
        String pos = "04:05";
        String op_start = "00:15";
        String op_end = "04:07";
        String[] commands = {"next"};

        try {
            quiz1 solution1 = new quiz1();
            String answer = solution1.solution(video_len, pos, op_start, op_end, commands);
            System.out.println(answer);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
