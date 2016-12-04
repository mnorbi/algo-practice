import java.util.*;
import java.io.*;
public class ShortestPathInDenseGraph{
  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("dense.in"));
      FileWriter fw = new FileWriter("dense.out");
    ){
      int[] aux = toArray(br.readLine());
      int N = aux[0], S = aux[1], F = aux[2];
      int[][] mat = new int[N+1][N+1];
      for(int a = 1; a <= N; ++a){
        aux = toArray(br.readLine());
        for(int b = 1; b <= N; ++b){
          mat[a][b] = aux[b-1];
        }
      }
      long[] sssp = new long[N+1];
      BitSet visited = new BitSet(N+1);
      Arrays.fill(sssp, Long.MAX_VALUE);
      sssp[S] = 0;
      for(int a = 1; a <= N; ++a){
        int min = -1;
        for(int b = 1; b <= N; ++b){
          if (visited.get(b)) { continue; }
          if (sssp[b] != Long.MAX_VALUE){
            if (min == -1 || sssp[min] > sssp[b]){
              min = b;
            }
          }
        }
        if (min == -1) { break; }
        if (min == F) { break; }
        for(int b = 1; b <= N; ++b){
          if (mat[min][b] == -1){ continue; }
          sssp[b] = Math.min(sssp[b],sssp[min]+mat[min][b]);
        }
        visited.set(min,true);
      }
      fw.write(""+(sssp[F] != Long.MAX_VALUE ? sssp[F] : -1) );
    }
  }
  private static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
