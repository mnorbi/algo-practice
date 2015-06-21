public class CountWaysOfChangingCoins{
  public static void main(String[]args){
    System.out.println(countOpt(10, new int[]{1,2}));
    System.out.println(countOpt(10, new int[]{1,2,3}));
    System.out.println(countOpt(10, new int[]{1,3,5}));
    System.out.println(countOpt(10, new int[]{1,4,7}));
    System.out.println(countOpt(10, new int[]{7,1,4}));

  }

  public static long countOpt(int N, int[] coins){
    long[] ways = new long[N+1];

    ways[0] = 1;

    for(int i = 0; i < coins.length; ++i){
      int coinValue = coins[i];
      for(int amount = coinValue; amount <= N; ++amount){
        ways[amount] += ways[amount-coinValue];
      }
    }
    return ways[N];
  }
 
  public static long count(int N, int[] coins){
    long[][] dp = new long[N+1][coins.length+1];

    for(int i = 0; i < dp.length; ++i){
      dp[i][0] = 0;
    }
    for(int i = 0; i < dp[0].length; ++i){
      dp[0][i] = 1;
    }

    for(int amount = 1; amount <= N; ++amount){
      for(int j = 1; j <= coins.length; ++j){
        int coinValue = coins[j-1];
        if (amount-coinValue >= 0){
          dp[amount][j] += dp[amount-coinValue][j];
        }
        dp[amount][j] += dp[amount][j-1];
      }
    }
    return dp[N][coins.length];
  }
}
