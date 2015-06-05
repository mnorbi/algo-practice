public class KthElementOfTwoSortedArrays{
  public static void main(String[] args){
    System.out.println(
      kThElementOfTwoSortedArrays(
        4,
        new int[]{2, 4, 8, 12},
        new int[] {3, 5, 10, 15, 27}));
  }
  public static int kThElementOfTwoSortedArraysLinear(int k, int[] arr1, int[] arr2){
    if (k < 1 || k > arr1.length+arr2.length) throw new IllegalArgumentException();
    int i1, i2;
    for(i1 = 0, i2 = 0; i1 < arr1.length && i2 < arr2.length;){
      if (k == 1) return Math.min(arr1[i1], arr2[i2]);
      else if (arr1[i1] <= arr2[i2]){
        ++i1;
      } else {
        ++i2;
      }
      --k;
    }
    for(; i1 < arr1.length && k > 1; ++i1, --k);
    if (i1 < arr1.length) return arr1[i1];
    for(; i2 < arr2.length && k > 1; ++i2, --k);
    if (i2 < arr2.length) return arr2[i2];
    throw new IllegalStateException();
  }
}
