class WineCollection{
        public static void main(String[] args){
                WineCollection solver = new WineCollection();
                assert print(solver.maxProfitRecDp(new int[] {1, 4, 2, 3})) == 29;
        }

        private static int print(int i){
                System.out.println(i);
                return i;
        }

        public int maxProfitRec(int[] p){
                return maxProfitRec(p, 0, p.length-1, 1);
        }

        public int maxProfitRec(int[] p, int b, int e, int y){
                if (b > e){
                        return 0;
                }
                return Math.max(maxProfitRec(p, b+1, e, y+1)+p[b]*y, maxProfitRec(p, b, e-1, y+1)+p[e]*y);
        }

        public int maxProfitRecDp(int[] p){
                int N = p.length;
                int[][] dp = new int[N][N];
                return maxProfitRecDp(p, dp, 0, N-1);
        }
        private int maxProfitRecDp(int[] p, int[][] dp, int b, int e){
                if (b > e) {
                        return 0;
                }
                if (dp[b][e] != 0){
                        return dp[b][e];
                }
                int N = p.length;
                int year = b + (N-1 - e) + 1;
                dp[b][e] = Math.max(maxProfitRecDp(p, dp, b+1, e)+ p[b]*year, maxProfitRecDp(p, dp, b, e-1)+p[e]*year);
                return dp[b][e];
        }
}
