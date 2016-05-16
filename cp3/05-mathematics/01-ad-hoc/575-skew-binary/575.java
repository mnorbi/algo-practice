import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      String next = in.readLine().trim();
      if ("0".equals(next)) break;
      int a = 0, b = 0;
      for(int c = 0; c < next.length(); ++c){
        int d = next.charAt(c)-'0';
        a <<= 1; a += d;
        b -= d;
      }
      a <<= 1; a += b;
      sb.append(a).append('\n');
    }
    System.out.print(sb);
  }
}
