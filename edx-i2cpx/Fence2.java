import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Fence{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("fence.in"));
      FileWriter fw = new FileWriter("fence.out");
    ){
      int[] arr = nextArray(br.readLine());
      int N = arr[0], K = arr[1];
      arr = nextArray(br.readLine());
      fw.write(""+solve2(arr, K));
    }
  }

  private static int solve2(int[]arr,int K){
    Arrays.sort(arr);
    for(int a = arr.length-2; a > 0; --a){
      arr[a] -= arr[a-1];
    }
    int ans = 0;
    for(int x = 1; x <= arr[0]; ++x){
      long sum = 0;
      for(int b = 0; b < arr.length-1; ++b){
        sum += Math.max(x,arr[b]);
      }
      if (K-sum >= x){
        ans = x;
      }
    }

    return ans;
  }

  private static int solve(int[] arr, int K){
    Arrays.sort(arr);
    for(int a = arr.length-2; a > 0; --a){
      arr[a] -= arr[a-1];
    }
    int lo = 0, hi = arr[0], best = 0;
    while(lo <= hi){
      int x = lo + (hi-lo)/2;
      long sum = 0;
      for(int a = 0; a < arr.length-1; ++a){
        sum += Math.max(arr[a],x);
      }
      if (K-sum >= x){
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
