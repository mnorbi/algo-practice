import java.util.*;
import java.io.*;
public class AntiQuicksort{
  public static void main(String[]args) throws IOException {
    try(
      BufferedReader br = new BufferedReader(new FileReader("antiqs.in"));
      FileWriter fw = new FileWriter("antiqs.out");
    ){
      int N = Integer.valueOf(br.readLine());
      int[] arr = new int[N];
      for(int a = 0; a < N; ++a){
        arr[a] = a+1;
        if (a > 1){
          swap(arr, a, a/2);
        }
      }
      fw.write(""+arr[0]);
      for(int a = 1; a < N; ++a){
        fw.write(" "+arr[a]);
      }
    }
  }
  private static void swap(int[]arr,int a, int b){
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
}
