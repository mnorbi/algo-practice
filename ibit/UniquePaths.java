class UniquePaths{
        public static void main(String[]args){
          System.out.println(uniquePaths(3,2));
        }
	static int uniquePaths(int a, int b) {
	    return combinations(a-1+b-1, b-1);
	}
	static int combinations(int n, int k){
	    int[][] dp = new int[2][k+1];
	    //C(n,k) = C(n-1, k) + C(n-1,k-1)
	    dp[0][0] = 1;
	    dp[1][0] = 1;
	    for(int i = 1; i <= n; ++i){
	        for(int j = 1; j <= k; ++j){
	            dp[i%2][j] = dp[(i-1)%2][j] + dp[(i-1)%2][j-1];
	        }
	    }
	    return dp[n%2][k];
	}
}
