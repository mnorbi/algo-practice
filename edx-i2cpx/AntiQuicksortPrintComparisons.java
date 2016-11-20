import java.util.*;
import java.io.*;
public class AntiQuicksortPrintComparisons{
  public static void main(String[]args) throws IOException {
    qsort(new int[]{0,1});
  }
  private static void qsort(int[]arr){
    int[] tmp  = Arrays.copyOf(arr,arr.length);
    int ret = qsort(arr,1,arr.length-1);
      System.out.println(Arrays.toString(arr));
  }
  private static int qsort(int[]arr,int left, int right){
    int key = arr[(left+right)/2];
    int i = left;
    int j = right;
    int ret = 0;
    do{
      while(true){
        System.out.println(String.format("%d<%d",arr[i],key));
        if (arr[i] < key){
          ++i;
        } else break;
      }
      while(true){
        System.out.println(String.format("%d>%d",arr[j],key));
        if (arr[j] > key){
          --j;
        } else break;
      }
      if (i <= j){
        swap(arr,i++,j--);
      }
    }while(i <= j);
    if (left < j) { ret += qsort(arr, left, j); }
    if (right > i) { ret += qsort(arr, i, right); }
    return ret;
  }
  private static void swap(int[]arr,int a, int b){
    System.out.println(String.format("%d<->%d",arr[a],arr[b]));
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
}
