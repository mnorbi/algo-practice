import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class MaxNonAdjSum{
  public static void main(String[]args){
    Random random = ThreadLocalRandom.current();
    int ans1, ans2;
    int[] arr;
    do{
      int N = 1+random.nextInt(20);
      arr = new int[N];
      for(int a = 0; a < N; ++a){
        arr[a] = -100+random.nextInt(200);
      }
      ans1 = solve(arr);
      ans2 = brute(arr);
    } while(ans1 == ans2);
    System.out.println(Arrays.toString(arr));
    System.out.println(String.format("ans1:%d, ans2:%d"));
  }
  static int brute(int[]arr){
    int ans = Integer.MIN_VALUE;
    for(int mask = 1; mask < (1<<arr.length); ++mask){
      int s = 0;
      boolean valid = true;
      for(int a = 0; a < arr.length; ++a){
        if ((mask & (1<<a)) != 0){
          if (a > 0 && (mask & (1 << (a-1))) != 0) {
            valid = false;
            break;
          }
          s += arr[a];
        }
      }
      if (valid) {
        ans = Math.max(ans, s);
      }
    }
    return ans;
  }
  static int solve(int[]arr){
    int ans;
    if (arr == null || arr.length == 0) {
      ans = 0;
    } else if (arr.length == 1){
      ans = arr[0];
    } else {
      int prev_2 = arr[0];
      int prev_1 = arr[1];
      ans = Math.max(prev_1, prev_2);
      for(int a = 2; a < arr.length; ++a){
        int cur = Math.max(arr[a], arr[a]+prev_2);
        ans = Math.max(ans, cur);
        prev_2 = Math.max(prev_2, prev_1);
        prev_1 = Math.max(prev_1, cur);
      }
    }
    return ans;
  }
}

