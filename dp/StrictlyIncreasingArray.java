class StrictlyIncreasingArray {
  public static void main(String[] args) {
    int[][] arrays = new int[][]{
      { 0 },
      { 1, 2, 4, 5, 20, 30, 8, 9, 10, 11 },
      { 1, 3, 3, 2, 4, 4, 6 },
      { 1, 2, 3, 4, 5 },
      { 1, 1, 1, 1, 1 },
      { 0, 1, 2, 3, 3, 4 },
      { 7, 8, 3, 4, 7, 6 },
      { 10, 20, 100, 30, 1, -50 }
    };
    for(int[] arr : arrays){
      printDiff(arr, strictlyIncreasingArrayFor(arr));
    }
  }

  static int[] strictlyIncreasingArrayFor(int[] arr){
    if (arr == null || arr.length == 0) return arr;

    int[] dpTable = new int[arr.length];
    int[] prev = new int[arr.length];
    dpTable[0] = 1;
    prev[0] = 0;
    int maxLength = 1;
    int endIdx = 0;
    for(int i = 1; i < arr.length; ++i){
      dpTable[i] = 1;
      prev[i] = i;
      for(int j = 0; j < i; ++j){
        if (arr[i]-arr[j] >= i-j && dpTable[i] < dpTable[j]+1){
          dpTable[i] = dpTable[j]+1;
          prev[i] = j;
          if (maxLength < dpTable[i]) {
            maxLength = dpTable[i];
            endIdx = i;
          }
        }
      }
    }
    return createSomeStrictlyIncreasingArray(arr, prev, endIdx);
  }

  private static int[] createSomeStrictlyIncreasingArray(int[] arr, int[] prev, int lo) {
    int[] ret = new int[arr.length];
    int hi = arr.length;
    for(;;){
      for(int i = lo; i < hi; ++i) {
        ret[i] = arr[lo] + i - lo;
      }
      hi = lo;
      if (lo != prev[lo]){
        lo = prev[lo];
      } else {
        break;
      }
    }
    for(int i = lo-1; i >= 0; --i){
      ret[i] = arr[lo]-(lo-i);
    }

    return ret;
  }

  static void printDiff(int[] before, int[] after) {
    StringBuilder diffMarker = new StringBuilder();
    StringBuilder beforeStr = new StringBuilder();
    StringBuilder afterStr = new StringBuilder();

    for(int i = 0; i < after.length; ++i){
      if (i != 0){
        beforeStr.append(", ");
        afterStr.append(", ");
        diffMarker.append("  ");
      }
      String bStr = Integer.toString(before[i]);
      String aStr = Integer.toString(after[i]);
      int width = Math.max(aStr.length(), bStr.length());

      beforeStr.append(bStr);
      afterStr.append(aStr);
      for(int j = 0; j < width-bStr.length(); ++j){
        beforeStr.append(' ');
      }
      for(int j = 0; j < width-aStr.length(); ++j){
        afterStr.append(' ');
      }

      if (before[i] == after[i]){
        diffMarker.append(' ');
      } else {
        diffMarker.append('*');
      }

      for(int j = 1; j < width; ++j) {
        diffMarker.append(' ');
      }
    }

    System.out.println(beforeStr.toString());
    System.out.println(diffMarker.toString());
    System.out.println(afterStr.toString());
    System.out.println();
    System.out.println();
  }

}
