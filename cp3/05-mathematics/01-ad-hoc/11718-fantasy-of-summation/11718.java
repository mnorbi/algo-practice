import java.util.*;
/**
  A[0] is contained in the sum the following times
    Let's fix i1=0, then A[0] is added n**(K-1) times to the end sum
    Let's fix i2=0, then A[0] is added n**(K-1) times to the end sum
    ,,,,
    Let's fix iK=0, then A[0] is added n**(K-1) times to the end sum
--------------------------------------------------------------------
summing all up, A[0] is added K*n**(K-1)

For all A[i] then:
  ( K*(A[0]+...+A[n-1])*n**(K-1) ) % MOD
**/
class Main{
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);
    int tcc = sc.nextInt();
    for(int i = 1; i <= tcc; ++i){
      int n = sc.nextInt();
      int K = sc.nextInt();
      int MOD = sc.nextInt();
      int aSum = 0;
      for(int j = 0; j < n; ++j){
        aSum = (aSum+(sc.nextInt()%MOD))%MOD;
      }
      long ans = (aSum*fastModPow(n,K-1, MOD))%MOD;
      ans = (ans*K)%MOD;
      System.out.println(String.format("Case %d: %d", i, ans));
    }
  }
  private static int fastModPow(int n, int k, int MOD){
    long ret = 1;
    for(long base = n; k > 0; base = (base*base)%MOD, k>>=1){
      if ((k&1) == 1){
        ret = (ret*base)%MOD;
      }
    }
    return (int)ret;
  }
}
