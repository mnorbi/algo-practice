import java.util.*;
public class GenerateInSortedOrderFromTwoThreeFiveSevenEquation_CareerCup_23823662{
  public static void main(String[]args){
    genFromEqWithTreeSet(100000);
  }
  //TODO implement merge sort based list merging solution with O(N) memory
  public static void genFromEqWithTreeSet(int len){
    TreeSet<Long> set = new TreeSet<>();
    long[] eq = new long[] { 2L, 3L, 5L, 7L };
    set.add(1L);
    for(int i = 0; i < len; ++i){
      long val = set.pollFirst();
      System.out.println(val);
      for(int j = 0; j < eq.length; ++j){
        set.add(val*eq[j]);
      }
      System.out.println("SIZE:"+set.size());
    }
   }
}
