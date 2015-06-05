import java.util.*;
class LogCutting{
  public static void main(String[]args){
    System.out.println(findMinCost(new int[] {1, 3, 7, 10},15));
    System.out.println(findMinCost(new int[] {}, 10));
    System.out.println(findMinCost(new int[] {1}, 10));
  }
/**
M => N = M+2: markings + left and right ends

0,1, … N-1 new markings
dp[i][j] = optimum cost cutting up between markings i and j
dp[i][j] = |i-j| < 2 => 0
             k: i+1 .. j-1  min(dp[i][k] + dp[k][j] + logLen(i,j))
betweenMarkCount: 1 .. M
O(M*(M+1))==>O(M^2)
logLen[j]-logLen[i]
**/
  private static int findMinCost(int[] markings, int len){
    int[] logLen = logLen(markings,len);
    int[][] dp = new int[markings.length+2][markings.length+2];
    for(int bmc = 1; bmc <= markings.length; ++bmc){
      for(int i = 0;; ++i){
        int j = i+bmc+1;
        if (j >= dp.length) break;
        int min = Integer.MAX_VALUE;
        int cost = logLen[j]-logLen[i];
        for(int k = i+1; k <= j-1; ++k){
          min = Math.min(min, dp[i][k] + dp[k][j] + cost);
        }
        dp[i][j] = min;
      }
    }
    return dp[0][dp.length-1];
  }

  private static int[] logLen(int[] markings, int len){
    int[] ret = new int[markings.length+2];
    for(int i = 1; i <= markings.length; ++i){
      ret[i] =markings[i-1];
    }
    ret[ret.length-1] = len;
    return ret;
  }
}
