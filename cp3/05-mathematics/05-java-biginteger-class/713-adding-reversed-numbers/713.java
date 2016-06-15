import java.util.*;
import java.io.*;
class Main{
  private static final char[] sum = new char[201];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int tc = Integer.parseInt(in.readLine());
    while(tc-->0){
      String nums = in.readLine();
      int a = 0, b = 0;
      int aStart = 0, aEnd = 0;
      while(true){
        char c = nums.charAt(a++);
        if (c == ' ') break;
        if (c != '0') aEnd = a;
      }
      int bStart = a, bEnd = 0;
      while(a < nums.length()){
        char c = nums.charAt(a++);
        if (c != '0') bEnd = a;
      }

      int cry = 0, sumEnd = 0;
      for(a = 0, b = bStart; a < aEnd || b < bEnd; ++sumEnd){
        int s = (a>=aEnd?0:nums.charAt(a++)-'0')+(b>=bEnd?0:nums.charAt(b++)-'0')+cry;
        sum[sumEnd] = (char)((s%10)+'0');
        cry = s/10;
      }
      if (cry != 0){
        sum[sumEnd++] = (char)(cry+'0');
      }
      int sumStart = 0;
      for(;sum[sumStart] == '0' && sumStart < sumEnd; ++sumStart);
      for(int s = sumStart; s < sumEnd; ++s){
        sb.append(sum[s]);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
}
