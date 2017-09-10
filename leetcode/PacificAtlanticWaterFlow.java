class Solution {
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return Collections.emptyList();
        
        int R = matrix.length;
        int C = matrix[0].length;
        
        boolean[][] pacific = new boolean[R][C];
        boolean[][] athlantic = new boolean[R][C];
        for(int a = 0; a < C; ++a){
            pacific[0][a] = true;
            athlantic[R-1][a] = true;
            dfs(matrix, pacific, 0, a);
            dfs(matrix, athlantic, R-1, a);
        }
        for(int b = 0; b < R; ++b){
            pacific[b][0] = true;
            athlantic[b][C-1] = true;
            dfs(matrix, pacific, b, 0);
            dfs(matrix, athlantic, b, C-1);
        }
        List<int[]> ans = new ArrayList<>();
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if (pacific[r][c] && athlantic[r][c]){
                    ans.add(new int[]{r,c});
                }
            }
        }
        return ans;
    }
    private void dfs(int[][]mat, boolean[][]reach, int r, int c){
        int R = mat.length;
        int C = mat[0].length;
        for(int[] dir : DIRS){
            int nr = r+dir[0], nc = c+dir[1];
            if (nr >= 0 && nr < R && nc >= 0 && nc < C && !reach[nr][nc] && mat[r][c] <= mat[nr][nc]){
                reach[nr][nc] = true;
                dfs(mat, reach, nr, nc);
            }
        }
    }
}
