public class Srm502Dev2Lev3{
	private static final int MOD = 1000000007;

	public static void main(String[] args){
		System.out.println(new Srm502Dev2Lev3().find(1000, 47));
	}

	public int find(int N, int K){
		int[][][] dp = new int[K][N][N];
		for(int i = 0; i < K; ++i){
			for(int j = 0; j < N; ++j){
				for(int k = 0; k < N; ++k){
					dp[i][j][k] = -1;
				}
			}
		}
		return find(N, K, 0, 0, dp);
	}

	private int find(int N, int K, int idxFrom, int curSum, int[][][] dp){
                curSum = curSum % N;
		if (K == 0 && curSum == 0){
			return 1;
		}
		if (K == 0 || idxFrom == N){
			return 0;
		}
                int mem = dp[K-1][idxFrom][curSum];
                if (mem > -1){
                        return mem;
                }
		int ret = 0;
		ret = (ret + find(N, K-1, idxFrom+1, curSum+idxFrom, dp)) % MOD;
		ret = (ret + find(N, K, idxFrom+1, curSum, dp)) % MOD;

		dp[K-1][idxFrom][curSum] = ret;
		return ret;
	}
}
