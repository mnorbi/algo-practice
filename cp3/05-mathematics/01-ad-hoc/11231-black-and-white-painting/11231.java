/**
  Solution is given by the bottom right white corner's valid positions
  inside the big chess table.
  These valid positions are constrained to a w*h area chess table from
  the bottom right, where w = n-7 and h = m-7.
  This number is the same if w*h is even and is 1 bigger or smaller
  than the black count, if w*h is odd (+1 if bottom right is white and
  -1 if bottom right is black)
**/
import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      int n = sc.nextInt();
      int m = sc.nextInt();
      int c = sc.nextInt();
      if (n == 0 && m == 0 && c == 0) break;
      int w = (n-7);
      int h = (m-7);
      
      int whiteCount = (w*h+c)/2;
      
      System.out.println(whiteCount);
    } 
  }
}
