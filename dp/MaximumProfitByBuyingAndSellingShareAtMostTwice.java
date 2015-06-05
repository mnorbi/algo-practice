import java.util.*;

class MaximumProfitByBuyingAndSellingShareAtMostTwice{
  public static void main(String[]args){
    System.out.println(maxProfit(new int[] {2, 30, 15, 10, 8, 25, 80}));
    System.out.println(maxProfit(new int[] {2, 30, 15, 20, 8, 10, 90, 80}));
    System.out.println(maxProfit(new int[] {3,4,7,10}));
  }
  public static int maxProfit(int[] arr){
    if (arr == null || arr.length < 2) return 0;
    RangeProfit profit = rangeProfit(arr, 0, arr.length-1);
    RangeProfit left = rangeProfit(arr, 0, profit.start -1);
    RangeProfit right = rangeProfit(arr, profit.end+1, arr.length-1);
    RangeProfit middle = 
      rangeProfit(
        reverse(arr, profit.start+1, profit.end-1),
        profit.start+1,
        profit.end-1);

    int sum = sum(
        profit,
        max(
           max(left, right),
           middle
        )
    );
    return Math.max(sum, profit.val);
  }

  private static int sum(RangeProfit a, RangeProfit b){
    if (a == null &&  b == null) return 0;
    if (a == null) return b.val;
    if (b == null) return a.val;

    return a.val+b.val;
  }

  private static RangeProfit max(RangeProfit a, RangeProfit b){
    if (a == null) return b;
    if (b == null) return a;

    return a.val > b.val ? a : b;
  }

  private static int[] reverse(int[] arr, int lo, int hi){
    for(int i = lo, j = hi; i < j; ++i, --j){
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
    return arr;
  }

  private static RangeProfit rangeProfit(int[] arr, int lo, int hi){
    if (lo >= hi || lo < 0 || hi >= arr.length) return null;

    RangeProfit ret = new RangeProfit();
    for(int i = lo, minIdx = lo; i <= hi; ++i){
      int val = arr[i]-arr[minIdx];
      if (val > ret.val){
        ret.val = val;
        ret.end = i;
        ret.start = minIdx;
      }
      if (arr[minIdx] > arr[i]){
        minIdx = i;
      }
    }
    return ret;
  }
}
class RangeProfit{
  int start = -1;
  int end = -1;
  int val = Integer.MIN_VALUE;
}