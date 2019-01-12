import java.util.*;
import java.io.*;
class Solver{
  public static void main(String[]args) throws IOException {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String[] arr = br.readLine().split("\\s+");
      int R = Integer.valueOf(arr[0]), C = Integer.valueOf(arr[1]);
      char[][]mat = new char[R][];
      for(int r = 0; r < R; ++r){
          mat[r] = br.readLine().toCharArray();
      }
      char[][] trMat = new char[C][R];
      for(int r = 0; r < R; ++r){
          for(int c = 0; c < C; ++c){
              trMat[c][r] = mat[r][c];
          }
      }
      
      int ans = find(mat);
      ans = Math.max(ans, find(trMat));
      
      System.out.println(ans);
    }
  }
  static int find(char[][]mat){
      int ans = 0, sp = 0;
      int R = mat.length, C = mat[0].length;
      int[] stack = new int[C+1], dp = new int[C+1];
      for(int r = 0; r < R; ++r){
          sp = 0; stack[sp] = 0;
          for(int c = 0; c <= C; ++c){
              dp[c] = c == C ? 0 : mat[r][c] == '1' ? 0 : dp[c]+1;
              while(sp > -1 && dp[stack[sp]] > dp[c]){
                  ans = Math.max(ans, 2*((c-stack[sp])+dp[stack[sp]]));
                  --sp;
              }
              if (sp > -1 && dp[stack[sp]] == dp[c]) continue;
              stack[++sp] = c;
          }
      }
      return ans;
  }
}
