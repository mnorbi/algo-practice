public class MinNoCoinToChange{
  public static void main(String[]args){
    int[] coins = new int[]{1,3,5,7};
    String coinsString = toString(coins);
    for(int i = 1; i < 20; ++i){
      System.out.printf("Change for [%d] using coins[%s], coins used[%d].\n", i, coinsString, minNoCoins(i, coins));
      printMinItemChange(i, coins);
      System.out.println();
    }
  }
  
  private static long minNoCoins(int N, int[] coins){
    long[] dp = new long[N+1];

    dp[0] = 0;

    for(int i = 1; i < dp.length; ++i){
      dp[i] = Integer.MAX_VALUE;
    }

    for(int i = 0; i < coins.length; ++i){
      int coinValue = coins[i];
      for(int amount = coinValue; amount <= N; ++amount){
        dp[amount] = Math.min(dp[amount], dp[amount-coinValue]+1); 
      }
    }

    return dp[N];
  }

  private static void printMinItemChange(int N, int[] coins){
    long[] dp = new long[N+1];
    int[] coinId = new int[N+1];

    dp[0] = 0;
    for(int i = 0; i < coins.length; ++i){
      int coinValue = coins[i];
      for(int amount = coinValue; amount <= N; ++amount){
        long v = dp[amount-coinValue]+1;
        if (dp[amount] == 0 || dp[amount] > v){
          dp[amount] = v;
          coinId[amount] = coinValue;
        }
      }
    }

    int[] ret = new int[(int)dp[N]];

    for(int amount = N, k = ret.length-1; k >= 0;--k){
      ret[k] = coinId[amount];
      amount = amount - coinId[amount];
    }

    print(ret);
  }
  private static String toString(int[] arr){
    if (arr == null || arr.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    for(int i : arr){
      sb.append(i);
      sb.append(" ");
    }
    return sb.toString();
  }
  private static void print(int[] arr){
    for(int i : arr){
      System.out.print(i+" ");
    }
    System.out.println();
  }
}
