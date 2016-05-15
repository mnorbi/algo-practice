import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      int next = sc.nextInt();
      if (next == 0) break;
      int bits = 1<<((next-1960)/10+2);
      int n = 2;
      for(double d = 0;; ++n){
        d += Math.log10(n)/Math.log10(2);
        if (d > bits) break;
      }
      System.out.println(n-1);
    }
  }
}
