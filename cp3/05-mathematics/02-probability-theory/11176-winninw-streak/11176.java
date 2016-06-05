import java.util.*;
import java.io.*;
class Main{
  private static final int MAX_GAMES = 500;
  private static final double[][] dp = new double[MAX_GAMES+1][MAX_GAMES+1];
  private static final double[][] dpUpto = new double[MAX_GAMES+1][MAX_GAMES+1];
  private static final double[] powP = new double[MAX_GAMES+1];
  public static void main(String[]args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String[] tokens = in.readLine().split(" ");
            int maxGames = Integer.parseInt(tokens[0]);
            if (maxGames == 0) break;
            double p = Double.parseDouble(tokens[1]);
            double oneMinusP = 1.0-p;
            powP[0] = 1.0;
            for(int a = 1; a <= maxGames; ++a){
                powP[a] = powP[a-1]*p;
            }
            for(int streak = 1; streak <= maxGames; ++streak){
                dpUpto[0][streak] = 1;
            }
            dpUpto[1][0] = oneMinusP;
            for(int games = 2; games <= maxGames; ++games){
                dpUpto[games][0] = dpUpto[games-1][0]*oneMinusP;
            }
            for(int games = 1; games <= maxGames; ++games){
                for(int streak = 1; streak <= games; ++streak){
                    dp[games][streak] = 0;
                    for(int firstLoss = 1; firstLoss <= streak && games-firstLoss >= streak; ++firstLoss){
                        dp[games][streak] += powP[firstLoss-1]*oneMinusP*dp[games-firstLoss][streak];
                    }
                    if (games > streak){
                        if (games-streak-1 >= streak) {
                            dp[games][streak] += powP[streak] * oneMinusP * dpUpto[games - streak - 1][streak];
                        } else {
                            dp[games][streak] += powP[streak] * oneMinusP;
                        }
                    } else if (games == streak){
                        dp[games][streak] += powP[streak];
                    }
                    dpUpto[games][streak] = dpUpto[games][streak-1]+dp[games][streak];
                }
            }
            double ans = 0.0;
            for(int streak = 1; streak <= maxGames; ++streak){
                ans += streak*dp[maxGames][streak];
            }
            sb.append(String.format("%.10f",ans)).append('\n');
        }
        System.out.print(sb);
    }
}
