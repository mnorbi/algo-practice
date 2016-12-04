import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

public class ShortestPathInSparseGraph{
  public static void main(String[]args) throws IOException{
    long time1 = System.currentTimeMillis();
    try(
      BufferedReader br = new BufferedReader(new FileReader("sparse.in"));
      FileWriter fw = new FileWriter("sparse.out");
    ){
      int[] aux = toArray(br.readLine());
      int N = aux[0], M = aux[1];
      int[] vToE = new int[N+1];
      int[][] edges = new int[2*M+1][4];
      for(int a = 1; a <= 2*M; ++a){
        String nextLine = br.readLine();
        if (nextLine == null) break;
        aux = toArray(nextLine);
        edges[a][0] = aux[0];
        edges[a][1] = aux[1];
        edges[a][2] = aux[2];
        edges[a][3] = vToE[aux[0]];
        vToE[aux[0]] = a;
        if(aux[0] == aux[1]) continue;
        ++a;
        edges[a][0] = aux[1];
        edges[a][1] = aux[0];
        edges[a][2] = aux[2];
        edges[a][3] = vToE[aux[1]];
        vToE[aux[1]] = a;
      }

      int[] sssp = new int[N+1];
      Arrays.fill(sssp, Integer.MAX_VALUE);
      PQ pq = new PQ(N);
      pq.add(1,0);
      while(!pq.isEmpty()){
        int id = pq.ids[1];
        int len = pq.vals[1];
        pq.remove();

        if (sssp[id] <= len) {
          throw new IllegalStateException();
        };
        sssp[id] = len;
        int[] edge = edges[vToE[id]];
        while(edge[0] != 0){
          if (sssp[edge[1]] == Integer.MAX_VALUE) {
            pq.add(edge[1], sssp[id] + edge[2]);
          }
          edge = edges[edge[3]];
        }
      }
      fw.write(""+sssp[1]);
      for(int a = 2; a < sssp.length; ++a){
        fw.write(" "+sssp[a]);
      }
    }
    long time2 = System.currentTimeMillis();
    System.out.println(time2-time1);
  }
  static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
  private static class PQ{
    int[] ids;
    int[] vals;
    int[] indxs;
    int last = 0;
    PQ(int size){
      ids = new int[size+1];
      vals = new int[size+1];
      indxs = new int[size+1];
    }
    boolean contains(int id){
      return indxs[id] > 0;
    }
    void add(int id, int val){
      if (!contains(id)){
        ++last;
        ids[last] = id;
        vals[last] = val;
        indxs[id] = last;
        sift(last);
      } else if (vals[indxs[id]] > val){
        vals[indxs[id]] = val;
        sift(indxs[id]);
      }
    }
    boolean isEmpty(){
      return last == 0;
    }
    int[] remove2(){
      int[] ans = new int[]{ ids[1],vals[1] };
      remove();
      return ans;
    }
    void remove(){
      if (last <= 0) throw new IllegalStateException();
      swap(1,last);
      indxs[ids[last]] = 0;
      vals[last] = 0;
      ids[last] = 0;

      --last;
      sink(1);
    }
    void swap(int indx1, int indx2){
      int tmp = indxs[ids[indx1]];
      indxs[ids[indx1]] = indxs[ids[indx2]];
      indxs[ids[indx2]] = tmp;

      tmp = ids[indx1];
      ids[indx1] = ids[indx2];
      ids[indx2] = tmp;

      tmp = vals[indx1];
      vals[indx1] = vals[indx2];
      vals[indx2] = tmp;

    }
    void sink(int idx){
      while(true){
        int left = 2*idx;
        if (left > last) { break; }
        int right = 2*idx+1;
        if (vals[left] < vals[idx] && (right > last || vals[left] <= vals[right])){
          swap(idx,left);
          idx = left;
        } else if (right <= last && vals[right] < vals[idx]){
          swap(idx,right);
          idx = right;
        } else { break; }
      }
    }
    void sift(int idx){
      while(true){
        int par = idx/2;
        if (par <= 0 || vals[par] <= vals[idx]) { break; }
        swap(idx,par);
        idx = par;
      }
    }
  }
}
