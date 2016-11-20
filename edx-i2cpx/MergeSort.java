import java.util.*;
import java.io.*;
public class MergeSort{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("sort.in"));
      FileWriter fw = new FileWriter("sort.out");
    ){
      int N = Integer.valueOf(br.readLine());
      if (N == 0) return;
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] aux = new int[arr.length];
      for(int a = 0; a < N; ++a){
        fw.write(String.format("%d %d %d %d\n",a+1,a+1,arr[a],arr[a]));
      }
      for(int L = 1; L < N; L <<= 1){
        for(int lo = 0; lo < N-L; lo += L+L){
          merge(arr,aux,lo,lo+L,Math.min(N,lo+L+L),fw);
        }
      }
      fw.write(""+arr[0]);
      for(int a = 1; a < N; ++a){
        fw.write(" "+arr[a]);
      }
    }
  }
  private static void merge(int[]arr,int[]aux,int lo,int mid,int hi, FileWriter fw) throws IOException{
    int a = lo, b = mid, t = lo;
    while(a<mid && b<hi){
      if (arr[a] <= arr[b]){
        aux[t++] = arr[a++];
      } else {
        aux[t++] = arr[b++];
      }
    }
    while(a<mid){
      aux[t++] = arr[a++];
    }
    while(t-->lo){
      arr[t] = aux[t];
    }
    fw.write(String.format("%d %d %d %d\n",lo+1,hi,arr[lo],arr[hi-1]));
  }
}
