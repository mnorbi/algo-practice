import java.util.*;
import java.io.*;
public class HakunaMatataSort{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("sort.in"));
      FileWriter fw = new FileWriter("sort.out");
    ){
      int[] arr = toArray(br.readLine());
      int N = arr[0];
      arr = toArray("-1 "+br.readLine());
      int[] idx = new int[N+1];
      for(int a = 1; a <= N; ++a){
        idx[arr[a]] = a;
      }
      StringBuilder sb = new StringBuilder();
      int cnt = 0;
      for(int a = 1; a <= N; ++a){
        while(arr[a] != a){
          sb.append(idx[arr[a]]+" "+(idx[arr[a]-1])+"\n");
          ++cnt;
          swap(arr, idx, a, idx[arr[a]-1]);
        }
      }
      fw.write(""+cnt+"\n");
      fw.write(sb.toString());
    }
  }
  static void swap(int[]arr,int[]idx,int id1, int id2){
    int val1 = arr[id1];
    int val2 = arr[id2];
    idx[val1] = id2;
    idx[val2] = id1;
    arr[id1] = val2;
    arr[id2] = val1;
  }
  static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
