import java.util.*;
class ArrangeToLargestNumber{
  public static void main(String[]args){
    Integer[] arr = new Integer[]{5, 50, 56};
    Arrays.sort(arr, (i1, i2) -> {
      String i1i2 = Integer.toString(i1)+Integer.toString(i2);
      String i2i1 = Integer.toString(i2)+Integer.toString(i1);
      return i2i1.compareTo(i1i2);
    });
    for(int a : arr){ System.out.print(a); }
    System.out.println();
  }
}
