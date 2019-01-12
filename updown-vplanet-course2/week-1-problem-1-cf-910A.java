import java.util.*;
import java.io.*;
class Solver{
  public static void main(String[]args) throws IOException {
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String[] arr = br.readLine().split("\\s+");
      int N = Integer.valueOf(arr[0]);
      int d = Integer.valueOf(arr[1]);
      String s = br.readLine();
      int ans = 0, a = 1, b;
      while(true){
        b =Math.min(N,a+d);
        while(s.charAt(b-1) != '1') --b;
        if (b == a) { ans = -1; break;}
        a = b;
        ++ans;
        if (a == N){ break; }
      }
     System.out.println(ans);
    }
  }
}
