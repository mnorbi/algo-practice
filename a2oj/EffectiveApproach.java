import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class EffectiveApproach{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int n = Integer.valueOf(br.readLine());
      int[] arr = ints(br.readLine(), n);
      int m = Integer.valueOf(br.readLine());
      int[] qs = ints(br.readLine(), m);
      int[] pos = new int[n+1];//#bug +1 missing
      for(int a = 0; a < n; ++a){
        pos[arr[a]] = a+1;
      }
      long cmp = 0;//#bug was int
      for(int a = 0; a < m; ++a){
        cmp += pos[qs[a]];
      }
      long cmp2 = (long)m*(n+1)-cmp;
      System.out.println(cmp + " " + cmp2);
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
