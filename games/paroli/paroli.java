import java.util.*;
import java.io.*;

class Main{
  static int ROWS = 10;
  static int COLS = 8;
  static char[][] input = new char[ROWS][COLS];
  static int CONSTRAINT_COL = -1;
  static int CONSTRAINT_ROW = -1;
  static int CONSTRAINT_VAL = -1;
  static int[][] TARGET = new int[ROWS/2][2];
  public static void main(String[]args)throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String[] constraints = in.readLine().split(" ");
    CONSTRAINT_COL = Integer.parseInt(constraints[0]);
    CONSTRAINT_ROW = Integer.parseInt(constraints[1]);
    CONSTRAINT_VAL = constraints[2].charAt(0);
    for(int a = 0; a < TARGET.length; ++a){
      String[] next = in.readLine().split(" ");
      TARGET[a][0] = Integer.parseInt(next[0]);
      TARGET[a][1] = Integer.parseInt(next[1]);
    }
    int row = -1, col = 0;
    while(true){
      String next = in.readLine();
      if (next == null || "".equals(next)) break;
      ++row;
      if (row == ROWS) {
        row = 0;
        ++col;
      }
      input[row][col] = next.charAt(0);
    }
    if (col+1 != COLS && row+1 != ROWS) throw new IllegalArgumentException(String.format("[%d,%d]", col,row));
    int[] filling = solve();
    if (filling == null) throw new IllegalArgumentException("unsolvable");
    printFilling(filling);
    printTarget(filling);
  }
  private static void printTarget(int[] filling){
    StringBuilder sb = new StringBuilder();
    for(int a = 0; a < TARGET.length; ++a){
      sb.append(input[filling[TARGET[a][0]]][TARGET[a][1]]);
    }
    System.out.println(sb);
  } 
  private static int[] solve(){
    int[] permutation = new int[ROWS];
    for(int a = 0; a < ROWS; ++a) permutation[a] = a;
    boolean ok = solve(permutation, 0);
    if (ok){
      return permutation;
    }
    return null;
  }
  private static boolean solve(int[] permutation, int pos){
    if (pos >= permutation.length){
      boolean ok = check(permutation);
      if (ok) return ok;
    }
    for(int a = pos; a < permutation.length; ++a){
      swap(permutation, pos, a);
      boolean ok = solve(permutation, pos+1);
      if (ok) return ok;
      swap(permutation, pos, a);
    }
    return false;
  }
  private static void swap(int[] arr, int a, int b){
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }
  private static boolean check(int[] filling){
    for(int a = 0; a < filling.length/2; ++a){
      if (a == CONSTRAINT_COL && input[filling[a]][CONSTRAINT_ROW] != CONSTRAINT_VAL) {
        return false;
      }
      for(int b = 0; b < filling.length/2; ++b){
        char c1 = input[filling[a]][((a%2==0)?3:0)+b];
        char c2 = input[filling[filling.length/2+b]][(((b%2)==0)?0:3)+a];
        if (c1 != c2){
          return false;
        }
      }
    }
    return true;
  }
  private static void printFilling(int[] filling){
    StringBuilder sb = new StringBuilder();
    for(int a = 0; a < 3; ++a){
      for(int b = 0; b < 2; ++b) sb.append(' ');
      for(int b = 0; b < filling.length/2; b+=2){
        sb.append(' ').append(input[filling[b]][a]);
      }
      sb.append('\n');
    }
    for(int a = 0; a < filling.length/2; ++a){
      char[] next = input[filling[filling.length/2+a]];
      if (a%2 == 0){
        for(int b = 0; b < 3; ++b) sb.append(' ');
      }
      sb.append(next).append('\n');
    }
    for(int a = COLS-3; a < COLS; ++a){
      for(int b = 0; b < 3; ++b) sb.append(' ');
      for(int b = 1; b < filling.length/2; b+=2){
        sb.append(' ').append(input[filling[b]][a]);
      }
      sb.append('\n');
    }
    System.out.print(sb);
  }
}
