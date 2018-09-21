import java.util.*;
import java.io.*;
/*
  m <= n
  dp[n][m] = n long, m uniq
	m*(dp[n-1][m-1] + (m<=n-1)? dp[n-1][m] : 0)
*/
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.valueOf(br.readLine());
            for (int i = 1; i <= t; ++i) {
                int MOD = 1_000_000_007;
                int[] arr = toIntArr(br.readLine());
                int M = arr[0];
                int N = arr[1];
                long[] dp = new long[M+1];
                dp[1] = 1;
                for(int n = 2; n <= N; ++n){
                    long pre = 1;
                    for(int m = 2; m <= Math.min(M, n); ++m){
                        long tmp = dp[m];
                        if (n <= m){
                            dp[m] = 0;
                        }
                        dp[m] += pre;
                        dp[m] *= m;
                        dp[m] %= MOD;
                        pre = tmp;
                    }
                }
                System.out.println("Case #" + i + ": " + dp[M]);
            }
        }
    }

    static int[] toIntArr(String s) {
        return Arrays.stream(s.split("\\s+")).mapToInt(Integer::valueOf).toArray();
    }
}
