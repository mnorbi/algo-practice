import java.util.*;
import java.io.*;

class Solver{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
      int n = Integer.valueOf(line);
      line = br.readLine();
      int ans = 0;
      for(int hi = 1; hi < Math.min(n, line.length()); ++hi){
        ans += line.charAt(hi) == line.charAt(hi-1) ? 1 : 0;
      }
      System.out.println(ans);
    }
  }
}

