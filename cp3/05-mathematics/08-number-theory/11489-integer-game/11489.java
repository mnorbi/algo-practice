import java.util.*;
import java.io.*;
/**
 the first move might remove a digit not divisible by 3 dependeing
 on the final sum
 all the subsequent moves must remove a digit divisible by 3
**/
class Main{
  private static int[] arr = new int[3];
  public static void main(String[]args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int tc = Integer.parseInt(in.readLine());
    for(int i = 1; i <= tc; ++i){
      Arrays.fill(arr,0);
      String next = in.readLine();
      for(int a = 0; a < next.length(); ++a){
        ++arr[(next.charAt(a)-'0')%3];
      }
      int sum = arr[1]%3;
      sum += (arr[2]<<1)%3;
      sum %= 3;
      String ans;
      if (arr[sum] == 0) {
        ans = "T";
      } else {
        --arr[sum];
        ans = (arr[0]%2 == 0) ? "S" : "T";
      }
      sb.append("Case ").append(i).append(": ").append(ans).append('\n');
    }
    System.out.print(sb);
  }
}
