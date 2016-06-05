import java.util.*;
import java.io.*;
/* Horner scheme */
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String nextLine;
    while(true){
      nextLine = br.readLine();
      if (nextLine == null || "".equals(nextLine)) break;
      int x = parseIntArray(nextLine)[0];
      int[] aArr = parseIntArray(br.readLine());
      int ret = 0;
      int n = aArr.length-1;//forget last element of array
      for(int i = n; i > 0; --i){
        ret = ret*x+i*aArr[n-i];
      }
      sb.append(ret).append("\n");
    }
    System.out.print(sb);
  }
  private static int[] parseIntArray(String s){
    int length = 0;
    char nextChar = ' ';
    for(int a = 0; a < s.length(); ++a){
      char prevChar = nextChar;
      nextChar = s.charAt(a);
      if (nextChar != ' '){
        if (nextChar == '\n') break;
        if (!Character.isDigit(prevChar) && Character.isDigit(nextChar)){
          ++length;
        }
      }
    }
    int[] ret = new int[length];
    nextChar = ' ';
    for(int a = 0, b = -1, sign = 1; a < s.length(); ++a){
      char prevChar = nextChar;
      nextChar = s.charAt(a);
      if (nextChar != ' '){
        if (nextChar == '\n') break;
        if (prevChar == ' '){
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
