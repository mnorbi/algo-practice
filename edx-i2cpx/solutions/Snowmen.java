import java.math.*;
import java.util.*;
import java.io.*;
public class Snowmen{
  public static void main(String[]args) throws IOException{
    try(BufferedReader in = new BufferedReader(new FileReader("snowmen.in")); BufferedWriter out = new BufferedWriter(new FileWriter("snowmen.out"));){
      int N = Integer.valueOf(in.readLine());
      int[] size = new int[N+1];
      int[] par = new int[N+1];
      BigDecimal res = BigDecimal.valueOf(0);
      for(int a = 1; a <= N; ++a){
        int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        if (arr[1] > 0){
          size[a] = size[arr[0]]+arr[1];
          par[a] = arr[0];
        } else {
          size[a] = size[par[arr[0]]];
          par[a] = par[par[arr[0]]];
        }
        res = res.add(BigDecimal.valueOf(size[a]));
      }
      out.write(""+res.toString());
    }
  }
}
