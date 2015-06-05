import java.util.*;

class GivenAPreOrderTraversalPrintAllPossibleBinaryTreesInOrder{
  public static void main(String[]args){
    print(new int[] {1,2, 3});
  }
  private static void print(int[] arr){
    List<String> gen = print(arr, 0, arr.length);
    for(String s : gen){
      System.out.println(s);
    }
  }
  private static List<String> print(int[] arr, int lo, int hi){
    if (lo>=hi) {
      return Collections.singletonList("");
    }
    List<String> ret = new ArrayList<String>();
    for(int i = lo; i < hi; ++i){
      List<String> lefts = print(arr, lo+1, i+1);
      ArrayList<String> leftWithRoots = new ArrayList<String>();
      for(String left : lefts){
        leftWithRoots.add(left+" "+arr[lo]);
      }
      List<String> rights = print(arr, i+1, hi);
      for(String leftWithRoot : leftWithRoots){
        for(String right : rights){
           ret.add(leftWithRoot+" "+right);
        }
      }
    }
    return ret;
  }

}
