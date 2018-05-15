import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class CodeForcesTemplate{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
    }
  }
  private static int[] ints(String s, int n){
    int[] arr = new int[n];
    char c, pc = ' ';
    int len = s.length();
    for(int a = 0, hi = 0;hi < len;++hi){
      c = s.charAt(hi);
      if (!isWhitespace(c)){
        arr[a] *= 10; arr[a] += (c-'0');
      } else {
        a += !isWhitespace(pc) ? 1 : 0;
      }
      pc = c;
    }
    return arr;
  }
  private static int[] ints(String s){
    return Arrays.stream(s.split("\\s+")).mapToInt(Integer::valueOf).toArray();
  }
}
