import java.util.*;
import java.io.*;
class Main{
  static final int PRIME = 131071;
  static final int[] rems = new int[10000+1];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    precompute();
    StringBuilder bitStr = newStringBuilder(in.readLine());
    while(bitStr != null){
      StringBuilder next = newStringBuilder(in.readLine());
      while(bitStr.charAt(bitStr.length()-1) != '#'){
        bitStr.append(next);
        next = newStringBuilder(in.readLine());
      }
      int ans = 0;
      int N = bitStr.length();
      for(int a = 0; a < N; ++a){
        if (bitStr.charAt(a) == '1'){
          ans = (ans+rems[(N-1)-a])%PRIME;
        }
      }
      sb.append(ans == 0 ? "YES\n" : "NO\n");
      bitStr = next;
    }
    System.out.print(sb);
  }
  static void precompute(){
    rems[0] = 1;
    for(int a = 1; a < rems.length; ++a){
      rems[a] = (rems[a-1]*2)%PRIME;
    }
  }
  static StringBuilder newStringBuilder(String s){
    if (s == null || s.length() == 0) return null;
    return new StringBuilder(s);
  }
}
