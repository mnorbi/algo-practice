import java.util.*;
import java.io.*;
class Solver{
    private static final int MOD = 1_000_000_007;
  public static void main(String[]args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            int N = Integer.valueOf(br.readLine());
            int[] arr = new int[N];
            for(int a = 0; a < N; ++a){
                arr[a] = br.readLine().equals("s") ? 0 : 1;
            }
            int[][]dp=new int[N][N+1];
            int maxDepth = 0;
            dp[0][0]=1;
            for(int pos = 1; pos < N; ++pos){
                maxDepth += arr[pos-1];
                for(int a = maxDepth; a>=0; --a){
                    if (arr[pos-1] == 1){//f
                        dp[pos][a]=a==0?0:dp[pos-1][a-1];
                    } else {
                        dp[pos][a]=dp[pos][a+1]+dp[pos-1][a];
                    }
                    dp[pos][a] %= MOD;
                }
            }
            int ans = 0;
            for(int a = 0; a < N; ++a){
                ans += dp[N-1][a];
                ans %= MOD;
            }
            System.out.println(""+ans);
        }
    }
}
