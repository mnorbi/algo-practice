import java.util.*;
/**
  N=(K+1)+...+(K+L)=L*K+L*(L+1)/2=L(2K+L+1)/2
**/
class Main{
  public static void main(String[]arg){
    Scanner sc = new Scanner(System.in);
    while(true){
      int N = sc.nextInt();
      if (N == -1) break;
      int N2 = N*2;
      for(int L = (int)Math.sqrt(N2); L >= 1; --L){
        if (N2%L == 0){
          int K2 = N2/L-L-1;
          if (K2 >= 0 && K2%2 == 0){
            int K = K2/2;
            System.out.println(String.format("%d = %d + ... + %d", N, K+1, K+L));
            break;
          }
        }
      }
    }
  }
}
