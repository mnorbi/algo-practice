import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class Inversions{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("inversions.in"));
      FileWriter fw = new FileWriter("inversions.out");
    ){
      int N = Integer.valueOf(br.readLine());
      if (N == 0) return;
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] aux = new int[arr.length];
      long invCnt = 0;
      for(int L = 1; L < N; L <<= 1){
        for(int lo = 0; lo < N-L; lo += L+L){
          invCnt += merge(arr,aux,lo,lo+L,Math.min(N,lo+L+L));
        }
      }
      fw.write(""+invCnt);
    }
    
  }
  private static long merge(int[]arr,int[]aux,int lo,int mid,int hi) throws IOException{
    long ret = 0;
    int a = lo, b = mid, t = lo;
    while(a<mid && b<hi){
      if (arr[a] <= arr[b]){
        aux[t++] = arr[a++];
      } else {
        ret += (mid-a);
        aux[t++] = arr[b++];
      }
    }
    while(a<mid){
      aux[t++] = arr[a++];
    }
    while(t-->lo){
      arr[t] = aux[t];
    }
    return ret;
  }
}
