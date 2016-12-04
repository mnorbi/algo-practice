import java.util.*;
import java.io.*;
public class APlusB{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader in = new BufferedReader(new FileReader("aplusb.in"));
      BufferedWriter out = new BufferedWriter(new FileWriter("aplusb.out"));
    ){
     int[] arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
     out.write(""+(arr[0]+arr[1]));
    }
  }
}
