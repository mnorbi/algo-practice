/**
Find if Given number is power of 2 or not. 
More specifically, find if given number can be expressed as 2^k where k >= 1.

Input:

number length can be more than 64, which mean number can be greater than 2 ^ 64 (out of long long range)
Output:

return 1 if the number if a power of 2 else return 0

Example:

Input : 128
Output : 1
**/
public class Solution {
	public int power(String a) {
	    int[] arr = new int[a.length()];
	    for(int i = 0; i < a.length(); ++i){
	        arr[i] = a.charAt(i)-'0';
	    }
	    for(;;){
		boolean end = true;
		int cry = 0;
	    	for(int i = 0; i < arr.length; ++i){
    	           int s = (cry*10+arr[i]);
	    	   arr[i] = s/2;
	    	   cry = s%2;
    	           end = end && (arr[i] == 0 || (i == arr.length-1 && arr[i] == 1));
    	        }
    		if (cry != 0) return 0;
		if (end) return 1;
	    }
	}
}
