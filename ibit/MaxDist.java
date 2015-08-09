/**
Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].

If there is no solution possible, return -1.

Example :

A : [3 5 4 2]

Output : 2 
for the pair (3, 4)
**/
import java.util.*;
class MaxDist{
  public static void main(String[]args){
    System.out.println(maxDist(new int[] {3, 5, 4, 2}));
    System.out.println(maxDistWithBinSearch(new int[]{3, 5, 4, 2}));
  }
  static int maxDistWithBinSearch(final int[] arr){
    if (arr == null || arr.length == 0) return -1;

    int N = arr.length;
    int[] pfxMin = new int[N];
    pfxMin[0] = arr[0];
    for(int i = 1; i < N; ++i){
      pfxMin[i] = Math.min(arr[i], pfxMin[i-1]);
    }
    int[] sfxMax = new int[N];
    sfxMax[N-1] = arr[N-1];
    for(int i = N-2; i >=0; --i){
      sfxMax[i] = Math.max(arr[i], sfxMax[i+1]);
    }
    int ret = 0;
    for(int i = 0; i < N; ++i){
      ret = Math.max(ret, findMax(i, pfxMin, sfxMax) - i );
    }
    return ret;
  }
  static int findMax(int minId, int[] pfxMin, int[] sfxMax){
    int ret = -1;
    int lo = 0, hi = sfxMax.length-1;
    while(lo<=hi){
      int mid = lo+((hi-lo)>>>1);
      if (pfxMin[minId] <= sfxMax[mid]){
        ret = mid; 
        lo = mid+1;
      } else {
        hi = mid-1;
      }
    }
    return ret;
  }
  static int maxDist(final int[] arr){
    if (arr == null || arr.length == 0) return -1;

    Integer[] idxs = range(0, arr.length);
    Arrays.sort(idxs, new Comparator<Integer>(){
      public int compare(Integer idx1, Integer idx2){
        int ret = Integer.compare(arr[idx1], arr[idx2]);
        if (ret == 0){
           ret = Integer.compare(idx1, idx2);
        }
        return ret;
      }
    });
    int ret = 0;
    int min = arr[0];
    for(int i = 0; i < arr.length; ++i){
      ret = Math.max(ret, arr[i]-min);
      min = Math.min(min, arr[i]);
    }
    return ret;
  }
  static Integer[] range(int lo, int hi){
    Integer[] ret = new Integer[hi-lo];
    for(int i = 0; i < ret.length; ++i){
      ret[i] = lo+i;
    }
    return ret;
  }
}
