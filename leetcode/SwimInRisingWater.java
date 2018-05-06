class SwimInRisingWater{

class Solution {
    static int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    public int swimInWater(int[][] grid) {
        Map<Integer, int[]> lookup = new HashMap<>();
        
        int n = grid.length;
        for(int a = 0; a < n; ++a){
            for(int b = 0; b < n; ++b){
                lookup.put(grid[a][b], new int[]{a,b});
            }
        }
        
        UF uf = new UF(n*n);
        
        int from = grid[0][0];
        int to = grid[n-1][n-1];
        for(int water = 0; water < n*n; ++water){
            int[] pos = lookup.get(water);
            for(int[] dir : dirs){
                int nr = pos[0]+dir[0];
                int nc = pos[1]+dir[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] <= water){
                    uf.join(water, grid[nr][nc]);
                }
            }
            if (uf.isConnected(from, to)){
                return water;
            }
        }
        
        return -1;
    }
    
    static class UF{
        int[] pars;
        int[] sizes;
        UF(int size){
            pars = new int[size];
            for(int a = 0; a < pars.length; ++a){
                pars[a] = a;
            }
            sizes = new int[size];
            Arrays.fill(sizes,1);
        }
        
        int getPar(int a){
            while(a != pars[a]){
                a = pars[a] = pars[pars[a]];
            }
            return a;
        }
        
        boolean isConnected(int a, int b){
            return getPar(a) == getPar(b);
        }
        
        void join(int a, int b){
            a = getPar(a);
            b = getPar(b);
            
            if (a != b){
                if (sizes[a] < sizes[b]){
                    int tmp = a; a = b; b = tmp;
                }
                sizes[a] += sizes[b];
                pars[b] = a;
            }
        }
    }
}


}
