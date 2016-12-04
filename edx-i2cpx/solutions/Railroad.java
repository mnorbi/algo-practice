import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

public class Railroad{
  public static void main(String[]args) throws IOException{
    try(
            BufferedReader br = new BufferedReader(new FileReader("railroad.in"));
            FileWriter fw = new FileWriter("railroad.out");
    ){
      int[] aux = toArray(br.readLine());
      int N = aux[0], M = aux[1];
      int[][] edges = new int[2*M+1][4];
      int[] vToE= new int[N+1];
      for(int a = 0, b = 1; b <= M; ++b){
        aux = toArray(br.readLine());
        ++a;
        edges[a][0] = aux[0];
        edges[a][1] = aux[1];
        edges[a][2] = b;
        edges[a][3] = vToE[aux[0]];
        vToE[aux[0]] = a;
        if (aux[0] == aux[1]) { continue; }
        ++a;
        edges[a][0] = aux[1];
        edges[a][1] = aux[0];
        edges[a][2] = b;
        edges[a][3] = vToE[aux[1]];
        vToE[aux[1]] = a;
      }
      int degOneCnt = 0, s = -1;
      for(int a = 1; a <= N; ++a){
        int outDeg = outDegOf(a, vToE, edges);
        if (outDeg == 1){
          ++degOneCnt;
          s = a;
        }
      }
      if (s == -1){
        s = 1;
      }
      if (degOneCnt > 1 && N > 2){
        fw.write("IMPOSSIBLE");
      } else {
        int[] ts = new int[M+1];
        if (degOneCnt > 1 && outDegOf(s, vToE, edges) != 1) throw new IllegalStateException();
        dfs(edges, vToE, s, 0, new BitSet(N+1), ts);
        fw.write(""+ts[edges[1][2]]);
        for(int a = 2; a < ts.length; ++a){
          fw.write("\n"+ts[a]);
        }
      }
    }
  }
  private static int outDegOf(int v, int[] vToE, int[][] edges) {
    int ans = 0;
    int[] edge = edges[vToE[v]];
    while(edge[0] != 0){
      ++ans;
      edge = edges[edge[3]];
    }
    return ans;
  }
  static int dfs(int[][] edges, int[] vToE, int v, int t, BitSet vis, int[] ts){
    if (vis.get(v)) { return t; }
    vis.set(v, true);
    int[] edge = edges[vToE[v]];
    while(edge[0] != 0){
      if (ts[edge[2]] == 0){
        ++t;
        ts[edge[2]] = t;
        t = dfs(edges, vToE, edge[1], t, vis, ts);
      }
      edge = edges[edge[3]];
    }
    return t;
  }
  static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
}
