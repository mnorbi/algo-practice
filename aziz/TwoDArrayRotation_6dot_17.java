public class TwoDArrayRotation_6dot_17{
  public static void main(String[]args){ 
    int[][] mat = new int[][]{
      {1,2,3,4,5,6,7,8},
      {9,10,11,12,13,14,15,16},
      {17,18,19,20,21,22,23,24},
      {25,26,27,28,29,30,31,32},
      {33,34,35,36,37,38,39,40},
      {41,42,43,44,45,46,47,48},
      {49,50,51,52,53,54,55,56},
      {57,58,59,60,61,62,63,64}
    };
    System.out.println("Before:");
    print(mat);
    rotate(mat);
    System.out.println("After:");
    print(mat);
  }

  public static int[][] rotate(int[][] mat){
    if (mat == null) return mat;
    int N =  mat.length;
  
    for(int len = N, i = 0; len > 1; len -= 2, ++i){
      for(int j = 0; j < len - 1; ++j){
        swap(mat, i, i+j, i+j, i+len-1);//right
        swap(mat, i, i+j, i+len-1, i+len-1-j);//bottom
        swap(mat, i, i+j, i+len-1-j, i);//left
      }
    }
    return mat;
  }

  public static void print(int[][] mat){
    for(int i = 0; i < mat.length; ++i){
      for(int j = 0; j < mat.length; ++j){
        System.out.printf("%02d ", mat[i][j]);
      }
      System.out.println();
    }
  }
  private static void swap(int[][] mat, int i1, int j1, int i2, int j2){
    int tmp = mat[i1][j1];
    mat[i1][j1] = mat[i2][j2];
    mat[i2][j2] = tmp;
  }
}
