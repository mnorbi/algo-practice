class NChooseK{
	public static void main(String[] args){
		System.out.println(nChooseK(10,2));
	}
	private static int nChooseK(int n, int k){
		int[][] dp = new int[2][k+1];
		
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= Math.min(k,i); j++){
				int val;
				if (i == j){
					val = 1;
				} else if (j == 1){
					val = i;
				} else {
					val = dp[(i-1)%2][j] + dp[(i-1)%2][j-1];
				}
				dp[i%2][j] = val;
			}
		}
		return dp[n%2][k];
	}
}
