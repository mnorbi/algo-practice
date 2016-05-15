import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      int next = sc.nextInt();
      if (next == 0) break;
      System.out.println(31-Integer.numberOfLeadingZeros(next));
    }
  }
}
