package backjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class quiz28279 {

   public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       StringTokenizer st;
       Deque<Integer> deck = new ArrayDeque<>();

       int a = Integer.parseInt(br.readLine());
       for(int i = 0; i < a; i++){
           st = new StringTokenizer(br.readLine());
           int N = Integer.parseInt(st.nextToken());

           switch(N){
               case 1 :
                   int X = Integer.parseInt(st.nextToken());
                   deck.addFirst(X);
                   break;
               case 2 :
                   int Y = Integer.parseInt(st.nextToken());
                   deck.addLast(Y);
                   break;
               case 3 :
                    if(!deck.isEmpty()){
                        bw.write(String.valueOf(deck.pollFirst()) + "\n");
                        bw.flush();
                        break;
                    } else {
                        bw.write("-1" + "\n");
                        bw.flush();
                        break;
                    }
               case 4 :
                   if(!deck.isEmpty()){
                       bw.write(String.valueOf(deck.pollLast()) + "\n");
                       bw.flush();
                       break;
                   } else {
                       bw.write("-1"+ "\n");
                       bw.flush();
                       break;
                   }
               case 5 :
                   bw.write(String.valueOf(deck.size()) + "\n");
                   bw.flush();
                   break;
               case 6 :
                   if(deck.isEmpty()){
                       bw.write("1"+ "\n");
                       bw.flush();
                       break;
                   } else {
                       bw.write("0"+ "\n");
                       bw.flush();
                       break;
                   }
               case 7 :
                   if(!deck.isEmpty()){
                       bw.write(String.valueOf(deck.getFirst())+ "\n");
                       bw.flush();
                       break;
                   } else {
                       bw.write("-1"+ "\n");
                       bw.flush();
                       break;
                   }
               case 8 :
                   if(!deck.isEmpty()){
                       bw.write(String.valueOf(deck.getLast())+ "\n");
                       bw.flush();
                       break;
                   } else {
                       bw.write("-1"+ "\n");
                       bw.flush();
                       break;
                   }
           }
       }
       bw.close();




   }
}
