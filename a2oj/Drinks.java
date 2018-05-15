import java.util.*;
import java.io.*;

class Solver{
  public static void main(String[]args)throws Exception{
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
      String line = br.readLine();
      int n = Integer.valueOf(line);
      if (n > 0){
        int[] arr = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
        int sum = 0;
        for(int a = 0; a < Math.min(arr.length, n); ++a){
          sum += arr[a];
        }
        System.out.println((double)sum/n);
      } else {
        System.out.println(0);
      }
    }
  }
}
