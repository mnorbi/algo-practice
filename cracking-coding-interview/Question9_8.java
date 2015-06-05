import java.util.*;
public class Question9_8{
	public static void main(String[]args){
		System.out.println(countNumberOfWays(25, 0, new int[]{1, 5, 10, 25}));
		print(genNumberOfWays(25, new int[]{1, 5, 10, 25}));
	}
	private static void print(List<int[]> list){
		for(int[] coins : list){
			print(coins);
		}
		System.out.println();
	}
	private static void print(int[] coins){
		for(int i : coins){
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
	}
	private static int countNumberOfWays(int n, int idx, int[] coins){
		if(n == 0) return 1;
		if (idx >= coins.length) return 0;
		int ret = 0;
		int coinVal = coins[idx];
		for(int i = 0; i <= n/coinVal; ++i){
			ret += countNumberOfWays(n-i*coinVal, idx+1, coins);
		}
		return ret;
	}
	private static List<int[]> genNumberOfWays(int n, int[] coins){
		return genNumberOfWays(n, coins, 0, new int[coins.length]);
	}
	private static List<int[]> genNumberOfWays(int n, int[] coins, int idx, int[] coinCounts){
		if (n == 0){
			return Collections.singletonList(Arrays.copyOf(coinCounts, coinCounts.length));
		}
		if (idx >= coins.length) return Collections.emptyList();
		int coinValue = coins[idx];
		List<int[]> ret = new ArrayList<int[]>();
		for(int i = 0; i <= n/coinValue; ++i){
			coinCounts[idx] = i;
			ret.addAll(genNumberOfWays(n-i*coinValue, coins, idx+1, coinCounts));
		}
		coinCounts[idx] = 0;
		return ret;
	}
}
