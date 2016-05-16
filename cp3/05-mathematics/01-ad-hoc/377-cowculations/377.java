import java.util.*;
import java.io.*;
class Main{
  private static int[] COW_TO_NUM = new int[] {0b01/*U*/, 0b00/*V*/, 0b10/*C*/, 0b11/*D*/};
  
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(in.readLine().trim());
    StringBuilder sb = new StringBuilder("COWCULATIONS OUTPUT\n");
    for(int a = 1; a <= N; ++a){
      int num1 = parseCow(in.readLine().trim());
      int num2 = parseCow(in.readLine().trim());
      for(int b = 0; b < 3; ++b){
        String op = in.readLine().trim();
        switch(op){
          case "A":
            num2 += num1; break;
          case "L":
            num2 <<= 2; break;
          case "R":
            num2 >>= 2; break;
          default:
        }
      }
      int result = parseCow(in.readLine().trim());
      if (result == num2){
        sb.append("YES\n");
      } else {
        sb.append("NO\n");
      }
    }
    sb.append("END OF OUTPUT\n");
    System.out.print(sb);
  }
  
  static int parseCow(String cow){
    int ret = 0;
    for(int i = 0; i < cow.length(); ++i){
      char next = cow.charAt(i);
      ret <<= 2;
      ret |= COW_TO_NUM[(int)next%10-5];
    }
    return ret;
  }
}
