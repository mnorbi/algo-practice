import java.util.*;
class TripletsToZero{
  public static void main(String[]args){
    //[-2,-2,4] [-2,-1,3] [-2,0,2] [-2,1,1] [-1,-1,2] [-1,0,1] [0,0,0]
    printTriplets(new int[] {-2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 4});
    System.out.println();
    //[-4,-2,6] [-4,0,4] [-4,1,3] [-4,2,2] [-2,-2,4] [-2,0,2]
    printTriplets(new int[] {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
  }
  private static void printTriplets(int[] arr){
    Arrays.sort(arr);
    int nonNegStartIdx = findNonNegStartIdx(arr);
    if (nonNegStartIdx < 1) return;

    for(int i = 0; i <= nonNegStartIdx;){
      for(int j = i+1, k = arr.length-1; j < k && k >= nonNegStartIdx;){
        int sum = arr[i]+arr[j]+arr[k];
        if (sum < 0){
          ++j;
          continue;
        } else if (sum > 0){
          --k;
          continue;
        } else {
          System.out.printf("[%d,%d,%d]\n", arr[i], arr[j], arr[k]);
          for(--k; k >= nonNegStartIdx && arr[k+1] == arr[k]; --k);
          for(++j; j < k && arr[j-1] == arr[j]; ++j);
        }
      }
      for(++i; i <= nonNegStartIdx && arr[i]==arr[i-1]; ++i);
    }
  }
  private static int findNonNegStartIdx(int[]arr){
    for(int i = 0; i < arr.length; ++i){
      if (arr[i] >= 0) return i;
    }
    return -1;
  }
}
