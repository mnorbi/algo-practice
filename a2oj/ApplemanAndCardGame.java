import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class ApplemanAndCardGame{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
	int[] arr = ints(br.readLine(),2);
	int n = arr[0]; long k = arr[1];
	long[] cnt = new long['Z'-'A'+1];
	String s = br.readLine();
	for(int a = 0; a < n; ++a){
		char c = s.charAt(a);
		cnt[c-'A']++;
	}
	Arrays.sort(cnt);
	long ans = 0L;
	for(int a = cnt.length-1; k > 0; --a){
		if (cnt[a] < k){
			ans += cnt[a]*cnt[a];
			k -= cnt[a];
		} else {
			ans += k*k;
			k = 0;
		}
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
