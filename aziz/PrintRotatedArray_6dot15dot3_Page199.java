public class PrintRotatedArray_6dot15dot3_Page199{
  public static void main(String[]args){
    printRotated(20);
  }

  static void printRotated(int len){
    if (len <= 0) return;
    print(0,0);
    int w = 0;
    int i = 0;
    int j = 0;
    int cnt = 0;
    int[][] dir = new int[][]{{0,-1}, {1,0}, {0,1}, {-1,0}};
    while(true){
      w+=2;
      --i;
      ++j;
      for(int d = 0; d < dir.length; ++d){
        for(int k = 0; k < w; ++k){
          if (cnt++ >= len) {
            System.out.println();
            return;
          }
          i += dir[d][0];
          j += dir[d][1];
          print(i,j);
        }
      }
    }
  }
  static void print(int i, int j){
    System.out.printf("[%d,%d]", i, j);
  }

}
