import java.util.*;
/**
 v(t) = v-c*t v(s) = 0 -->  v-c*s = 0 --> c=v/s
 v(t) = v(1-t/s)
 dist= integral[0,s]{v(t)}dt = v*(s-(s^2)/2/s) = v(s-s/2)=v*s/2

 Used reflection trick to count the bounces (one single straight line for the ball)
**/
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    while(true){
      double a = sc.nextInt();
      double b = sc.nextInt();
      double v = sc.nextInt();
      double A = Math.toRadians(sc.nextInt());
      double s = sc.nextInt();
      if (a == 0) break;
      double dist = v*s/2.0;
      double hDist = dist*Math.cos(A)-a/2.0;
      double vDist = dist*Math.sin(A)-b/2.0;
      
      int vHit = 0;
      if (Double.compare(hDist, 0) >= 0){
        vHit = 1 + (int)(hDist/a);
      }
      int hHit = 0;
      if (Double.compare(vDist, 0) >= 0){
        hHit = 1 + (int)(vDist/b);
      }
      System.out.println(vHit+" "+hHit);
    }
  }
}
