import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class KBest{
  public static void main(String[]args){
    int N = 4;
    int[][] arr = new int[N][N];
    random(arr[0], 0, 10);
    random(arr[1], 1, 10);
    int[] idx = IntStream.range(0,N);
    Arrays.sort(idx,(i1,i2) -> Double.compare((arr[0][i1]+0.0)/(arr[1][i1]+0.0),(arr[0][i2]+0.0)/(arr[1][i2]+0.0)));
    permute(arr[0],idx);permuge(arr[1],idx);
    int max = 0;
    int[] sol = new int[2];
    for(int a = 0; a < arr.length; ++a){
      for(int b = a+1; b < arr.length; ++b){
        int mediant = mediant(arr,a,b);
        if (max < mediant){
          sol[0] = a;
          sol[1] = b;
          max = mediant;
        }
      }
    }
    System.out.println(Arrays.toString(arr[0]));
    System.out.println(Arrays.toString(arr[1]));
    System.out.println(String.format("%d/%d %d/%d", arr[0][sol[0]], arr[1][sol[0]], arr[0][sol[1]], arr[1][sol[1]]));
  }
  private static void permute(int[]arr,int[]perm){
    int sum = 0;
    for(int a : arr){
      sum += arr[a];
    }
    if (sum == 0) return;
    for(int p : perm){
      arr[a] += sum*(arr[perm[p]]%sum);
    }
    for(int a = 0; a < arr.length; ++a){
      arr[a] /= sum;
    }
  }
  private static int mediant(int[][]arr, int...as){
    int ret = 0;
    for(int a : as){
      ret += arr[0][a]/arr[1][a];
    }
    return ret;
  }
  private static void random(int[] arr, int lo, int hi){
    for(int a = 0; a < arr.length; ++a){
      arr[a] = ThreadLocalRandom.current().nextInt(lo,hi);
    }
  }
}
