/**
Grammar definition:
  A --> B1
  
  B --> B0
    --> A0
    --> stop

Recursive counting definition:
  B(N) = B(N-1) + A(N-1) if N > 1
         1 if N == 1
  A(N) = B(N-1) if N > 1
         1 if N == 1

Result definition:
  Result = A(N)+B(N) = B(N+1) if N > 0, 0 otherwise

Recurrence matrix form:
             _  _    _   _    _       _
  B(N+1)  = |1  1|  | B(N)| =|B(N)+A(N)|
  A(N+1)    |1  0| *| A(N)|  |B(N)     |
   
             _  _       _  _
  B(N+1)  = |1  1|**N  |B(1)|
  A(N+1)    |1  0|   * |A(1)|
  
**/
public class NoConsecutiveOneBitStringCount{
  public static void main(String[]args){
    long count = findCount(5);
    System.out.println(count);
  }
  private static long findCount(int len){ 
    if (len == 0) return 0;
    
    long[][] mat = new long[][]{ {1L, 1L}, {1L, 0L} };

    mat = fastExp(mat, len);
 
    return mat[0][0] + mat[0][1];
  }
  private static long[][] fastExp(long[][] base, int x){
    long[][] ret = new long[][]{ {1L,0L},{0L,1L} };
    for(;;){
      if ((x&1) > 0){
        ret = mul(base,ret); 
      }
      x = (x>>1);
      if (x == 0) break;
      base = mul(base, base);
    }
    return ret;
  }
  private static long[][] mul(long[][] a, long[][] b){
    long[][] ret = new long[2][2];

    ret[0][0] = a[0][0]*b[0][0]+a[0][1]*b[1][0];
    ret[0][1] = a[0][0]*b[0][1]+a[0][1]*b[1][1];
  
    ret[1][0] = a[1][0]*b[0][0]+a[1][1]*b[1][0];
    ret[1][1] = a[1][0]*b[0][1]+a[1][1]*b[1][1];
    return ret;
  }
  private static void print(long[][] mat){
    for(int i = 0; i < mat.length; ++i){
      for(int j = 0; j < mat[0].length; ++j){
        System.out.print(mat[i][j]+" ");
      }
      System.out.println();
    }
  }
}
