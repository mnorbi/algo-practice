import java.util.*;

public class Srm169Div1Lev2{
	public static void main(String[] args){
		FairWorkload fw = new FairWorkload();
		System.out.println(
			fw.getMostWork(
				new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 },
				3
			));
	}
}

class FairWorkload{
	public int getMostWork(int[] folders, int workers){
		int n = folders.length;
		int[][] dp = new int[workers+1][];
		dp[0] = new int[n+1];
                dp[1] = new int[n+1];
		for(int i = 1; i <= n; i++){
                        dp[1][i] = dp[1][i-1] + folders[i-1];
                }
		for(int wCount = 2; wCount <= workers; wCount++){
			dp[wCount] = new int[n+1];
			for(int fCount = wCount; fCount <= n; fCount++){
				dp[wCount][fCount] = Integer.MAX_VALUE;
				for(int k = fCount-1; k >= wCount-1; k--){
					dp[wCount][fCount] = 
						Math.min(
							dp[wCount][fCount],
							Math.max(dp[1][fCount]-dp[1][k], dp[wCount-1][k]));
				}
			}
		}

		return dp[workers][n];
	}

	private void print(int[][] dp){
		for(int[] arr : dp){
			for(int i : arr){
				System.out.print(i);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
