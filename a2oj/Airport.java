import java.util.*;
import java.io.*;
import static java.lang.Character.isWhitespace;

public class Airport{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      int[] arr = ints(br.readLine(),2);
      int n = arr[0], m = arr[1];
      arr = ints(br.readLine(), m);

      PriorityQueue<Integer> pq = new PriorityQueue<>((v1,v2) -> -Integer.compare(v1,v2));
      for(int a = 0; a < m; ++a){
        pq.add(arr[a]);
      }
      int max = 0;
      for(int a = n; a > 0; --a){
        int v = pq.remove();
        max += v;
        --v;
        pq.add(v);
      }

      Arrays.sort(arr);
      int min = 0;
      for(int a = n, b = 0; a > 0;--a){
        if (arr[b] > 0){
          min += arr[b];
          --arr[b];
        } else {
          ++b;
          ++a;
        }
      }

      System.out.println(max + " " + min);
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
