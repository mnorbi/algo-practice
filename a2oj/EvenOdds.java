import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class EvenOdds{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      long[] arr = longs(br.readLine(),2);
      long n = arr[0], k = arr[1];
      long oddCnt = (n+1)/2;
      long ans = 0;
      if (k <= oddCnt){
	ans = 1 + (k-1<<1);//#bug #precedence << has lower precedence than +
      } else {
	k -= oddCnt;
        ans = k<<1;
      }
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
  private static long[] longs(String s, int n){
    long[] arr = new long[n];
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
