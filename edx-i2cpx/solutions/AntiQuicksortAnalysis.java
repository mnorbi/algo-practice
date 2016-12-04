import java.util.*;
import java.io.*;
public class AntiQuicksortAnalysis{
  public static void main(String[]args) throws IOException {
      int N = 6;
      int[] arr = new int[N+1];
      for(int a = 1; a <= N; ++a){
        arr[a] = a;
      }
      test(arr, 1);
  }
  private static void test(int[]arr,int lo){
    if (lo == arr.length){
      qsort(Arrays.copyOf(arr,arr.length));
    }
    for(int a = lo; a < arr.length; ++a){
      swap(arr,lo,a);
      test(arr,lo+1);
      swap(arr,lo,a);
    }
  }
  private static void qsort(int[]arr){
    int[] tmp  = Arrays.copyOf(arr,arr.length);
    System.out.println(Arrays.toString(arr));
    int ret = qsort(arr,1,arr.length-1);
    System.out.println(ret);
    System.out.println("-----");
  }
  private static int qsort(int[]arr,int left, int right){
    int key = arr[(left+right)/2];
    int i = left;
    int j = right;
    int ret = 0;
    do{
      while(arr[i] < key) {++i; ++ret;} ++ret;
      while(arr[j] > key) {--j; ++ret;} ++ret;
      if (i <= j){
        swap(arr,i++,j--);
      }
    }while(i <= j);
    if (left < j) { ret += qsort(arr, left, j); }
    if (right > i) { ret += qsort(arr, i, right); }
    return ret;
  }
  private static void swap(int[]arr,int a, int b){
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
}
