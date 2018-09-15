import java.util.*;
import java.io.*;
public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.valueOf(br.readLine());
            for (int i = 1; i <= t; ++i) {
                int[] arr = toIntArr(br.readLine());
                double B = (double)arr[0]*750;
                int L = arr[1];
                int N = arr[2];
                double[][][] dp = new double[L+1][L+2][L+2];
                dp[1][1][1] = B;
                for(int l = 2; l <= L; ++l){
                        for(int r = 1; r <= l; ++r){
                                for(int c = 1; c <= r; ++c){
                                        dp[l][r][c] += Math.max(0, (dp[l-1][r-1][c-1]-250))/3;
                                        dp[l][r][c] += Math.max(0, (dp[l-1][r-1][c]-250))/3;
                                        dp[l][r][c] += Math.max(0, (dp[l-1][r][c]-250))/3;
                                }
                        }
                }
                int x = (int)((Math.sqrt(1+8*(N-1))-1)/2);
                int y = N-x*(x+1)/2;

                System.out.println("Case #" + i + ": "+Math.min((double)dp[L][x+1][y], 250));
            }
        }
    }

    static int[] toIntArr(String s) {
        return Arrays.stream(s.split("\\s+")).mapToInt(Integer::valueOf).toArray();
    }
}

