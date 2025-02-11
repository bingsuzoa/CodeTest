package programmers;


import java.io.*;
import java.util.*;

public class Test250211 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        LinkedList<Student> list = new LinkedList<>();

        int number = Integer.parseInt(br.readLine());
        for(int i = 0; i < number; i++) {
            String input = br.readLine();
            st = new StringTokenizer(input);
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            list.add(new Student(name, korean, english, math));
        }
        Collections.sort(list);
        for(int i =0; i < list.size(); i++) {
            System.out.println(list.get(i).name);
        }
    }


}

class Student implements Comparable<Student> {
    String name;
    int korean;
    int english;
    int math;

    Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Student s) {
        if(s.korean == this.korean && s.english == this.english && s.math == this.math) {
            return this.name.compareTo(s.name);
        }
        if(s.korean == this.korean && s.english == this.english) {
            return s.math - this.math;
        }
        if(s.korean == this.korean) {
            return this.english - s.math;
        }
        return s.korean - this.korean;
    }
}

