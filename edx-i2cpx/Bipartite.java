import java.util.*;
import java.io.*;
public class Bipartite{
  public static void main(String[]args) throws IOException {
    try(
      BufferedReader br = new BufferedReader(new FileReader("bipartite.in"));
      FileWriter fw = new FileWriter("bipartite.out");
    ){
      int[] arr = toArray(br.readLine());
      int N = arr[0], M = arr[1];

      int[] vToE = new int[N+1];
      int[][] edges = new int[2*M+1][2];

      for(int a = 1; a <= 2*M; ++a){
        arr = toArray(br.readLine());
        int source = arr[0], target = arr[1];

        int nextEdgeId = vToE[source];
        vToE[source] = a;
        edges[a] = new int[]{ target, nextEdgeId };

        ++a;
        nextEdgeId = vToE[target];
        vToE[target] = a;
        edges[a] = new int[] { source, nextEdgeId};
      }

      BitSet visited = new BitSet(N+1);
      BitSet color = new BitSet(N+1);

      ArrayDeque<int[]> deq = new ArrayDeque<>();
      for(int a = 1; a <= N; ++a){
        if (visited.get(a)) { continue; }
        visited.set(a, true);//color black as default
        if (vToE[a] != 0){
          deq.addLast(new int[]{ a, vToE[a] });
        }
        while(!deq.isEmpty()){
          arr = deq.removeLast();
          int source = arr[0];
          int edgeId = arr[1];
          int[] edge = edges[edgeId];
          int target = edge[0];
          int nextEdgeId = edge[1];
          if (nextEdgeId != 0){
            deq.addLast(new int[] { source, nextEdgeId } );
          }
          if (!visited.get(target)){
            visited.set(target,true);
            color.set(target, true ^ color.get(source));
            if (vToE[target] != 0) {
              deq.addLast(new int[]{target, vToE[target]});
            }
          } else if (color.get(target) == color.get(source)){
            fw.write("NO");
            return;
          }
        }
      }
      fw.write("YES");
    }
  }
  private static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
