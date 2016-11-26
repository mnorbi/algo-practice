import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.BitSet;
public class ShortestPath{
  private static final Long PINF = 2001*1000000000000000L;
  private static final Long NINF = -PINF;

  public static void main(String[]args) throws IOException{
    try(
      BufferedReader br = new BufferedReader(new FileReader("path.in"));
      FileWriter fw = new FileWriter("path.out");
    ){
      int[] arr = toArray(br.readLine());
      int N = arr[0], M = arr[1], S = arr[2];

      long[] sssp = new long[N+1];
      for(int a = 1; a < sssp.length; ++a){
        Arrays.fill(sssp, PINF);
      }
      int[] vToE = new int[N+1];
      long[][] edges = new long[M+1][4];
      for(int a = 1; a <= M; ++a){
        long[] aux = toLongArray(br.readLine());
        edges[a][0] = aux[0];
        edges[a][1] = aux[1];
        edges[a][2] = aux[2];
        edges[a][3] = vToE[(int)aux[0]];
        vToE[(int)aux[0]] = a;
      }
      sssp[S] = 0;
      BitSet noSp = new BitSet(N+1);
      for(int a = 1; a <= N;++a){
        boolean changed = false;
        for(int b = 1; b < edges.length; ++b){
          boolean relaxed = relax(sssp, edges[b]);
          changed = changed || relaxed;
          if ((a == N) && relaxed){
            int source = (int)edges[b][0];
            if (!noSp.get(source)) {
              ArrayDeque<long[]> stack = new ArrayDeque<>(M);
              noSp.set(source, true);
              stack.add(edges[vToE[source]]);
              while (!stack.isEmpty()) {
                long[] next = stack.remove();
                if (next[0] == 0) {
                  continue;
                }
                stack.push(edges[(int) next[3]]);
                int target = (int) next[1];
                if (noSp.get(target)) {
                  continue;
                }
                noSp.set(target, true);
                stack.push(edges[vToE[target]]);
              }
            }
          }
        }
        if (!changed) { break; }
      }
      for(int a = 1; a < sssp.length; ++a){
        if (noSp.get(a)){
          fw.write("-\n");
        } else if (sssp[a] == PINF){
          fw.write("*\n");
        } else {
          fw.write(""+sssp[a]+"\n");
        }
      }
    }
  }
  private static boolean relax(long[] sssp, long[] edge){
    long d;
    if (sssp[(int)edge[0]] == PINF){
      d = PINF;
    } else {
      d = sssp[(int)edge[0]]+edge[2];
    }

    if (sssp[(int)edge[1]] > d){
      sssp[(int)edge[1]] = Math.max(NINF, d);
      return true;
    }

    return false;
  }
  private static long[] toLongArray(String s){
    return Arrays.stream(s.split(" ")).mapToLong(Long::valueOf).toArray();
  }
  private static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
