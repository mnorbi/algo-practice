import java.util.concurrent.*;

public class Kadane{
	public static void main(String[]args){
		byte[] arr = randomBytes(10);
		print(arr, 0, arr.length);
		bruteForceMaxSubarraySearch(arr);
		substractMinBasedMaxSubbaraySearch(arr);	
	}
	private static void kadaneMaxSubarraySearch(byte[] arr){
		
	}
	private static void substractMinBasedMaxSubbaraySearch(byte[] arr){
		int maxS = -1, maxE = -1, maxSum = Integer.MIN_VALUE, minPfxSum = 0;
		for(int i = 0, s = 0, pfxSum = 0; i < arr.length; ++i){
			pfxSum += arr[i];
			if (maxSum < pfxSum - minPfxSum){
				maxSum = pfxSum - minPfxSum;
				maxS = s;
				maxE = i+1;
			}
			if (minPfxSum > pfxSum){
				minPfxSum = pfxSum;
				s = i+1;
			}
		}
		print(arr, maxS, maxE);
	}
	private static void bruteForceMaxSubarraySearch(byte[] arr){
		int[] pfxSum = pfxSum(arr);
		int maxS = 0, maxE = 0, maxSum = arr[0];
		for(int i = 0; i < arr.length; ++i){
			for(int j = i+1; j <= arr.length; ++j){
				int rangeSum = pfxSum[j] - pfxSum[i];
				if (rangeSum > maxSum){
					maxSum = rangeSum;
					maxS = i;
					maxE = j;
				}
			}
		}
		print(arr, maxS, maxE);
	}
	private static int[] pfxSum(byte[] arr){
		int[] ans = new int[arr.length+1];
		ans[0] = 0;
		for(int i = 0; i < arr.length; ++i){
			ans[i+1] = ans[i]+arr[i];
		}
		return ans;
	}
	private static byte[] randomBytes(int len){
		byte[] ans = new byte[len];
		ThreadLocalRandom.current().nextBytes(ans);
		return ans;
	}
	public static void print(byte[] arr, int from, int to){
		for(int i = from; i < to; ++i){
			System.out.printf(" %d", arr[i]);
		}
		System.out.println();
	}
}
