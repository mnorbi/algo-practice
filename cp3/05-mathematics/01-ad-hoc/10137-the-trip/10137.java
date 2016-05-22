import java.util.*;
import java.io.*;
/*
Fixed point arithmetic
*/
class Main{
  private static final int[] costsInCent = new int[1000];
  public static void main(String[]args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      int cnt = Integer.parseInt(in.readLine().trim());
      if (cnt == 0) break;
      int sumInCents = 0;
      for(int i = 0; i < cnt; ++i){
        costsInCent[i] = parseCents(in.readLine().trim());
        sumInCents += costsInCent[i];
      }
      int overInCents = 0;
      int underInCents = 0;
      for(int i = 0; i < cnt; ++i){
        int diffInCents = costsInCent[i]*cnt-sumInCents;//instead of cost-sum/cnt we scale everything by cnt, to avoid precision problem
        diffInCents = diffInCents/cnt;//now we can scale back and drop everything after the decimal because we cannot pay fractions
        if (diffInCents < 0){
          underInCents -= diffInCents;
        } else {
          overInCents += diffInCents;
        }
      }
      double ret = Math.max(underInCents, overInCents)/100.0;
      sb.append(String.format("$%.2f", ret)).append('\n');
    }
    System.out.print(sb);
  }
  private static int parseCents(String s){
    int ret = 0, a = 0;
    for(; a < s.length(); ++a){
      char c = s.charAt(a);
      if (c == '.'){
        ++a;
        break;
      }
      ret *= 10; ret += (c-'0');
    }
    for(int b = 0; b < 2; ++b, ++a){
      ret *= 10;
      if (a < s.length()){
        ret += (s.charAt(a)-'0');
      }
    }
    return ret;
  }
}
