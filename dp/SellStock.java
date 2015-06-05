import java.util.*;
class SellStock{
        public static void main(String[]args){
                int[] prices = new int[] {100,10,15,20,11,17,16,21,18,14,12,12,13,13,5};

                solveSellStockOne(prices);
                solveSellStockArbitrary(prices);
                solveSellStock1Times(prices);
                solveSellStock2Times(prices);
                solveSellStock3Times(prices);
                solveSellStock4Times(prices);
                solveSellStock5Times(prices);
                //new int[]{10, 12, 8 11, 11, 10, 12, 15, 13, 10}; N=10, K=5, ret=9
                //                                                 N=10, K=6, ret=10
        }
        private static void solveSellStockOne(int[] prices){
                SellStockOnce solver = new SellStockOnce();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices));
                assert Double.compare(2100.0, maxProfit) == 0;
        }
        private static void solveSellStockArbitrary(int[] prices){
                SellStockArbitrary solver = new SellStockArbitrary();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices));
                assert Double.compare(4394.886363636363, maxProfit) == 0;
        }
        private static void solveSellStock1Times(int[] prices){
                SellStockKTimes solver = new SellStockKTimes();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices, 1));
                assert Double.compare(2100.0, maxProfit) == 0;
        }
        private static void solveSellStock2Times(int[] prices){
                SellStockKTimes solver = new SellStockKTimes();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices, 2));
                assert Double.compare(3818.1818181818185, maxProfit) == 0;
        }
        private static void solveSellStock3Times(int[] prices){
                SellStockKTimes solver = new SellStockKTimes();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices, 3));
                assert Double.compare(4136.363636363637, maxProfit) == 0;
        }
        private static void solveSellStock4Times(int[] prices){
                SellStockKTimes solver = new SellStockKTimes();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices, 4));
                assert Double.compare(4394.886363636363, maxProfit) == 0;
        }
        private static void solveSellStock5Times(int[] prices){
                SellStockKTimes solver = new SellStockKTimes();
                double maxProfit;
                System.out.println(maxProfit = solver.findMaxProfit(prices, 5));
                assert Double.compare(4394.886363636363, maxProfit) == 0;
        }
}
class SellStockOnce{
        double findMaxProfit(int[] prices){
                double max = 1.0;
                for(int i = 0, min = prices[0]; i < prices.length; i++){
                        double factor = 1.0*prices[i]/min;
                        if (Double.compare(max, factor) < 0){
                                max = factor;
                        }
                        if (min > prices[i]){
                                min = prices[i];
                        }
                }
                return 1000.0*max;
        }
}
class SellStockArbitrary{
        double findMaxProfit(int[] prices){
                double[] dp = new double[prices.length+1];

                double max = 1.0;
                dp[0] = 1.0;
                for(int i = 0; i < prices.length; i++){
                        for(int k = 0; k < i; k++){
                                if (prices[k] < prices[i]){
                                        max = Math.max(max, 1.0*prices[i]/prices[k]*dp[k]);
                                }
                        }
                        dp[i+1] = max;
                }
                return 1000.0*max;
        }
}
class SellStockKTimes{
        double findMaxProfit(int[] prices, int K){
                double[][] dp = new double[K+1][prices.length+1];

                for(int i = 0; i < dp.length; i++){
                        dp[i][0] = 1.0;
                }
                for(int i = 0; i < dp[0].length; i++){
                        dp[0][i] = 1.0;
                }
                double overallMax = 1.0;
                for(int i = 1; i < dp.length; i++){
                        double max = 1.0;
                        for(int r = 0; r < prices.length; r++){
                                for(int s = 0; s < r; s++){
                                        max = Math.max(max, 1.0*dp[i-1][s]*prices[r]/prices[s]);
                                }
                                dp[i][r+1] = max;
                        }
                        overallMax = Math.max(max, overallMax);
                }
                return 1000.0*overallMax;
        }
}
