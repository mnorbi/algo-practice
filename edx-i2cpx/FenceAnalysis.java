import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class FenceAnalysis{
  public static void main(String[]args) throws IOException{
      int[] arr = new int[]{};
      int N = arr[0], K = arr[1];
      arr = new int[]{};
      fw.write(""+solve(arr, K));
  }
  private static int solve(int[] arr, int K){
    Arrays.sort(arr);
    for(int a = arr.length-2; a > 0; --a){
      arr[a] -= arr[a-1];
    }
    int lo = 0, hi = arr[0], best = 0;
    while(lo <= hi){
      int x = lo + (hi-lo)/2;
      long remaining = K;
      for(int a = 0; a < arr.length-1 && remaining >= x; ++a){
        remaining -= Math.max(arr[a],x);
      }
      if (remaining >= x){
        best = x;
        lo = x+1;
      } else {
        hi = x-1;
      }
    } 
    return best;
  }
  private static int[] nextArray(String line){
    int[] ret = Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray();
    return ret;
  }
}
