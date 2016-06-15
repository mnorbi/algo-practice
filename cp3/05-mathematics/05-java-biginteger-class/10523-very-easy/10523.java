import java.util.*;
import java.io.*;
import java.math.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      String next = in.readLine();
      if (next == null || "".equals(next)) break;
      String[] tokens = next.trim().split(" ");
      int i = 0;
      for(;"".equals(tokens[i]);++i);
      int N = Integer.parseInt(tokens[i++]);
      for(;"".equals(tokens[i]);++i);
      int A = Integer.parseInt(tokens[i++]);
      BigInteger ans;
      if (A == 0) ans = BigInteger.ZERO;
      else if (A == 1) ans = BigInteger.valueOf((N*(N+1))/2);
      else ans = BigInteger.valueOf(A).pow(N+1).multiply(BigInteger.valueOf(A*N-N-1)).add(BigInteger.valueOf(A)).divide(BigInteger.valueOf((A-1)*(A-1)));
      sb.append(ans).append('\n');
    }
    System.out.print(sb);
  }
}
