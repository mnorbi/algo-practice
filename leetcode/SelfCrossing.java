class Solution {
    private static int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static int[] lookbacks = {3,5};
    
    public boolean isSelfCrossing(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        
        int maxLookback = lookbacks[lookbacks.length-1];
        int[][] traces = new int[maxLookback][3];
        int x = 0, y = 0;
        for(int a = 0, px = 0, py = 0, from = 0, to = 0, level = 0; a < arr.length; ++a){
            px = x;
            py = y;
            x += dirs[a%4][0]*arr[a];
            y += dirs[a%4][1]*arr[a];

            from = py == y ? px : py;
            to = py == y ? x : y;
            level = py == y ? y : x;

            for(int lookback : lookbacks){
                if (a-lookback >= 0){
                    int lookbackId = (a-lookback)%maxLookback;
                    if (isCross(traces[lookbackId][0], traces[lookbackId][1], level) &&
                        isCross(from, to, traces[lookbackId][2])){
                        return true;
                    }
                }
            }

            int lookbackId = a%maxLookback;
            traces[lookbackId][0] = from;
            traces[lookbackId][1] = to;
            traces[lookbackId][2] = level;

        }
        return x == 0 && y == 0;
    }

    private boolean isCross(int from, int to, int level){
        return Integer.signum(level - from) != Integer.signum(level - to) || level == from || level == to;
    }
}
