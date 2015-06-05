import java.util.*;
class SearchRotatedArray{
  public static void main(String[]args){
    System.out.println(searchRotatedArr2(new int[]{16, 17, 18, 19, 10, 11, 12, 13}, 13));
    System.out.println(searchRotatedArr2(new int[]{5, 6, 7, 0, 1, 2, 4}, 4));
  }
  static int searchRotatedArr2(int[] arr, int toFind){
    if (arr == null || arr.length == 0) return -1;

    int lo = 0;
    int hi = arr.length-1;

    while(lo <= hi){
      int mid = lo + (hi-lo)/2;
      if (arr[lo] <= toFind && toFind < arr[mid]){
        hi = mid-1;
      } else if (arr[mid] != toFind){
        lo = mid+1;
      } else {
        return mid;
      }
    }

    return -1;
  }

  static int searchRotatedArr(int[] arr, int toFind){
    int shift = searchShift(arr);
    System.out.println(shift);
    int idx = searchShifted(arr, shift, toFind);
    return idx;
  }

  static int searchShifted(int[] arr, int shift, int target){
    int lo = 0;
    int hi = arr.length-1;

    while(lo <= hi){
      int mid = lo + (hi-lo)/2;
      int midVal =arr[(shift+mid)%arr.length];
      if (midVal > target){
        hi = mid-1;
      } else if (midVal < target){
        lo = mid+1;
      } else {
        return (shift+mid)%arr.length;
      }
    }
    return -1;
  }

  static int searchShift(int[] arr){
    if (arr == null || arr.length == 0) return 0;
    int lo = 0;
    int hi = arr.length-1;

    while(lo < hi){
      int mid = lo+(hi-lo)/2;
      if (arr[mid] > arr[lo] && arr[mid] > arr[hi]){
       lo = mid+1;
      } else {
        hi = mid;
      }
    }
    return lo;
  }

}
