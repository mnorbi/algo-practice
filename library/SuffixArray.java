import java.util.*;
import java.util.concurrent.*;

class EvenSubarray{
    /*
     * Complete the evenSubarray function below.
     */
    static int evenSubarray(int[] numbers, int k) {
        /*
         * Write your code here.
         */
        Node root = new Node();
        for(int a = 0; a < numbers.length; ++a){
            Node cur = root;
            for(int b = a; b < numbers.length; ++b){
                if (cur.next[numbers[b]] == null){
                    cur.next[numbers[b]] = new Node();
                }
                cur = cur.next[numbers[b]];
            }
        }

        return count(root, k);
    }

    static int count(Node root, int k){
        int ans = 0;
        for(int a = 0; a < root.next.length; ++a){
            if (root.next[a] != null){
                if (a%2 == 0){
                    ans += 1+count(root.next[a], k);
                } else if (k > 0){
                    ans += 1+count(root.next[a], k-1);
                }
            }
        }
        return ans;
    }

    static int MAX = 251;
    static class Node{
        Node[] next = new Node[MAX];
    }
}

class EvenDistinctSubarrayWithSuffixArray {
    static int MAX_VAL = 1001;
    static int[] count = new int[MAX_VAL];


    /*
     * Complete the evenSubarray function below.
     */
    static int evenSubarray(int[] numbers, int k) {
        /*
         * Write your code here.
         */
        int n = numbers.length;
        int[] tmp = new int[n+1];
        for(int a = 0; a < numbers.length; ++a){
                tmp[a] = numbers[a];
        }
        tmp[n] = 0;
        int[] sfxArr = createSfxArr(tmp);
        int[] plcp = createPlcp(sfxArr,tmp);
        int[] withOddsLen = createWithOddsLen(numbers, k);
        int ans = 0;
        for(int a = 1; a <= n; ++a){
                ans += Math.max(withOddsLen[sfxArr[a]]-plcp[sfxArr[a]], 0);
        }
        return ans;
    }

    static int[] createSfxArr(int[] arr){//last item is terminating char
        int n = arr.length;
        int[] sfxArr = new int[n], rank = new int[n], tmpRank = new int[n];

        sfxArr[n-1] = n-1; rank[n-1] = 0;
        for(int sfxId = 0; sfxId < n-1; ++sfxId){
            sfxArr[sfxId] = sfxId;
            rank[sfxId] = arr[sfxId];
        }

        int[] tmp;
        for(int k = 1; k < n; k<<=1){
            sfxArr = sorted(sfxArr, rank, k);
            sfxArr = sorted(sfxArr, rank, 0);
            int a = tmpRank[sfxArr[0]] = 0;
            for(int b = 1; b < n; ++b){
                tmpRank[sfxArr[b]] = rank[sfxArr[b]] == rank[sfxArr[b-1]] && rank[sfxArr[b]+k] == rank[sfxArr[b-1]+k] ? a : ++a;
            }
            if (a == n-1) break;
            tmp = rank; rank = tmpRank; tmpRank = tmp;
        }
        return sfxArr;
    }

    static int[] createPlcp(int[] sfxArr, int[] arr){
        int n = sfxArr.length;
        int[] plcp = new int[n];
        int[] prev = new int[n];
        prev[sfxArr[0]] = -1;
        for(int a = 1; a < n; ++a){
            prev[sfxArr[a]] = sfxArr[a-1];
        }
        for(int sfx = 0, lcp = 0; sfx < n-1; ++sfx){
            lcp = Math.max(0, lcp-1);
            while(arr[sfx+lcp] == arr[prev[sfx]+lcp]){
                ++lcp;
            }
            plcp[sfx] = lcp;
        }
        return plcp;
    }

    static int[] createWithOddsLen(int[] arr, int k){
        int n = arr.length;
        int[] ans = new int[n];
        int l = n, r = l;
        while(l > 0){
            --l;
            if (arr[l]%2 != 0) {
                if (k > 0) {
                    --k;
                } else {
                    while (r > l) {
                        if (arr[--r] % 2 != 0) {
                            //#bug ++k; invariant: once window is filled with odds, we keep number of odds constant
                            break;
                        }
                    }
                }
            }
            ans[l] = r-l;
        }
        return ans;
    }

    static int[] sorted(int[] toSort, int[] rank, int d){
        Arrays.fill(count,0);
        int n = toSort.length;
        for(int sfxId = 0; sfxId < n; ++sfxId){//#bug sfx id:[0..n) (n-1th is terminating: 0)
            int key = Math.min(toSort[sfxId]+d, n-1);//#bug was 0 instead of n
            ++count[rank[key]+1];
        }
        for(int a = 1; a < count.length; ++a){
            count[a] += count[a-1];
        }
        int[] ans = new int[n];
        for(int sfxId = 0; sfxId < n; ++sfxId){
            int key = Math.min(toSort[sfxId]+d, n-1);
            ans[count[rank[key]]++] = toSort[sfxId];
        }
        return ans;
    }

}


class Test{
        public static void main (String[] args)
        {
                while(true){
                        int[] arr = rndArr(rnd(1,1001), 1, 251);
			int k = rnd(0, arr.length);
			try{
	                        int ans1 = EvenSubarray.evenSubarray(arr, k);
        	                int ans2 = EvenDistinctSubarrayWithSuffixArray.evenSubarray(arr, k);
	                        if (ans1 != ans2){
	                                print(ans1);
	                                print(ans2);
					throw new RuntimeException();
                        	}
			} catch(RuntimeException re){
				print(k);
				print(arr);
				throw re;
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
        static void print(int n){
            System.out.println(n);
        }
	static void print(int[]arr){
	    System.out.println(Arrays.toString(arr));
	}
}

