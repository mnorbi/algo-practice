import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int tcc = Integer.parseInt(in.readLine().trim());
    StringBuilder sb = new StringBuilder();
    for(int a = 1; a <= tcc; ++a){
      int N = Integer.parseInt(in.readLine().trim());
      long ans = 0L;
      int b = 1;
      for(int prev = N;; ++b){
        int next = N/b;
        ans += (prev-next)*(b-1);
        if (b <= next) {
          ans += next;
        }
        if (b >= next){
          break;
        }
        prev = next;
      }
      sb.append(ans).append('\n');
    }
    System.out.print(sb);
  }
}
