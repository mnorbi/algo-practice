class Question92{
	public static void main(String[] args){
		System.out.println(new Question92().solve(2,2));
		System.out.println(new Question92().solve(3,3, new java.util.BitSet[]{
                        new java.util.BitSet(),
			java.util.BitSet.valueOf(new long[]{1L<<2}),
                        new java.util.BitSet(),
                        new java.util.BitSet(),
                        new java.util.BitSet(),
                        new java.util.BitSet()
		}));
	}

	private int solve(int x, int y){
		int[][] dp = new int[2][y+1];
		for(int i = 0; i<2; i++){
			dp[i][0] = 1;
		}
		for(int i = 0; i<=y; i++){
			dp[0][i] = 1;
		}
		for(int i = 1; i<=x; i++){
			for(int j = 1; j<=y; j++){
				dp[i%2][j] = dp[(i-1)%2][j] + dp[i%2][j-1];
			}
		}
		return dp[x%2][y];
	}

	private int solve(int x, int y, java.util.BitSet[] constraint){
		int[][] dp = new int[x+1][y+1];

		if (constraint[0].get(0) || constraint[x].get(y)){
			return 0;
		}

		dp[0][0] =1;
		for(int j = 1; j<=y; j++){
			dp[0][j] = constraint[0].get(j) ? 0 : dp[0][j-1];
		}
		for(int i = 1; i<=x; i++){
			dp[i][0] = constraint[i].get(0) ? 0 : dp[i-1][0];
		}

		for(int i = 1; i<=x; i++){
			for(int j = 1; j<=y; j++){
				if (constraint[i].get(j)){
					dp[i][j] = 0;
					continue;
				}
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}

		return dp[x][y];
	}
}
