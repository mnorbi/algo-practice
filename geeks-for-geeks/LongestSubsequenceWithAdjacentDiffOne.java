import java.util.*;
class LongestSubsequenceWithAdjacentDiffOne{
	public static void main(String[]args){
		System.out.println(solve(new int[]{10, 9, 4, 5, 4, 8, 6}));
		System.out.println(solve(new int[]{1, 2, 3, 2, 3, 7, 2, 1}));
	}
	static int solve(int[] arr){
		int ans = 0;
		if (arr == null || arr.length == 0) return ans;

		Map<Integer, Integer> map = new HashMap<>();
		for(int a = 0; a < arr.length; ++a){
			int val = arr[a];
			Integer lo = map.get(val-1);
			Integer hi = map.get(val+1);
			map.put(val, Math.max(lo != null ? lo : 0, hi != null ? hi : 0)+1);
			ans = Math.max(ans, map.get(val));
		}
		return ans;
	}
}
