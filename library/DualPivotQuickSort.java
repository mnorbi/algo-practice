import java.util.*;
public class DualPivotQuickSort{
  private static final Random rnd = new Random();
  public static void main(String[]args){
    while(true){
    int[] arr = randomArr(rnd.nextInt(10));
    int[] arrCpy = arr.clone();
    quickSort(arr, 0, arr.length-1);
    if (!isSorted(arr)){
      throw new RuntimeException();
    }
    dualPivotQuickSort(arrCpy, 0, arrCpy.length-1);
    boolean isEq = isEqual(arr, arrCpy);
    if (!isEq){
      System.out.println(Arrays.toString(arr));
      System.out.println(Arrays.toString(arrCpy));
      throw new RuntimeException();
    }
    }
  }
  static boolean isSorted(int[]arr){
    for(int a = 1; a < arr.length; ++a){
      if (arr[a-1] > arr[a]){ return false; }
    }
    return true;
  }
  static void dualPivotQuickSort(int[]arr, int start, int end){
    if (start >= end){ return; };
    if (arr[start] > arr[end]){ swap(arr,start,end); }
    int pivot1 = arr[start], pivot2 = arr[end];
    int a = start, b = end;
    for(int k = a+1; k<=b;){
      if (arr[k] < pivot1){ swap(arr, a++, k++); }
      else if (pivot2 < arr[k]){ swap(arr, k, b--); }
      else {++k;}
    }
    dualPivotQuickSort(arr, start, b-1);
    if (a == start && b == end){
      sortPivots(arr, a, b);
    } else {
      dualPivotQuickSort(arr, a, b);
    }
    dualPivotQuickSort(arr, a+1, end);
  }
  static void sortPivots(int[]arr, int start, int end){
    int pivot1 = arr[start], pivot2 = arr[end];
    for(int a = start+1, b = end-1; a < b;){
      if(arr[a] == pivot1){ ++a; }
      else if (arr[b] == pivot2){ --b; }
      else { swap(arr,a++,b--); }
    }
  }
  static void quickSort(int[]arr,int start, int end){
    if (start >= end) { return; }
    int a = start, b = end, pivot = arr[(start+end)/2];
    while(a<=b){
      while(arr[a] < pivot){ ++a; }
      while(pivot < arr[b]){ --b; }
      if (a<=b){
        swap(arr,a++,b--);
      }
    }
    quickSort(arr, start, b);
    quickSort(arr, a, end);
  }
  static void swap(int[]arr, int a, int b){
    if (a == b) { return; }
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
  static boolean isEqual(int[]arr1, int[]arr2){
    if (arr1 == arr2) { return true; }
    if (arr1 == null || arr2 == null) { return false; }
    if (arr1.length != arr2.length) { return false; }
    for(int a = 0; a < arr1.length; ++a){
      if (arr1[a] != arr2[a]) { return false; }
    }
    return true;
  }
  static int[] randomArr(int n){
    int[] ans = new int[n];
    for(int a = 0; a < n; ++a){
      ans[a] = rnd.nextInt(100);
    }
    return ans;
  }
}
