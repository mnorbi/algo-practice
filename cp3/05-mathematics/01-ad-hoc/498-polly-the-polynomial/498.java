import java.io.*;
class Main{
  public static void main(String[]args) throws Exception{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      long[] cArr = toLongArray(in.readLine());
      if (cArr == null) break;
      long[] xArr = toLongArray(in.readLine());
      if (xArr == null) break;
      for(int x = 0; x < xArr.length; ++x){
        long ret = 0L;
        for(int c = 0; c < cArr.length; ++c){
          ret = ret*xArr[x]+cArr[c];
        }
        if (x > 0) sb.append(' ');
        sb.append(ret);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
  private static long[] toLongArray(String s){
    if (s == null || "".equals(s)) return null;
    int size = 0;
    boolean space = true;
    for(int i = 0; i < s.length(); ++i){
      if(space && s.charAt(i) != ' '){
        space = false;
        ++size;
      } else if (s.charAt(i) == ' '){
        space = true;
      }
    }
    long[] ret = new long[size];
    space = true;
    for(int i = 0, j = 0, sign = 1; i < s.length(); ++i){
      char nextChar = s.charAt(i);
      if (!space){
        if (nextChar != ' '){
          if (nextChar == '-') {
            sign = -1;
          } else {
            ret[j] *= 10;
            if (sign > 0){
              ret[j] += (nextChar - '0');
            } else {
              ret[j] -= (nextChar - '0');
            }
          }
        } else {
          space = true;
          ++j;
        }
      } else {
        if (nextChar != ' '){
          space = false;
          sign = 1;
          --i;
        }
      }
    }
    return ret;
  }
}
