import java.util.*;
import java.io.*;

class Solver{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
      int n = Integer.valueOf(line);
      int x = 0, y = 0, z = 0;
      while(n-->0){
        int[] arr = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        x += arr[0]; y += arr[1]; z += arr[2];
      }
      if (x == 0 && y == 0 && z == 0) System.out.println("YES");
      else System.out.println("NO");
    }
  }
}
