import java.util.*;
import java.io.*;
public class FootballBroadcasting{
  static int[][] dirs = new int[][] {{-1,0}, {1,0}, {0,-1},{0,1}};

  public static void main(String[]args) throws IOException{
    long time1 = System.currentTimeMillis();
    try(
      BufferedReader br = new BufferedReader(new FileReader("broadcast.in"));
      FileWriter fw = new FileWriter("broadcast.out");
    ){
      int[] aux = toArray(br.readLine());
      int W = aux[0], H = aux[1];
      aux = toArray(br.readLine());
      int N = aux[0];
      int[][] boundaries = new int[N][4];
      for(int a = 0; a < N; ++a){
        boundaries[a] = toArray(br.readLine());
      }
      BitSet[] visited = new BitSet[W+1];
      for(int a = 0; a < visited.length; ++a) { visited[a] = new BitSet(H+1); }
      LinkedList<Node> queue = new LinkedList<>();
      for(int a = 1; a <= W; ++a){
        for(int b = 1; b <= H; ++b){
          if (isOutside(boundaries[0], a, b)){
            queue.addLast(new Node(a,b,null));
          }
        }
      }
      for(int t = 1; t < N; ++t){
        int cnt = queue.size();
        while(cnt--> 0){
          Node cur = queue.removeFirst();
          for(int[] dir : dirs){
            int nextX = cur.x+dir[0];
            int nextY = cur.y+dir[1];
            if (isValid(nextX, nextY, W, H) && !visited[nextX].get(nextY) && isOutside(boundaries[t],nextX,nextY)){
              visited[nextX].set(nextY, true);
              queue.addLast(new Node(nextX, nextY, cur));
            }
          }
        }
        for(int x = 1; x <= W; ++x){
          visited[x].clear();
        }
      }
      if (queue.size() > 0){
        Node[] nodes = new Node[N];
        Node last = queue.peekLast();
        for(int a = N-1; a >= 0; --a){
          nodes[a] = last;
          last = last.par;
        }
        fw.write(""+nodes[0].x+" "+nodes[0].y);
        for(int a = 1; a < N; ++a){
          fw.write("\n"+nodes[a].x+" "+nodes[a].y);
        }
      } else {
        fw.write("Impossible");
      }
    }
    long time2 = System.currentTimeMillis();
    System.out.println(time2-time1);
  }
  static boolean isValid(int x, int y, int W, int H){
    boolean valid = x >= 1 && x <= W && y >= 1 && y <= H;
    return valid;
  }
  static boolean isOutside(int[] boundary, int x, int y){
    boolean inside = x >= boundary[0] && x <= boundary[2] && y >= boundary[1] && y <= boundary[3];
    return !inside;
  }
  static int[] toArray(String s){
    return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
  }
  static class Node{
    int x, y;
    Node par;
    Node(int x, int y, Node par){
      this.x = x;
      this.y = y;
      this.par = par;
    }
  }
}
