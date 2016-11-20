import java.util.*;
import java.io.*;
public class MergeSort4{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("sort.in"));
      FileWriter fw = new FileWriter("sort.out");
    ){
      int N = Integer.valueOf(br.readLine());
      if (N == 0) return;
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] aux = new int[arr.length];
      sort(arr,aux,0,N,fw);
      fw.write(""+arr[0]);
      for(int a = 1; a < N; ++a){
        fw.write(" "+arr[a]);
      }
    }
  }
  private static void sort(int[]arr,int[]aux,int lo, int hi, FileWriter fw) throws IOException{
    if (lo+1==hi){
      print(lo,lo,arr[lo],arr[lo],fw);
      return;
    } 
    int mid = (lo+hi)/2;
    sort(arr,aux,lo,mid,fw);
    sort(arr,aux,mid,hi,fw);
    merge(arr,aux,lo,mid,hi,fw);
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
    print(lo+1,hi+1,arr[lo],arr[hi-1],fw);
  }
  private static void print(int lo, int hi, int vlo, int vhi, FileWriter fw) throws IOException{
    fw.write(String.format("%d %d %d %d\n",lo+1,hi+1,vlo,vhi));
  }
}
