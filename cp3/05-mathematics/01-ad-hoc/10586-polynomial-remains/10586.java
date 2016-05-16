import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String nextLine;
    StringBuilder sb = new StringBuilder();
    while(true){
      nextLine = in.readLine();
      if (nextLine == null) break;
      int[] nk = parseIntArray(nextLine, 2); int n = nk[0], k = nk[1];
      if (k == -1) break;
      int[] aArr = parseIntArray(in.readLine(), n+1);
      for(int b = aArr.length-1; b >= 0; --b){
        int c = b-k;
        if (c < 0) break;
        aArr[c] -= aArr[b];
      }
      int last = 0;
      for(int i = 0; i < Math.min(k, aArr.length); ++i){
        if (aArr[i] != 0) last = i;
      }
      for(int i = 0; i <= last; ++i){
        if (i > 0) sb.append(' ');
        sb.append(aArr[i]);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
  private static int[] parseIntArray(String s, int length){
    int[] ret = new int[length];
    char nextChar = ' ';
    for(int a = 0, b = -1, sign = 1; a < s.length(); ++a){
      char prevChar = nextChar;
      nextChar = s.charAt(a);
      if (!Character.isWhitespace(nextChar)){
        if (nextChar == '\n') break;
        if (Character.isWhitespace(prevChar)){
          ++b;
          sign = 1;
          if (nextChar == '-'){
            sign = -1;
            continue;
          }
        }
        ret[b] *= 10;
        ret[b] += sign*(nextChar - '0');
      }
    }
    return ret;
  }
}
