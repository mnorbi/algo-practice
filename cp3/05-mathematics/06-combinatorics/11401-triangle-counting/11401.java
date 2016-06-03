import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){ 
      int N = Integer.parseInt(in.readLine().trim());
      if (N < 3) break;
      long k = N/2;
      long ans;
      if (N%2 == 0){
        ans = (k*(k-1)*(4*k-5))/6;
      } else {
        ans = (k*(k-1)*(4*k+1))/6;
      }
      sb.append(ans).append('\n');
    }
    System.out.print(sb);
  }
}
