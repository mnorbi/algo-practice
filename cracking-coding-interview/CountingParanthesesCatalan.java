public class CountingParanthesesCatalan{
  public static void main(String[]args){
    System.out.println(catalanNumber(3));
  }
  static int catalanNumber(int n){
    int[] dp = new int[n+1];
    dp[0] = 1;
    for(int i = 1; i <= n; ++i){
      for(int j = 0; j < i; ++j){
        dp[i] += dp[j]*dp[i-j-1];
      }
    }
    return dp[n];
  }
}
