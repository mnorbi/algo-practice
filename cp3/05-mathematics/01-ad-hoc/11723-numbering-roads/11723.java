import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    for(int i = 1; i <= 10002; ++i){
      int R = sc.nextInt();
      int N = sc.nextInt();
      if (R == 0) break;
      System.out.print(String.format("Case %d: ", i));
      if (N >= R) {
        System.out.println("0");
        continue;
      }
      int diff = R-N;
      int ans = diff/N+(diff%N > 0 ? 1 : 0);
      if (ans <= 26) {
        System.out.println(ans);
      } else {
        System.out.println("impossible");
      }
    }
  }
}
