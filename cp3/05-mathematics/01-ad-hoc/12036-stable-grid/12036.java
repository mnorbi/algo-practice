import java.util.*;
import java.io.*;
class Main{
  private static int[] arr = new int[100];
  public static void main(String[]args) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int tcc = Integer.parseInt(in.readLine().trim());
    StringBuilder sb = new StringBuilder();
    boolean possible = true;
    for(int i = 1; i <= tcc; ++i, possible = true){
      Arrays.fill(arr, 0);
      sb.append("Case "+i+": ");
      int size = Integer.parseInt(in.readLine().trim());
      for(int j = 0; j < size; ++j){
        String line = in.readLine();
        int val = -1;
        for(int k = 0;;++k){
          if (k < line.length()){
            char c = line.charAt(k);
            if (Character.isDigit(c)){
              if (val < 0) val = 0;
              val *= 10; val += Character.getNumericValue(c);
              continue;
            }
	  }
          if (val > -1){ 
            ++arr[val];
            if(arr[val] > size && possible) {
              sb.append("no\n");
              possible = false;
            }
            val = -1;
          }
          if (k >= line.length()) break;
        }
      }
      if (possible){
        sb.append("yes\n");
      }
    }
    System.out.print(sb);
  }
}
