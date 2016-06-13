import java.util.*;
import java.io.*;
/**
Converted to 4 nim piles:
      x
      x
      x
xxxxx O xxxx
      x
      x
**/
class Main{
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int tcc = Integer.parseInt(in.readLine());
    for(int a = 1; a <= tcc; ++a){
      String[] tokens = in.readLine().split(" ");
      int width = Integer.parseInt(tokens[0]);
      int length = Integer.parseInt(tokens[1]);
      int row = Integer.parseInt(tokens[2]);
      int col = Integer.parseInt(tokens[3]);
        
      sb.append((row^(width-1-row)^col^(length-1-col)) == 0 ? "Hansel" : "Gretel").append('\n');
    }
    System.out.print(sb);
  }
}
