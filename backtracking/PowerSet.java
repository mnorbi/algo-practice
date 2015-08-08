import java.util.*;
class PowerSet{
  public static void main(String[]args){
    powerset(new int[]{1, 2, 3, 4, 5});
  }
  static void powerset(int[] arr){
    powerset(arr, -1, new BitSet());
  }
  static void powerset(int[]arr, int i, BitSet bs){
    if (i >= arr.length-1){
      print(arr, bs);
      return;
    }
    bs.set(i+1, true);
    powerset(arr, i+1, bs);
    bs.set(i+1, false);
    powerset(arr, i+1, bs);
  }
  static void print(int[]arr, BitSet bs){
    for(int i = 0; i < arr.length; ++i){
      if (bs.get(i)){
        System.out.print(arr[i] +" ");
      }
    }
    System.out.println();
  }
}
