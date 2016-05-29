import java.util.*;
import java.io.*;
class Main{
  public static void main(String[]args) throws IOException{
    int[][] board = new int[9][9];
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    int row = -1, col = -1;
    while(true){
      String nextLine = in.readLine();
      if (nextLine == null || "".equals(nextLine)) break;
      ++row;
      col = -1;
      for(int a = 0; a < nextLine.length(); ++a){
        char c = nextLine.charAt(a);
        if (Character.isDigit(c)){
          board[row][++col] = Character.getNumericValue(c);
        } else {
          board[row][++col] = 0;
        }
      }
      if (col != 8) throw new IllegalArgumentException();
    }
    if (row != 8) throw new IllegalArgumentException();
    boolean solved = solve(board);
    if (solved){
      print(board);
    }
  }
  private static void print(int[][] board){
    for(int a = 0; a < board.length; ++a){
      System.out.println(Arrays.toString(board[a]));
    }
  }
  private static boolean solve(int[][] board){
    Deque<Point> unfilled = new LinkedList<>();
    for(int a = 0; a < board.length; ++a){
      for(int b = 0; b <board[a].length; ++b){
        if (board[a][b] == 0){
          unfilled.add(Point.of(a,b));
        }
      }
    }
    return solve(board, unfilled);
  }
  private static boolean solve(int[][] board, Deque<Point> unfilled){
    if (unfilled.size() == 0) return true;
    Point point = unfilled.removeLast();
    for(int a = 1; a <= 9; ++a){
      boolean ok = checkColumn(board, point, a);
      ok &= checkRow(board, point, a);
      ok &= checkSegment(board, point, a);
      if (ok){
        board[point.row][point.col] = a;
        boolean solved = solve(board, unfilled);
        if (solved) return true;
        board[point.row][point.col] = 0;
      }
    }
    unfilled.addLast(point);
    return false;
  }
  private static boolean checkRow(int[][]board, Point p, int val){
    for(int a = 0; a < 9; ++a){
      if (board[p.row][a] == val) return false;
    }
    return true;
  }
  private static boolean checkColumn(int[][]board, Point p, int val){
    for(int a = 0; a < 9; ++a){
      if (board[a][p.col] == val) return false;
    }
    return true;
  }
  private static boolean checkSegment(int[][]board, Point p, int val){
    for(int a = 0; a < 3; ++a){
      for(int b = 0; b < 3; ++b){
        if (board[p.row-p.row%3+a][p.col-p.col%3+b] == val) return false;
      }
    }
    return true;
  }
}
class Point{
  final int row, col;
  Point(int row, int col){
    this.row = row;
    this.col = col;
  }
  static Point of(int row, int col){
    return new Point(row, col);
  }
}
