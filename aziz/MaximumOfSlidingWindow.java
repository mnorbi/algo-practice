import java.util.*;
class MaximumOfSlidingWindow{
  public static void main(String[]args){
    System.out.println(Arrays.toString(
	maxSlideWindow(
		new double[]{1.3,2.5,3.7,1.4,2.6,2.2,1.7,1.7},
		new int[]{0,2,3,5,6,8,9,14}, 
		3))
    );
  }

  static double[] maxSlideWindow(double[] vals, int[] ts, int window){
    double[] ret = new double[vals.length];
    ArrayDeque<Integer> deq = new ArrayDeque<>();
    for(int a = 0, b = 0; a < vals.length; ++a){
      while(!deq.isEmpty() && ts[a]-ts[deq.getFirst()] > window){ deq.removeFirst(); }//evict old timestamps
      while(!deq.isEmpty() && vals[a] >= vals[deq.getLast()]){ deq.removeLast(); }//deq strictly decreasing
      deq.addLast(a);
      ret[b++] = vals[deq.getFirst()];
    }
    return ret;
  }

}
