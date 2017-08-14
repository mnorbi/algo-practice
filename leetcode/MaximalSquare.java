public class Solution {
    public int maximalSquare(char[][] matrix) {
      if (matrix == null || matrix.length == 0) return 0;
      int R = matrix.length;
      int C = matrix[0].length;
      int[] lens = new int[C];
      int res = 0;
      for(int c = 0; c < C; ++c){
        lens[c] = matrix[0][c] == '1' ? 1 : 0;
        res = Math.max(res, lens[c]);
      }
      for(int r = 1; r < R; ++r){
          int pre = lens[0];
          lens[0] = matrix[r][0] == '1' ? 1 : 0;
          res = Math.max(res, lens[0]);
          for(int c = 1; c < C; ++c){
              if (matrix[r][c] == '0'){
                  pre = lens[c];
                  lens[c] = 0;
              } else {
                  int tmp = lens[c];
                  lens[c] = Math.min(pre, Math.min(lens[c-1], lens[c]))+1;
                  pre = tmp;
              }
              res = Math.max(res, lens[c]);
          }
      }
      return res*res;
    }
}
