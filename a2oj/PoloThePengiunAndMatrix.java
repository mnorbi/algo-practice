import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class PoloThePengiunAndMatrix{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
	int[] arr = ints(br.readLine(), 3);
	int n = arr[0], m = arr[1], d = arr[2];
	int[] vec = new int[n*m];
	int c = 0;
 	while(n-->0){
		arr = ints(br.readLine(), m);
		for(int a = 0; a < m; ++a){
			if (a > 0 && arr[a] %d != arr[a-1] %d){
				System.out.println(-1);
				return;
			}
			vec[c++] = arr[a];
		}
	}
	Arrays.sort(vec);
	int midVal = vec[vec.length/2];
	int ans = 0;
	for(int val : vec){
		ans += Math.abs((midVal-val)/d);
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
