import java.util.*;
import java.io.*;
class Main{
  private static int[] arr = new int[3];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      Arrays.fill(arr, 0);
      String line = in.readLine();
      if (line == null || "".equals(line)) break;
      for(int a = 0, b = 0;a < line.length();){
        char c = line.charAt(a++);
        if (!Character.isDigit(c)) {
          continue;
        }
        while(Character.isDigit(c)){
          arr[b] *= 10; arr[b] += Character.getNumericValue(c);
          if (a >= line.length()) break;
          c = line.charAt(a++);
        }
        ++b;
      }
      double ncows = arr[0];
      double ncars = arr[1];
      double nshow = arr[2];
      double winProbability = ncars/(ncars+ncows)*(ncars-1)/(ncars-1+ncows-nshow)
      +ncows/(ncars+ncows)*(ncars)/(ncars+ncows-1-nshow);
      sb.append(String.format("%.5f", winProbability)).append('\n');
    }
    System.out.print(sb);
  }
}
