import java.util.*;
import java.io.*;
public class FootballBroadcasting2 {
  static byte[][] dirs = new byte[][] {{-1,0}, {1,0}, {0,-1},{0,1}};
  static byte[][][][] par = new byte[2][300][301][301];
  static boolean[][][] valid = new boolean[300][301][301];

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
      for(int x = 1; x <= W; ++x){
        for(int y = 1; y <= H; ++y){
          valid[0][x][y]= isOutside(boundaries[0], x, y);
        }
      }
      for(int t = 1; t < N; ++t){
        for(int x = 1; x <= W; ++x){
          for(int y = 1; y <= H; ++y){
            if (!isOutside(boundaries[t], x, y)){ continue; }
            for(byte[] dir : dirs){
              int prevX = x-dir[0], prevY = y-dir[1];
              if (!isValid(prevX, prevY, W, H)) { continue; }
              if (valid[(t-1)][prevX][prevY]){
                par[0][t][x][y] = dir[0];
                par[1][t][x][y] = dir[1];
                valid[t][x][y] = true;
              }
            }
          }
        }
      }
      for(int x = 1; x <= W; ++x){
        for(int y = 1; y <= H; ++y){
          if (valid[(N-1)][x][y]){
            print(fw, N-1, x, y, par);
            long time2 = System.currentTimeMillis();
            System.out.println(time2 - time1);
            return;
          }
        }
      }
      fw.write("Impossible");
    }
    long time2 = System.currentTimeMillis();
    System.out.println(time2-time1);
  }

  private static void print(FileWriter fw, int t, int x, int y, byte[][][][] par) throws IOException {
    if (t == 0){
      fw.write(""+(x-par[0][t][x][y] + " "+(y-par[1][t][x][y])));
    } else {
      print(fw, t-1, x-par[0][t][x][y], y-par[1][t][x][y], par);
      fw.write("\n"+x+" "+y);
    }
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
}
