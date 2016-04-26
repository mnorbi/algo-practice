import java.util.*;
class Main{
  static int side = 1;
  static double yGrid = side*Math.sqrt((1-0.5*0.5))/3;
  static double xGrid = side*0.5;

  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      int m = sc.nextInt();
      int[] nCoord = distToTop(n);
      int[] mCoord = distToTop(m);
      double ans = Math.sqrt(
	      Math.pow((nCoord[0]-mCoord[0])*xGrid, 2)+
	      Math.pow((nCoord[1]-mCoord[1])*yGrid, 2));
      System.out.println(String.format("%.3f", ans));
    }
  }
  private static int[] distToTop(int a){
    int i = (int)Math.sqrt(a);
    int xDist = a - i*(i+1);
    int yDist = 3*i + (2-(a-i*i)%2);
    return new int[]{xDist,yDist};
  }
}
