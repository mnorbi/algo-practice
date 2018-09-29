import java.util.*;
class Solution{
	public static void main(String[]args){
		while(true){
			java.util.Random rnd = java.util.concurrent.ThreadLocalRandom.current();
			int N = 2+rnd.nextInt(1000);
			int[] arr = new int[N];
			for(int a = 0; a < arr.length; ++a){
				arr[a] = a*1000+rnd.nextInt(1000);
			}
			if (maxExhaustive(arr) != maxXorTrie(arr)){
				throw new RuntimeException(Arrays.toString(arr));
			}
			System.out.println(maxExhaustive(arr));
			System.out.println(maxXorTrie(arr));
		}
	}
	public static int maxExhaustive(int[]arr){
		int ans = Integer.MIN_VALUE;
		for(int a = 0; a < arr.length; ++a){
			for(int b = 1; b < arr.length; ++b){
				ans = Math.max(ans, arr[a]^arr[b]);
			}
		}
		return ans;
	}
	static class Node{ Node[] next = new Node[2]; }
	public static int maxXorTrie(int[]arr){
		Node root = new Node();
		for(int a = 0; a < arr.length; ++a){
			Node cur = root;
			for(int b = 31; b >= 0; --b){
				int dir = (arr[a]>>>b)&1;
				cur = cur.next[dir] == null ? cur.next[dir] = new Node() : cur.next[dir];
			}
		}
		int max = 0;
		for(int a = 0; a < arr.length; ++a){
			Node cur = root;
			int tmp = 0;
			for(int b = 31; b >= 0; --b){
                                int dir = (arr[a]>>>b)&1;
				if (cur.next[dir^1] != null){
					dir = dir^1;
					tmp |= (1<<b);
				}
				cur = cur.next[dir];
			}
			max = Math.max(max, tmp);
		}
		return max;
	}
   public int findMaximumXOR(int[] nums) {
       int max = 0, mask = 0;
       for(int i = 31; i >= 0; i--){
           mask = mask | (1 << i);
           Set<Integer> set = new HashSet<>();
           for(int num : nums){
               set.add(num & mask);
           }
           int tmp = max | (1 << i);
           for(int prefix : set){
               if(set.contains(tmp ^ prefix)) {
                   max = tmp;
                   break;
               }
           }
       }
   }
}
