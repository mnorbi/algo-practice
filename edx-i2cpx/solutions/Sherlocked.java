import java.io.*;
import java.util.*;
public class Sherlocked {
    private static int[][] dirs = new int[][] {
        {-1,0},{1,0},{0,1},{0,-1}
    };
    public static void main(String[] args) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("sherlocked.in"));
                FileWriter fw = new FileWriter("sherlocked.out");
        ) {
            int[] arr = toArray(br.readLine());
            int N = arr[0], M = arr[1];
            int[][] mat = new int[N][M];
            int[][] dp = new int[N][M];
            for(int a = 0; a < N; ++a){
                mat[a] = toArray(br.readLine());
                Arrays.fill(dp[a], -1);
            }
            int ans = 0;
            for(int a = 0; a < N; ++a){
                for(int b = 0; b < M; ++b){
                    ans = Math.max(ans, fill(dp, mat, a, b));
                }
            }
            fw.write(""+ans);
        }
    }
    static boolean valid(int a, int b, int[][] mat){
        return a >= 0 && a < mat.length && b >= 0 && b < mat[0].length;
    }
    static int fill(int[][] dp, int[][] mat, int a, int b){
        if (dp[a][b] != -1) { return dp[a][b]; }
        dp[a][b] = 1;
        for(int[] dir : dirs){
            int pa = a+dir[0], pb = b+dir[1];
            if (valid(pa, pb, mat) && (mat[pa][pb] == mat[a][b]-1)){
                dp[a][b] = Math.max(dp[a][b], fill(dp,mat, pa, pb)+1);
            }
        }
        return dp[a][b];
    }
    static int[] toArray (String s){
        return Arrays.stream(s.split(" ")).mapToInt(Integer::valueOf).toArray();
    }
}
