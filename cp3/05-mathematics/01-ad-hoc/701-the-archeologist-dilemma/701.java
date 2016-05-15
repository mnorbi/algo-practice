import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int a = sc.nextInt();
      int digits = 0;
      for(int b = a; b > 0; b /= 10){
        ++digits;
      }
      double lower = logTwo(a);
      double upper = logTwo(a+1);
      double logTwoOfTen = logTwo(10);
      for(int b = digits+1;;++b){
        int c = (int)Math.ceil(b*logTwoOfTen+lower);
        int d = (int)Math.floor(b*logTwoOfTen+upper);
        if (c == d) {
	  System.out.println(c);
          break;
        }
      }
    }
  }
  private static double logTwo(int val){
    return Math.log10(val)/Math.log10(2);
  }
}
