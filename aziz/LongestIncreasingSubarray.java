/**
Problem 6.6, pg. 55: Design and implement an algorithm that takes as input an array A of
n elements, and returns the beginning and ending indices of a longest increasing subarray of
A.
**/
class LongestIncreasingSubarray{
  public static void main(String[]args){
  	longestIncreasingSubarrayNaive(new int[]{ 1, 4, 3, 2, 5, 6, 7, 8, 9, 1, 3 });
  }
  static void longestIncreasingSubarrayNaive(int[] arr){
	  if (arr == null || arr.length == 0) return;
	
	  int maxS = 0, maxE = 0, maxLen = 1;
	  for(int s = 0, e = 1; e < arr.length; ++e){
	    if (arr[e] <= arr[e-1]){
	      s = e;
	    }
	    if (e-s+1>maxLen){
	      maxLen = e-s+1;
	      maxS = s;
	      maxE = e;
	    }
	  }
	  System.out.printf("%d %d", maxS, maxE);
  }
}