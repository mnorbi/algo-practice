import java.util.*;

class Question98{
	public static void main(String[] args){
		Collection<int[]> reprs = centRepresentations(25, 0);
		for(int[] repr : reprs){
			print(repr);
		}
	}

	static void print(int[] arr){
		for(int i : arr){
			System.out.print(i + " ");
		}
		System.out.println();
	}

	static int[] coins = new int[] {25, 10, 5, 1};

	static List<int[]> centRepresentations(int n, int fromCoinIdx){
		if (n <= 0) return Collections.emptyList();
		if (fromCoinIdx >= coins.length) return Collections.emptyList();

		List<int[]> ret = new ArrayList<int[]>();
		int coin = coins[fromCoinIdx];
		int coinSum = 0, coinCount = 0;
		for(; coinSum < n; coinCount += 1, coinSum += coin){
			for(int[] subRepr : centRepresentations(n-coinSum, fromCoinIdx+1)){
				int[] repr = Arrays.copyOf(subRepr, subRepr.length + coinCount);
				Arrays.fill(repr, subRepr.length, subRepr.length + coinCount, coin);
				ret.add(repr);
			}
			
		}
		if (coinSum == n){
			int[] repr = new int[coinCount];
			Arrays.fill(repr, coin);
			ret.add(repr);
		}
		return ret;
	} 
}
