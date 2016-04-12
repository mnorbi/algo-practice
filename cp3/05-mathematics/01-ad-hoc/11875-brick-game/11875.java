import java.util.*;
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int cases = sc.nextInt();
    for(int i = 1; i <= cases; ++i){
      int N = sc.nextInt();
      for(int j = 1; j <= N; ++j){
        int next = sc.nextInt();
        if (j == N/2+1){
          System.out.println(String.format("Case %d: %d", i, next));
	}
      }
    }
  }
}
