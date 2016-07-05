import java.util.*;
import java.io.*;
class Main{
  private static char[] digits = "0123456789ABCDEF".toCharArray();

  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      String line = in.readLine();
      if (line == null || "".equals(line)) break;
      int[] split = split(line);
      long from = parse(split[0], split[1], line, Integer.valueOf(line.substring(split[2],split[3])));
      if (from < 0L){
        sb.append("  ERROR");
      } else {
        String to = convertToBase(from, Integer.valueOf(line.substring(split[4],split[5])));
        if (to == null){
          sb.append("  ERROR");
        } else {
          sb.append(to);
        }
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
  private static int[] split(String s){
    int[] ret = new int[6];
    char prev = ' ';
    int b = 0;
    for(int a = 0; a < s.length(); ++a){
      char c = s.charAt(a);
      if (prev == ' ' ^ c == ' ') ret[b++] = a;
      prev = c;
    }
    if (b != ret.length) ret[b++] = s.length();
    return ret;
  }
  private static String convertToBase(long from, int base){
    StringBuilder sb = new StringBuilder();
    do{
      sb.append(digits[(int)(from%base)]);
      from /= base;
      if (sb.length() > 7) return null;
    }while(from != 0);
    for(int a = 7-sb.length(); a > 0; --a) sb.append(' ');
    return sb.reverse().toString();
  }
  private static long parse(int start, int end, String from, int base){
    long ret = 0;
    for(int a = start; a < end; ++a){
      ret *= base; ret += toDigit(from.charAt(a));
      if (ret > 0x0FFFFFFF) return -1;
    }
    return ret;
  }
  private static int toDigit(char c){
    if (c <= '9') return c-'0';
    return 10+c-'A';
  }
}
