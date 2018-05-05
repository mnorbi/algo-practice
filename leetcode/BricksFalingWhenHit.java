import java.util.*;

class BricksFallingWhenHit {
    static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        for(int[] hit : hits){
            if (grid[hit[0]][hit[1]] == 1){
                grid[hit[0]][hit[1]] = -1;
            }
        }

        BitSet vis = new BitSet();
        dfs(grid,vis);
        int[] ans = new int[hits.length];
        int n = grid.length, m = grid[0].length;
        for(int a = hits.length-1;a >=0; --a){//#bug #indexing
            int[] hit = hits[a];
            if (grid[hit[0]][hit[1]] == -1){//same brick deletion not supported
                grid[hit[0]][hit[1]] = 1;
                boolean startDfs = (hit[0] == 0);//#bug #logical-error was missing
                if (!startDfs) {
                    for (int[] dir : dirs) {
                        int nr = hit[0] + dir[0], nc = hit[1] + dir[1];
                        startDfs = (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1 && vis.get(nr * m + nc));//#bug #bound-check
                        if (startDfs) break;
                    }
                }
                if (startDfs) {
                    Deque<int[]> deq = new ArrayDeque<>();
                    vis.set(hit[0] * m + hit[1]);
                    deq.addLast(hit);
                    int delta = dfs(grid, deq, vis);
                    ans[a] = delta;//#bug #counting was inside for loop
                }
            }
        }
        return ans;
    }
    void dfs(int[][]grid, BitSet vis){
        Deque<int[]> deq = new ArrayDeque<>();
        int n = grid.length, m = grid[0].length;
        for(int a = 0; a < m; ++a){
            if (grid[0][a] == 1 && !vis.get(a)){
                vis.set(a);
                deq.addLast(new int[]{0,a});
            }
            dfs(grid,deq,vis);
        }
    }
    int dfs(int[][]grid, Deque<int[]>deq,BitSet vis){
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        while(!deq.isEmpty()){
            int[] p = deq.removeLast();
            for(int[] dir : dirs){
                int nr = p[0]+dir[0], nc = p[1]+dir[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != 1 || vis.get(nr*m+nc)) continue;
                ++ans;
                vis.set(nr*m+nc);
                deq.addLast(new int[]{nr,nc});
            }
        }
        return ans;
    }
}
