import java.util.*;
import java.io.*;
public class MergeSort{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("sort.in"));
      FileWriter fw = new FileWriter("sort.out");
    ){
      int N = Integer.valueOf(br.readLine());
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] tmp = new int[arr.length];
      for(int L = 1, lo=0, hi=L;L < N;L<<=1, lo=0, hi=L){
        for(;hi < N; lo = hi+L, hi=lo+L){
          int a = lo, b = hi, t = lo;
          for(;a < lo+L && a < N && b < hi+L && b < N && t < N;){
            if (arr[a] <= arr[b]){
              tmp[t++] = arr[a++];
            } else {
              tmp[t++] = arr[b++];
            }
          }
          for(;a<lo+L && a<N && t < N;){
            tmp[t++] = arr[a++];
          }
          while(--t>=lo){
            arr[t] = tmp[t];
          }
        }
      }
      System.out.println(Arrays.toString(arr));
    }
  }
}
