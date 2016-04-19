import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int tcc = sc.nextInt();
    double sqrtTwo = Math.sqrt(2);
    for(int i = 1; i <= tcc; ++i){
      int N = sc.nextInt();
      double pathLength = 0.0;
      if (N > 1){
        pathLength = (N-2)*(N-2)*sqrtTwo + 4*(N-1);
      }
      System.out.println(String.format("%.3f",pathLength));
    }
  }
}
