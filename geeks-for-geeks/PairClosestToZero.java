import java.util.*;
class PairClosestToZero{
  public static void main(String[]args){
    Pair ans = find(new int[]{ 3, 5, 1, 0, -3, 6});
    System.out.println(ans);
  }
  private static Pair find(int[] arr){
    if (arr == null || arr.length < 2) return null;
    Arrays.sort(arr);
    Pair ans = new Pair(arr[0], arr[1]);
    for(int i = 0, j = arr.length-1, bestSum = Integer.MAX_VALUE; i < j;){
      int sum = arr[i]+arr[j];
      if (Math.abs(sum) < Math.abs(bestSum)){
        bestSum = sum;
        ans = new Pair(arr[i], arr[j]);
      }
      if (sum < 0){
        ++i;
      } else if (sum > 0){
        --j;
      } else {
        break;
      }
    }
    return ans;
  }
}
class Pair{
  int x, y;
  Pair(int x, int y){
     this.x = x;
     this.y = y;
  }
  public String toString(){
    return String.format("(%d, %d)", x, y);
  }
}
