import java.util.*;
import java.io.*;
class Main{
  static final int SIZE = 4;
  static final int RUN_LENGTH = 4;
  static char[][] board = new char[SIZE][SIZE];
  static int[][] dirs = new int[][]{
    {0,1}, {1,0}, {1,1}, {-1,1}
  };
  public static void main(String[]args) throws IOException{
//    long start = System.currentTimeMillis();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    while(true){
      if (in.readLine().charAt(0) == '$') break;
      for(int a = 0; a < SIZE; ++a){
        String line = in.readLine();
        for(int b = 0; b < SIZE; ++b){
          board[a][b] = line.charAt(b);
        }
      }
      sb.append(solve(board)).append('\n');
    }
//    long end = System.currentTimeMillis();
//    sb.append(end-start).append('\n');
    System.out.print(sb);
  }
  static String solve(char[][] board){
    int[] sol = new int[2];
    int point = solve(board,'x',Integer.MIN_VALUE, Integer.MAX_VALUE,sol);
    if (isWin(point, 'x')) return String.format("(%d,%d)",sol[0],sol[1]);
    return "#####";
  }
  static int solve(char[][] board, char player, int alpha, int beta, int[] sol){
    int point = initPointOf(player);
    for(int a = 0; a < SIZE; ++a){
      for(int b = 0; b < SIZE; ++b){
        if (board[a][b] == '.'){
          board[a][b] = player;
          boolean win = isWin(a,b);
          boolean isCutoff = false;
          if (!win){
            int otherPoint = solve(board, other(player), alpha, beta, sol);
            point = betterOf(point, otherPoint, player);
            alpha = newAlpha(point, alpha, player);
            beta = newBeta(point, beta, player);
            if (beta <= alpha) isCutoff = true;
            win = isWin(point, player);
          }
          board[a][b] = '.';
          if (win) {
            sol[0] = a; sol[1] = b;
            return winPointOf(player);
          }
          if (isCutoff) break;
        }
      }
    }
    return point;
  }
  static int newAlpha(int point, int alpha, char player){
    return player == 'x' ? betterOf(point, alpha, player) : alpha;
  }
  static int newBeta(int point, int beta, char player){
    return player == 'o' ? betterOf(point, beta, player) : beta;
  }
  static int winPointOf(int player){
    return (player == 'x') ? 1 : -1;
  }
  static boolean isWin(int point, char player){
    return point == winPointOf(player);
  }
  static char other(char player){
    return (player == 'x') ? 'o' : 'x';
  }
  static int betterOf(int pointA, int pointB, char player){
    return (player == 'x') ? Math.max(pointA, pointB) : Math.min(pointA, pointB);
  }
  static int initPointOf(char player){
    return (player == 'x') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
  }
  static boolean isWin(int a, int b){
    for(int[] dir : dirs){
      int cnt = 1;
      for(int s = -1; s < 2; s+=2){
        for(int aa = a+s*dir[0], bb = b+s*dir[1];; aa += s*dir[0], bb += s*dir[1]){
          if (aa < 0 || bb < 0 || aa >= SIZE || bb >= SIZE || board[aa][bb] != board[a][b]) break;
          ++cnt;
        }
        if (cnt >= RUN_LENGTH) return true;
      }
    }
    return false;
  }
}
