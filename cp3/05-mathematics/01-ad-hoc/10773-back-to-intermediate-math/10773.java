import java.util.*;

class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int testCase = sc.nextInt();
    for(int i = 1; i <= testCase; ++i){
        double d = sc.nextInt();
        double v = sc.nextInt();
	double u = sc.nextInt();
	System.out.print("Case " +i+": ");
	if (u == 0 || v == 0 || v >= u ) {
		System.out.println("can't determine");
		continue;
	}
	//double ans = d/u*(1/Math.cos(Math.asin(v/u))-1);
        double ans = d/u*(1/Math.sqrt(u*u-v*v)-1);
        System.out.println(String.format("%.3f",ans));
    }
  }
}
