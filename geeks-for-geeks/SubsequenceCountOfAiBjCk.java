class SubsequenceCountOfAiBjCk{
	public static void main(String[]args){
		System.out.println(count("abbc"));
	}
	public static int count(String s){
		int[] dp = new int[3];
		for(int d = 0; d < s.length(); ++d){
			int id = s.charAt(d)-'a';
			if (id == 0) {dp[0] += (1+dp[0]);}
			else if (id < dp.length){
				dp[id] += (dp[id] + dp[id-1]);
			}
		}
		return dp[dp.length-1];
	}
}
