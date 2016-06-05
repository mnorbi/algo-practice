import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    for(int c = 1;; ++c){
      String[] tokens = in.readLine().split(" ");
      int Z = parseInt(tokens, 0);
      int I = parseInt(tokens, 1);
      int M = parseInt(tokens, 2);
      int L = parseInt(tokens, 3);
      if (Z == 0) break;
      int t = L; int h = L;
      do{
        t = (Z*t+I)%M;
        h = (Z*h+I)%M;
        h = (Z*h+I)%M;
      }while(t != h);
      int s = t;
      t = L;
      int ans = 0;
      do{
       t = (Z*t+I)%M;
       ++ans;
      }while(t != s);
      sb.append(String.format("Case %d: %d\n", c, ans));
    }
    System.out.print(sb);
  }
  private static int parseInt(String[] tokens, int idx){
    return Integer.parseInt(tokens[idx]);
  }
}
