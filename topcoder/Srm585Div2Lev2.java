import java.util.*;
public class Srm585Div2Lev2{
	public long theMinCars1(int treeHeight){
		long[] dp = new long[treeHeight+1];
		if (treeHeight == 0) return 1;

		dp[0] = 1L;
		dp[1] = 1L;

		for(int i = 2; i <= treeHeight; i++){
			dp[i] = Math.min(4L*dp[i-2]+1L,2L*dp[i-1]+1L);
		}
		return dp[treeHeight];
	}



//static int MOD = 1000000007;
long[] f = new long[1000001];
long[] sum = new long[1000001]; //sum[i] stores the sum of f[0] + f[1] + ... +  f[i]
 
long theMinCars(int treeHeight)
{
    f[0] = 1;
    sum[0] = 1;
    f[1] = 1;
    sum[1] = 2;
    for (int t=2; t<=treeHeight; t++) {
        f[t] = (1 + 2 * sum[t-2]);// % MOD;
        sum[t] = (f[t] + sum[t-1]);// % MOD;
         
        // We need to find the (result % MOD).
        // Remember that (a + b) % MOD = (a % MOD + b % MOD) % MOD, hence we
        // only need to remember the partial results % MOD when doing the calculations
    }
    return f[treeHeight];
}	


	public static void main(String[] args){
		Srm585Div2Lev2 solver = new Srm585Div2Lev2();
		for (int i = 0; i <= 60; i++){
			long expected = solver.theMinCars(i);
			long actual = solver.theMinCars1(i);
			if (expected != actual){
				System.out.println(String.format("Height [%d]. Expected [%d], actual[%d]", i, expected, actual));
			}
		}
	}
}
