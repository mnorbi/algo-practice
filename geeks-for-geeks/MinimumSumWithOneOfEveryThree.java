// A Dynamic Programming based java program to
// find minimum possible sum of elements of array
// such that an element out of every three
// consecutive is picked.
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

class MinimumSumWithOneOfEveryThree 
{
	// A utility function to find minimum of
	// 3 elements
	static int minimum(int a, int b, int c)
	{
		return Math. min(Math.min(a, b), c);
	}
	
	// Returns minimum possible sum of elements such
	// that an element out of every three consecutive
	// elements is picked.
	static int findMinSum1(int arr[], int n)
	{
		// Create a DP table to store results of
		// subpriblems. sum[i] is going to store
		// minimum possible sum when arr[i] is
		// part of the solution.
		int sum[] =new int[n];
	
		// When there are less than or equal to
		// 3 elements
		sum[0] = arr[0];
		sum[1] = arr[1];
		sum[2] = arr[2];
	
		// Iterate through all other elements
		for (int i = 3; i < n; i++)
		sum[i] = arr[i] + minimum(sum[i - 3], 
						sum[i - 2], sum[i - 1]);
	
		return minimum(sum[n - 1], sum[n - 2], sum[n - 3]);
	}
	
	static int findMinSum2(int[]arr, int n){
	    int[] dp = new int[n];
	    Arrays.fill(dp,0);
	    dp[n-3] = minimum(arr[n-3],arr[n-2],arr[n-1]);
	    for(int a = n-4; a >= 0; --a){
	        dp[a] = minimum(arr[a]+dp[a+1], arr[a+1]+dp[a+2], arr[a+2]+dp[a+3]);
	    }
	    return dp[0];
	}
	
	// Driver code
	public static void main (String[] args) 
	{
		while(true){
			int[] arr = rndArr(rnd(3,101), 0, 100);
			int ans1 = findMinSum1(arr, arr.length);
			int ans2 = findMinSum2(arr, arr.length);
			if (ans1 != ans2){
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(Arrays.toString(arr));
				throw new RuntimeException();
			}
		}
	}
	static int[] rndArr(int size, int min, int max){
		int[] arr = new int[size];
		for(int a = 0; a < arr.length; ++a){
			arr[a] = rnd(min, max);
		}
		return arr;
	}
	static int rnd(int min, int max){
		return ThreadLocalRandom.current().nextInt(Math.min(min,max), Math.max(min, max));
	}
}

// This code is contributed by vt_m

