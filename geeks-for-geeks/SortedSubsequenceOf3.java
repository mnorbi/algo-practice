import java.util.*;
import java.util.concurrent.*;

class SortedSubsequenceOf3{
	static int[] solve1(int[]arr){
		int n = arr.length;
		int[] lt = new int[n]; Arrays.fill(lt,-1);
		for(int a = 1, minId = 0, min = arr[minId]; a < arr.length; ++a){
			if (min < arr[a]){
				lt[a] = minId;
			} else if (min > arr[a]){
				min  = arr[a];
				minId = a;
			}
		}
		int[] gt = new int[n]; Arrays.fill(gt,-1);
		for(int a = n-2, maxId = n-1, max = arr[maxId]; a >= 0; --a){
			if (max > arr[a]){
				gt[a] = maxId;
			} else if (max < arr[a]){
				max = arr[a];
				maxId = a;
			}
		}
		for(int a = 1; a < n-1; ++a){
			if (lt[a] != -1 && gt[a] != -1){
				return new int[]{ arr[lt[a]], arr[a], arr[gt[a]] };
			}
		}
		return null;	
	}
	static int[] solve2(int[]arr){
		int n = arr.length;
		int[] par = new int[n+1];
		int maxLen = 3;
		int[] dp = new int[maxLen+1];
		Arrays.fill(dp,-1);
		for(int a = 0; a < n; ++a){
			int len = 1;
			while(len < dp.length && dp[len] != -1 && arr[dp[len]] < arr[a]){
				++len;
			}
			if (len >= dp.length || len == dp.length-1 && dp[len] != -1) break;
			dp[len] = a;
			par[a] = dp[len-1];
		}
		int[] ans = new int[maxLen];
		if (dp[maxLen] == -1) return null;
		ans[maxLen-1] = dp[maxLen];
		for(int a = maxLen-1; a > 0; --a){
			ans[a-1] = par[ans[a]];
		}
		for(int a = 0; a < ans.length; ++a){
			ans[a] = arr[ans[a]];
		}
		return ans;
	}

    static int[] find3Numbers(int arr[])
    {
        int n = arr.length;
        int max = n-1; //Index of maximum element from right side
        int min = 0; //Index of minimum element from left side
        int i;
 
        // Create an array that will store index of a smaller
        // element on left side. If there is no smaller element
        // on left side, then smaller[i] will be -1.
        int[] smaller = new int[n];
        smaller[0] = -1;  // first entry will always be -1
        for (i = 1; i < n; i++)
        {
            if (arr[i] <= arr[min])
            {
                min = i;
                smaller[i] = -1;
            }
            else
                smaller[i] = min;
        }
 
        // Create another array that will store index of a
        // greater element on right side. If there is no greater
        // element on right side, then greater[i] will be -1.
        int[] greater = new int[n];
        greater[n-1] = -1;  // last entry will always be -1
        for (i = n-2; i >= 0; i--)
        {
            if (arr[i] >= arr[max])
            {
                max = i;
                greater[i] = -1;
            }
            else
                greater[i] = max;
        }
 
        // Now find a number which has both a greater number
        // on right side and smaller number on left side
        for (i = 0; i < n; i++)
        {
            if (smaller[i] != -1 && greater[i] != -1)
            {
                return new int[]{arr[smaller[i]],
                                 arr[i],arr[greater[i]]};
            }
        }
 
        return null;
    }

        public static void main (String[] args)
        {
                label: while(true){
                        int[] arr = rndArr(rnd(3,101), 0, 100);
			//int[] arr = new int[]{ 12, 11, 10, 5, 6, 2, 30 };
			int[][] ans = new int[3][];
			ans[0] = find3Numbers(arr);
                        ans[1] = solve1(arr);
                        ans[2] = solve2(arr);
			if (ans[0] == ans[1] && ans[1] == ans[2]) continue;
			for(int a = 0; a < ans.length; ++a){
				for(int b = 1; b < ans[a].length; ++b){
					if (ans[a][b] <= ans[a][b-1]){
						System.out.print(a+": ");
			                        System.out.println(Arrays.toString(ans[a]));
			                        System.out.println(Arrays.toString(arr));
						throw new RuntimeException();
					}
				}
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
