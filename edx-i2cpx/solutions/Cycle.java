import java.io.*;
import java.util.*;

public class Cycle{
  public static void main(String[]args) throws IOException{
    try(
            BufferedReader br = new BufferedReader(new FileReader("cycle.in"));
            FileWriter fw = new FileWriter("cycle.out");
    ){
      int[] arr = nextArray(br.readLine());
      int N = arr[0], M = arr[1];
      int[] vToE = new int[N+1];
      int[][] edges = new int[M+1][2];
      for(int a = 1; a <= M; ++a){
        arr = nextArray(br.readLine());
        int sourceVertex = arr[0];
        int targetVertex = arr[1];

        int nextEdgeId = vToE[sourceVertex];
        vToE[sourceVertex] = a;

        edges[a] = new int[]{ targetVertex, nextEdgeId };
      }

      int[] prev = new int[N+1];
      int[][] times = new int[N+1][2];
      ArrayDeque<int[]> deq = new ArrayDeque<>();


      for(int a = 1, t = 1; a <= N; ++a) {
        if (times[a][0] == 0) {
          deq.addLast(new int[]{a, vToE[a]});
          times[a][0] = t++;
          while(!deq.isEmpty()) {
            arr = deq.removeLast();
            int source = arr[0], edgeId = arr[1];
            if (edgeId == 0) {
              //no more edges
              times[source][1] = t++;
            } else {
              int[] edge = edges[edgeId];
              int target = edge[0];
              int nextEdge = edge[1];

              if (times[target][0] != 0 && times[target][1] == 0) {
                //cycle found
                fw.append("YES\n");
                StringBuilder sb = new StringBuilder();
                sb.append(new StringBuilder("" + target).reverse().toString());
                while (source != target) {
                  sb.append(" " + new StringBuilder("" + source).reverse().toString());
                  source = prev[source];
                }
                fw.write(sb.reverse().toString());
                return;
              }

              deq.addLast(new int[]{source, nextEdge});
              if (times[target][0] == 0) {
                //next dfs tree edge
                times[target][0] = t++;
                prev[target] = source;
                deq.addLast(new int[]{target, vToE[target]});
              }
            }
          }
        }
      }
      fw.write("NO");
    }
  }
  private static int[] nextArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
