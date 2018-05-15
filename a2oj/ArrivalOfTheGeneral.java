import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class ArrivalOfTheGeneral{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int n = Integer.valueOf(br.readLine());
      int[] arr = ints(br.readLine(),n);
      int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
      for(int a = 0; a < n; ++a){
        min = Math.min(min, arr[a]);
        max = Math.max(max, arr[a]);
      }
      int maxPos = -1;
      while(max != arr[++maxPos]);
      int minPos = n;
      while(min != arr[--minPos]);
      int ans = maxPos > minPos ? -1 : 0;
      ans += maxPos;
      ans += n-1-minPos;
      System.out.println(ans);
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
