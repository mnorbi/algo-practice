// Constraints:
//	1) 1 <= numbers <= 10
//	2) '0' <= d <= '9'
//	3) 0 <= sum <= 100
//	4) leading zeros do not change the result
// Ideas:
//	1) brute force: 
//		generate all possible "+" insertions
//		check if summing equals to sum
//		record if number of "+" decreased 
//
//		time:  O(2^(numbers-1)) = O(2^9) = O(512)
//		space: O(numbers)
//	2) dp:
//		ms = minimum number of additions
//			min(
//				for all possible numbers splits
//					for all possible sum splits
//						ms[firstNumbersSplit,firstSumSplit]+ms[secondNumbersSplit,secondSumSplit]+1
//			)
//		time:  numbersLength^2*sum
//		space: same
public class QuickSums {
	public int minSums(String digits, int sum){
		int N  = digits.length();
		if (digits == null || N == 0){
			return -1;
		}
		int[][][] dp = new int[N][N][sum+1];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				for(int k = 0; k <= sum; k++){
					dp[i][j][k] = -2;
				}
			}
		} 
		minSums(digits.toCharArray(), 0, N-1, sum, dp);
                for(int i = 0; i < N; i++){
                        for(int j = i; j < N; j++){
//                                for(int k = 0; k <= sum; k++){
//                                        System.out.println(String.format("'%s*%s*%s',%d=%d", (i>0?digits.substring(0,i):""),digits.substring(i, j+1),(j+1)<N?digits.substring(j+1):"", k, dp[i][j][k]));
//                                }
			}
 
                }

		return dp[0][N-1][sum];
	}

	private int minSums(char[] digits, int fr, int to, int sum, int[][][] dp){
		if (dp[fr][to][sum] > -2){
                        return dp[fr][to][sum];
                }
		if (checkMatch(digits, fr, to, sum)){
			dp[fr][to][sum] = 0;
			return 0;
		}
		int min = Integer.MAX_VALUE; boolean summable = false;
		for(int i=fr+1;i<=to && min > 0;i++){
			for(int j=0;j<=sum && min > 0;j++){
				int ms1 = minSums(digits, fr, i-1, j, dp);
				if (ms1 > -1){
					int ms2 = minSums(digits, i, to, sum-j, dp);
					if (ms2 > -1){
						min = Math.min(min, ms1+ms2+1);
						summable = true;
					}
				}
			}
		}
		if (!summable){
			min = -1;
		}
		dp[fr][to][sum] = min;
		return min;
	}

	private boolean checkMatch(char[] digits, int fr, int to, int sum){
		long parsed = parse(digits, fr, to);
		return parsed == sum;
	}

	private long parse(char[] digits, int fr, int to){
		long ret = 0L;
		for(int i = fr; i<=to; i++){
			ret = 10*ret + (digits[i]-'0');
		}
		return ret;
	}

	public static void main(String[] args){
		QuickSums qs = new QuickSums();
		System.out.println(qs.minSums("99999", 45));
                System.out.println(qs.minSums("1110", 3));
                System.out.println(qs.minSums("0123456789", 45));
                System.out.println(qs.minSums("99999", 10));
                System.out.println(qs.minSums("382834", 100));
                System.out.println(qs.minSums("9230560001", 71));

	}
}
