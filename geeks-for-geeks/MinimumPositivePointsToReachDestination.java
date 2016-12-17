import java.util.concurrent.ThreadLocalRandom;

public class MinimumPositivePointsToReachDestination{
  public static void main(String[]args){
      int[][] points = randomPoints();
      System.out.println(solve(points));
  }

    private static int[][] randomPoints() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int R = 3+random.nextInt(10);
        int C = 3+random.nextInt(10);
        int[][] points = new int[R][C];
        for(int a = 0; a < R; ++a){
            for(int b = 0; b < C; ++b){
                points[a][b] = random.nextInt(-100,101);
            }
        }
        return points;
    }

    private static int solve(int[][] points){
    int R = points.length, C = points[0].length;
    int[][] dp = new int[R][C];
    for(int a = R-1; a >=0; --a){
      for(int b = C-1; b >= 0; --b) {
          if (a == R - 1 && b == C - 1) {
              dp[a][b] = points[a][b] < 0 ? -points[a][b] + 1 : 1;
              continue;
          }
          int nextMin;
          if (a == R - 1) {
              nextMin = dp[a][b + 1];
          } else if (b == C - 1) {
              nextMin = dp[a + 1][b];
          } else {
              nextMin = Math.min(dp[a + 1][b], dp[a][b + 1]);
          }

          if (points[a][b] > 0){
              dp[a][b]  = nextMin - points[a][b];
              if (nextMin - points[a][b] <= 0) {
                  dp[a][b] = 1;
              }
          } else if (points[a][b] == 0){
              dp[a][b] = nextMin;
          } else {//points[a][b] < 0
            dp[a][b] = nextMin + Math.abs(points[a][b]);
          }
      }
    }
    return dp[0][0];
  }
}
