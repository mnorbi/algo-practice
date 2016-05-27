import java.util.*;
import java.io.*;
class Main{
  private static int MAX_DICE = 24;
  private static int MAX_SUM = 149;
  private static long[][] dp = new long[MAX_DICE+1][MAX_SUM+1];
  private static long[] pow6 = new long[MAX_DICE+1];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    computeDpTable();
    computePow6();
    while(true){
      String[] tokens = in.readLine().split(" ");
      int n = Integer.parseInt(tokens[0]);
      int x = Integer.parseInt(tokens[1]);
      if (n == 0 && x == 0) break;
      long nominator = 0;
      for(int a = x; a <= 6*n; ++a){
        nominator += dp[n][a];
      }
      long denominator = pow6[n];
      long gcd = gcd(nominator, denominator);
      nominator = Long.divideUnsigned(nominator,gcd);
      String nomStr = Long.toUnsignedString(nominator);
      sb.append(nomStr);
      denominator = Long.divideUnsigned(denominator,gcd);
      String denStr = Long.toUnsignedString(denominator);
      if (!nomStr.equals("0") && !denStr.equals("1")){
        sb.append('/').append(denStr);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
  private static long gcd(long a, long b){
    long ret = 1L;
    while(true){
      if (a == b) return a*ret;
      if ((a>>>1) == 0 || (b>>>1) == 0) return ret;
      if ((a&1) == 0 && (b&1) == 0){
        a = a>>>1; b = b>>>1;
        ret = ret << 1;
        continue;
      }
      if ((a&1) == 0 && (b&1) != 0){
        a = a>>>1;
        continue;
      }
      if ((a&1) == 1 && (b&1) == 0){
        b = b>>>1;
        continue;
      }
      if (Long.compareUnsigned(a, b) < 0){
        b = b-a;
        continue;
      } else {
        a = a-b;
      }
    }
  }
  private static void computePow6(){
    pow6[0] = 1;
    for(int a = 1; a < pow6.length; ++a){
      pow6[a] = (pow6[a-1]<<2)+(pow6[a-1]<<1);
    }
  }
  private static void computeDpTable(){
    for(int roll = 1; roll <= 6; ++roll){
      dp[1][roll] = 1;
    }
    for(int dices = 2; dices < dp.length; ++dices){
      for(int roll = 1; roll <= 6; ++roll){
        for(int sum = (dices-1)+roll; sum <= 6*(dices-1)+roll; ++sum){
          dp[dices][sum] += dp[dices-1][sum-roll];
        }
      }
    }
  }
}
