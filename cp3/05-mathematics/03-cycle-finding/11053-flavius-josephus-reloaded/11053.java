import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      String[] tokens = in.readLine().trim().split(" ");
      int N = Integer.parseInt(tokens[0]);
      if (N == 0) break;
      int a = Integer.parseInt(tokens[1]);
      int b = Integer.parseInt(tokens[2]);
      long t = 0L;
      long h = 0L;
      do{
        t = next(t, a, b, N);
        h = next(h, a, b, N);
        h = next(h, a, b, N);
      } while(t != h);
      int died = 0;
      do{
         h = next(h, a, b, N);
         ++died;
      }while(t != h);
      sb.append(N-died).append('\n');
    }
    System.out.print(sb);
  }
  private static long next(long x, long a, long b, long N){
    return ((a*((x*x)%N)%N)+b)%N;
  }
}
