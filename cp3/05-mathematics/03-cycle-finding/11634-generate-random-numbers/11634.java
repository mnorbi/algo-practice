import java.util.*;
import java.io.*;
class Main{
  private static final int[] dp = new int[10000];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    precompute();
    while(true){
      int a = Integer.parseInt(in.readLine().trim());
      if (a == 0) break;
      sb.append(dp[a]).append('\n');
    }
    System.out.print(sb);
  }
  private static void precompute(){
    for(int a = 0; a < 10000; ++a){//mistake: ;a < 1000;
      if (dp[a] != 0) continue;
      int lam = 1;
      for(int b = a, c = next(b), pow = 1; b != c;){
        //mistake: ++lam;
        if (lam == pow){
          pow <<= 1;
          lam = 0;
          b = c;
        }
        c = next(c);
        ++lam;
      }
      int mu = 0;
      for(int b = a, c = seek(b, lam); b != c; ++mu){
        dp[c] = lam;
        b = next(b);
        c = next(c);
      }
      for(int b = a; mu >= 0; --mu){
        dp[b] = mu+lam;
        b = next(b);
      }
    }
  }
  private static int seek(int a, int seek){
    while(seek-- > 0){//mistake: --seek 
      a = next(a);
    }
    return a;
  }
  private static int next(int a){
    int b = a*a;
    b/= 100;
    if (b == 0) return 0;
    int ans = 0;
    for(int c = 1; c < 10000; c *= 10){
      ans += c*(b%10);
      b /= 10;
    }
    return ans;
  }
}
